/**
 * 
 */
package paypal.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.types.ap.ExecutePaymentRequest;
import com.paypal.svcs.types.ap.ExecutePaymentResponse;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;

/**
 * @author SON
 *
 */
public class Payment {
	private final String SUCCESS = "SUCCESS";
	private final String FAILURE = "FAILURE";

	public String pay(double money) throws SSLConfigurationException, InvalidCredentialException,
			UnsupportedEncodingException, HttpErrorException, InvalidResponseDataException,
			ClientActionRequiredException, MissingCredentialException, OAuthException, IOException,
			InterruptedException {
		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		List<Receiver> receivers = new ArrayList<Receiver>();

		Receiver receiver = new Receiver();
		receiver.setAmount(money);
		receiver.setEmail("matrimony_master@gmail.com");
		receivers.add(receiver);

		ReceiverList receiverList = new ReceiverList(receivers);

		PayRequest payRequest = new PayRequest();
		payRequest.setRequestEnvelope(requestEnvelope);
		payRequest.setReceiverList(receiverList);
		payRequest.setCurrencyCode("USD");
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl("http://localhost:8080/matrimony/paymentVerify");
		payRequest.setReturnUrl("http://localhost:8080/matrimony/paymentVerify");

		PayResponse payResponse;

		payResponse = PaypalAPI.service().pay(payRequest);
		String value = payResponse.getResponseEnvelope().getAck().getValue();
		System.out.println("Payment Pay: " + value);
		if (SUCCESS.equalsIgnoreCase(value))
			return payResponse.getPayKey();
		else {
			System.out.println("Payment Pay: " + payResponse.getError().get(0).getMessage());
			return null;
		}

	}

	public String checkPayment(String payKey) throws SSLConfigurationException, InvalidCredentialException, UnsupportedEncodingException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, IOException, InterruptedException{
		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		ExecutePaymentRequest executePaymentRequest=new ExecutePaymentRequest(requestEnvelope, payKey);
		ExecutePaymentResponse executePaymentResponse=PaypalAPI.service().executePayment(executePaymentRequest);
		String value=executePaymentResponse.getResponseEnvelope().getAck().getValue();
		System.out.println("ExecutePayment: " +value);
		if(SUCCESS.equalsIgnoreCase(value)){
			return executePaymentResponse.getPaymentExecStatus();
		}
		else{
			System.out.println("ExecutePayment: "+executePaymentResponse.getError().get(0).getMessage());
			return null;
		}
	}
}
