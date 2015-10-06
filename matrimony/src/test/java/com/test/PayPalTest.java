/**
 * 
 */
package com.test;

import java.io.IOException;

import javassist.expr.NewArray;

import com.paypal.api.PaypalAPI;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.types.ap.PaymentDetailsRequest;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;
import com.paypal.svcs.types.common.RequestEnvelope;

/**
 * @author SON
 *
 */
public class PayPalTest {
	public static void main(String[] args) {
		PaymentDetailsRequest paymentDetailsRequest=new PaymentDetailsRequest(new RequestEnvelope("en_US"));
		paymentDetailsRequest.setPayKey("AP-4S4378867U246394U");
		
		try {
			PaymentDetailsResponse paymentDetailsResponse=PaypalAPI.getService().paymentDetails(paymentDetailsRequest);
			if(paymentDetailsResponse.getResponseEnvelope().getAck().toString().equals(PaypalAPI.SUCCESS)){
				System.out.println("ngon");
				System.out.println(paymentDetailsResponse.getStatus());
				System.out.println(paymentDetailsResponse.getPaymentInfoList().getPaymentInfo().get(0).getReceiver().getAmount());
//			System.out.println(paymentDetailsResponse.getPaymentInfoList().getPaymentInfo().get(0).getSenderTransactionId());
			}else{
				System.out.println("thua");
				System.out.println(paymentDetailsResponse.getError().get(0).getMessage());
			}
		} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
				| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
				| OAuthException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
