/**
 * 
 */
package com.matrimony.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrimony.database.TransactionDAO;
import com.matrimony.database.UserDAO;
import com.matrimony.entity.Transaction;
import com.matrimony.entity.User;
import com.matrimony.exception.STException.EntityIDHaveAlready;
import com.matrimony.model.SessionKey;
import com.paypal.api.CredentialsConfiguration;
import com.paypal.api.PaypalAPI;
import com.paypal.api.PaypalPayment;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;

/**
 * @author SON
 *
 */
@Controller
public class PaymentController {
	private final PaypalPayment payment = new PaypalPayment();

	@RequestMapping(value = "payment", method = RequestMethod.GET)
	public String viewPayment(HttpServletRequest request, String returnKey) {
		User ssUser = (User) request.getSession().getAttribute(SessionKey.USER);
		if (ssUser == null)
			return "joinUs";
		else
			return "payment";
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
					System.out.println("Da luu paypalPayResponse");
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
	public String viewPaymentConfirm(HttpServletRequest request, String code) {
		System.out.println("Code thanh toán: " +code);
		User ssUser = (User) request.getSession().getAttribute("user");
		PayResponse pr = (PayResponse) request.getSession().getAttribute("paypalPayResponse");
		if (ssUser == null)
			return "redirect:";
		if (pr == null)
			return "redirect:payment";

		if (code != null && code.equals(request.getAttribute("paymentConfirmCode"))) {
			System.out.println("da chay vao day");
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

				if (pdr.getPaymentInfoList().getPaymentInfo().get(0).getReceiver().getAmount() == 49.99)
					calendar.set(Calendar.MONTH, 1);
				else if (pdr.getPaymentInfoList().getPaymentInfo().get(0).getReceiver().getAmount() == 499.99)
					calendar.set(Calendar.MONTH, 12);

				ssUser.setExpiries(new Timestamp(calendar.getTimeInMillis()));
				// UPDATE USER EXPIRES
				UserDAO.Update(ssUser);
				Transaction transaction = new Transaction();
				transaction.setId(RandomStringUtils.randomAlphanumeric(26));
				transaction.setCreateAt(timeNow);
				transaction.setUserId(ssUser.getId());
				transaction.setCurrencyCode(pdr.getCurrencyCode());
				transaction.setMethod("Paypal");
				transaction.setDecription("ghi chú");
				transaction.setAmount(pdr.getPaymentInfoList().getPaymentInfo().get(0).getReceiver().getAmount());
				TransactionDAO.add(transaction);
				request.getSession().setAttribute("user", ssUser);
				request.getSession().setAttribute("paymentConfirm", null);
				request.setAttribute("paymentResultSuccess", "paymentResultSuccess");
			} else {
				request.setAttribute("paymentResultFailed", "paymentResultFailed");
			}
			return "paymentResult";
		} else
			return "redirect:payment";
	}
}
