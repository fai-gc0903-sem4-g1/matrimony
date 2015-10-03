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
		else {
			String ssReturnKey = (String) request.getSession().getAttribute("returnKey");
			if (returnKey != null && returnKey.equals(ssReturnKey)) {
				request.getSession().setAttribute("paymentConfirm", 1);
				return "redirect:paymentConfirm";
			}
			return "payment";
		}
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
				try {
					String returnKey = RandomStringUtils.randomNumeric(26);
					PayResponse payResponse = payment.pay(finalPayment, "http://localhost/matrimony/payment?returnKey="
							+ returnKey, "http://localhost/matrimony/payment", "USD");
					if (null != payResponse) {
						request.getSession().setAttribute(SessionKey.PAYPAL_PAY_RESPONSE, payResponse);
						request.getSession().setAttribute("returnKey", returnKey);
						return "redirect:" + CredentialsConfiguration.SAND_BOX + payResponse.getPayKey();
					}
					request.setAttribute("paymentTimeOut", 1);
				} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
						| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
						| OAuthException | IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("paypalError", 1);
				}
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
	public String viewPaymentConfirm(HttpServletRequest request) {
		if (request.getSession().getAttribute("paymentConfirm") != null) {
			return "paymentConfirm";
		} else
			return "payment";
	}

	@RequestMapping(value = "paymentConfirm", method = RequestMethod.POST)
	public String doPaymentConfirm(HttpServletRequest request) {
		boolean paymentSussess = false;
		int totalMonth = 1;
		User ssUser = (User) request.getSession().getAttribute("user");
		if (paymentSussess) {
			Timestamp expiries = ssUser.getExpiries();
			Timestamp timeNow = new Timestamp(System.currentTimeMillis());
			Calendar calendar;
			if (expiries.after(timeNow))
				calendar = DateUtils.toCalendar(expiries);
			else
				calendar = DateUtils.toCalendar(timeNow);
			calendar.set(Calendar.MONTH, totalMonth);

			ssUser.setExpiries(timeNow);
			// UPDATE USER EXPIRES
			UserDAO.Update(ssUser);
			Transaction transaction = new Transaction();
			transaction.setId(RandomStringUtils.randomAlphanumeric(26));
			transaction.setCreateAt(timeNow);
			transaction.setUserId(ssUser.getId());
			transaction.setCurrencyCode("USD");
			transaction.setMethod("PAYPAL");
			transaction.setDecription("ghi chú");
			transaction.setAmount(49.99);
			TransactionDAO.add(transaction);
			request.getSession().setAttribute("user", ssUser);
			request.getSession().setAttribute("paymentConfirm", null);
			return "redirect:";
		} else {
			return "failed";
		}
	}

	@RequestMapping(value = "paymentVerify", method = RequestMethod.GET)
	public String doPaymentVerify(HttpServletRequest request, @ModelAttribute("transaction") Transaction transaction) {
		System.out.println(transaction);
		try {
			PaymentDetailsResponse paymentDetailsResponse = payment.checkPayment(transaction.getId());
			if (null != paymentDetailsResponse) {
				if (PaypalAPI.SUCCESS.equals(paymentDetailsResponse)) {
					User currentUser = (User) request.getSession().getAttribute(SessionKey.USER);

					double amount = paymentDetailsResponse.getPaymentInfoList().getPaymentInfo().get(0).getReceiver()
							.getAmount();

					transaction.setCreateAt(new Timestamp(System.currentTimeMillis()));
					transaction.setUserId(currentUser.getId());
					transaction.setCurrencyCode(paymentDetailsResponse.getCurrencyCode());
					transaction.setMethod("PAYPAL");
					transaction.setDecription("ghi chú");
					transaction.setAmount(amount);
					transaction.setId(RandomStringUtils.randomAlphanumeric(26));
					TransactionDAO.add(transaction);

					Timestamp expiries = currentUser.getExpiries();
					Calendar calendar;
					if (expiries.after(new Timestamp(System.currentTimeMillis())))
						calendar = DateUtils.toCalendar(expiries);
					else
						calendar = DateUtils.toCalendar(new Timestamp(System.currentTimeMillis()));

					if (49.99 == transaction.getAmount())
						calendar.set(Calendar.MONTH, 1);
					else if (499.99 == transaction.getAmount())
						calendar.set(Calendar.MONTH, 12);

					currentUser.setExpiries(new Timestamp(calendar.getTimeInMillis()));
					// UPDATE USER EXPIRES
					UserDAO.Update(currentUser);
					// RESET USER
					request.getSession().setAttribute(SessionKey.USER, currentUser);
					// CLEAR SESSION
					request.getSession().setAttribute(SessionKey.PAYPAL_AMOUNT_PAY, null);
					request.getSession().setAttribute(SessionKey.PAYPAL_PAY_RESPONSE, null);
					request.setAttribute("psCode", 2);
					return "payment";
				}
			}
		} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
				| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
				| OAuthException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "Co_loi_xay_ra";
	}

	@RequestMapping(value = "paymentVerify2", method = RequestMethod.GET)
	public String doPaymentVerify2(HttpServletRequest request, @ModelAttribute("transaction") Transaction transaction) {
		User currentUser = (User) request.getSession().getAttribute(SessionKey.USER);
		Timestamp news = new Timestamp(System.currentTimeMillis());
		Calendar calendar = DateUtils.toCalendar(news);
		calendar.add(Calendar.MONTH, 1);
		currentUser.setExpiries(new Timestamp(calendar.getTimeInMillis()));
		// UPDATE USER EXPIRES
		UserDAO.Update(currentUser);
		// RESET USER
		request.getSession().setAttribute(SessionKey.USER, currentUser);
		request.setAttribute("psCode", 2);
		return "payment";
	}
}
