/**
 * 
 */
package com.matrimony.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrimony.database.TransactionDAO;
import com.matrimony.database.UserDAO;
import com.matrimony.entity.Transaction;
import com.matrimony.entity.User;
import com.matrimony.exception.STException.TransactionAlready;
import com.matrimony.model.Printer;
import com.matrimony.model.SessionKey;
import com.matrimony.util.MailUtil;
import com.paypal.api.CredentialsConfiguration;
import com.paypal.api.PaypalPayment;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;

/**
 * @author SON
 *
 */
@Controller
public class PaymentController {

	@RequestMapping(value = "payment", method = RequestMethod.GET)
	public String viewPayment(HttpServletRequest request, String returnKey) {
		User ssUser = (User) request.getSession().getAttribute(SessionKey.USER);
		if (ssUser == null)
			return "joinUs";
		else
			return "payment";
	}
	
	@RequestMapping(value = "paymentHistory", method = RequestMethod.GET)
	public String viewHistoryTransaction(HttpServletRequest request) {
		User ssUser = (User) request.getSession().getAttribute(SessionKey.USER);
		if (ssUser == null)
			return "joinUs";
		else
			return "paymentHistory";
	}

	@RequestMapping(value = "payment", method = RequestMethod.POST)
	public String doPayment(HttpServletRequest request, String pack, String payWith) {
		boolean verifyForm = true;
		User currentUser = (User) request.getSession().getAttribute(SessionKey.USER);
		if (currentUser == null)
			return "joinUs";
		System.out.println("pack: " + pack);
		System.out.println("payWith: " + payWith);

		double finalPayment = 0;
		if ("1".equals(pack))
			finalPayment = 49.99;
		else if ("12".equals(pack))
			finalPayment = 499.99;
		else {
			request.setAttribute("finalPaymentInvalid", 1);
			verifyForm = false;
		}

		if (payWith == null) {
			request.setAttribute("payWithNotNull", 1);
			verifyForm = false;
		}
		// MAJOR
		if (verifyForm) {
			switch (payWith) {
			case "paypal":
				String paymentConfirmCode = RandomStringUtils.randomNumeric(26);
				PayResponse payResponse = PaypalPayment.pay(finalPayment,
						"http://localhost/matrimony/paymentConfirm?code=" + paymentConfirmCode,
						"http://localhost/matrimony/payment", "USD");
				if (null != payResponse) {
					request.getSession().setAttribute("paypalPayResponse", payResponse);
					request.getSession().setAttribute("paymentConfirmCode", paymentConfirmCode);
					System.out.println("Code xac nhan ne: "+paymentConfirmCode);
					return "redirect:" + CredentialsConfiguration.SAND_BOX + payResponse.getPayKey();
				}
				request.setAttribute("paymentTimeOut", 1);
			case "credit":
				request.setAttribute("creditNotSupport", 1);
			default:
				request.setAttribute("payWithInvalid", 1);
				break;
			}
		}
		return "payment";

	}

	@RequestMapping(value = "paymentConfirm", method = RequestMethod.GET)
	public String viewPaymentConfirm(HttpServletRequest request, String code){
		User ssUser = (User) request.getSession().getAttribute("user");
		PayResponse pr = (PayResponse) request.getSession().getAttribute("paypalPayResponse");
		if (ssUser == null)
			return "redirect:";
		if (pr == null)
			return "redirect:payment";

		if (code != null && code.equals(request.getSession().getAttribute("paymentConfirmCode"))) {
			PaymentDetailsResponse pdr = PaypalPayment.checkPaymentByPayKey(pr.getPayKey());
			if ("COMPLETED".equals(pdr.getStatus())) {
				System.out.println("Thanh toán: Xác nhận đã thanh toán");
				Timestamp expiries = ssUser.getExpiries();
				Timestamp timeNow = new Timestamp(System.currentTimeMillis());
				Calendar calendar;
				if (expiries.after(timeNow))
					calendar = DateUtils.toCalendar(expiries);
				else
					calendar = DateUtils.toCalendar(timeNow);

				int payMonth=0;
				if (pdr.getPaymentInfoList().getPaymentInfo().get(0).getReceiver().getAmount() == 49.99)
					payMonth=1;
				else if (pdr.getPaymentInfoList().getPaymentInfo().get(0).getReceiver().getAmount() == 499.99)
					payMonth=12;
				
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONDAY)+payMonth);

				ssUser.setExpiries(new Timestamp(calendar.getTimeInMillis()));
				
				Transaction transaction = new Transaction();
				transaction.setId(pdr.getPaymentInfoList().getPaymentInfo().get(0).getSenderTransactionId());
				transaction.setCreateAt(timeNow);
				transaction.setUser(ssUser);
				transaction.setCurrencyCode(pdr.getCurrencyCode());
				transaction.setMethod("PAYPAL");
				transaction.setDecription("Pay for "+payMonth+" month");
				transaction.setAmount(pdr.getPaymentInfoList().getPaymentInfo().get(0).getReceiver().getAmount());
				try {
					TransactionDAO.add(transaction);
					UserDAO.Update(ssUser);
					request.getSession().setAttribute("paypalPayResponse", null);
					request.setAttribute("paymentResultSuccess", "paymentResultSuccess");
					
					//send bill to email
					Printer printer=new Printer();
					String bill=printer.printBillPos(pdr, timeNow);
					StringBuilder sb=new StringBuilder("Xác nhận chuyển khoản qua paypal\n\n");
					sb.append(bill);
					MailUtil mailUtil=new MailUtil(ssUser.getEmail(), "Xác nhận thanh toán chuyển khoản qua Paypal", sb.toString());
					mailUtil.run();
				} catch (TransactionAlready e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("paymentResultFailed", "paymentTransactionAlready");
				}
			} else {
				request.setAttribute("paymentResultFailed", "paymentResultFailed");
			}
			
			return "paymentResult";
			
		} else
			return "code_khong_trung_hoac_biNull";
	}
}
