/**
 * 
 */
package com.matrimony.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;

import paypal.api.CredentialsConfiguration;
import paypal.api.Payment;

/**
 * @author SON
 *
 */
@Controller
public class PaymentController {
	private final Payment payment = new Payment();
	@RequestMapping(value = "payment", method = RequestMethod.GET)
	public String viewPayment() {
		return "payment";
	}

	@RequestMapping(value = "payment", method = RequestMethod.POST)
	public String doPayment(HttpServletRequest request, String productValue, String payWith) {
		System.out.println(productValue);
		System.out.println(payWith);
		switch (payWith) {
		case "Paypal":
			
			double moneyToPay;
			if ("1".equals(productValue)) {
				moneyToPay = 49.99;
			} else if ("12".equals(productValue)) {
				moneyToPay = 499.99;
			} else {
				return "404";
			}
			String payKey;
			try {
				payKey = payment.pay(moneyToPay);
				if (null != payKey) {
					request.getSession().setAttribute("paypalPayKey", payKey);
					return "redirect:" + CredentialsConfiguration.SAND_BOX_STRING + payKey;
				}
			} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
					| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
					| OAuthException | IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return "payment";
	}
	
	@RequestMapping(value = "paymentVerify", method = RequestMethod.GET)
	public String doPaymentVerify(HttpServletRequest request) {
		if (null != request.getSession().getAttribute("paypalPayKey")) {
			try {
				String checkString=payment.checkPayment((String) request.getSession().getAttribute("paypalPayKey"));
				System.out.println(checkString);
			} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
					| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
					| OAuthException | IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return "payment";
	}

}
