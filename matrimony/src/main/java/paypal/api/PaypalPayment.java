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
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.PaymentDetailsRequest;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;

/**
 * @author SON
 *
 */
public class PaypalPayment {

	public PayResponse pay(double money) throws SSLConfigurationException, InvalidCredentialException,
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
		payRequest.setCancelUrl("http://localhost:8080/matrimony/payment");
		payRequest.setReturnUrl("http://localhost:8080/matrimony/paymentVerify");

		PayResponse payResponse;

		payResponse = PaypalAPI.getService().pay(payRequest);
		String value = payResponse.getResponseEnvelope().getAck().getValue();
		System.out.println("PayResponse: " + value);
		if (PaypalAPI.SUCCESS.equalsIgnoreCase(value))
			return payResponse;
		else {
			System.out.println("PayResponse: " + payResponse.getError().get(0).getMessage());
			return null;
		}
	}

	public PaymentDetailsResponse checkPayment(String transactionId) throws SSLConfigurationException, InvalidCredentialException, UnsupportedEncodingException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, IOException, InterruptedException{
		RequestEnvelope requestEnvelope=new RequestEnvelope("vi_VN");
		PaymentDetailsRequest paymentDetailsRequest=new PaymentDetailsRequest(requestEnvelope);
		paymentDetailsRequest.setTransactionId(transactionId);
		
		PaymentDetailsResponse paymentDetailsResponse = PaypalAPI.getService().paymentDetails(paymentDetailsRequest);
			String value=paymentDetailsResponse.getResponseEnvelope().getAck().getValue();
			System.out.println("PaymentDetailsResponse: " +value);
			if(PaypalAPI.SUCCESS.equalsIgnoreCase(value)){
				return paymentDetailsResponse;
			}
			else{
				System.out.println("PaymentDetailsResponse: "+paymentDetailsResponse.getError().get(0).getMessage());
				return null;
			}
	}
	public static void main(String[] args) {
		PaypalPayment payment=new PaypalPayment();
		try {
			PaymentDetailsResponse detailsResponse=payment.checkPayment("7FY7743789402974J");
			System.out.println(detailsResponse.getPaymentInfoList().getPaymentInfo().get(0).getReceiver().getAmount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
