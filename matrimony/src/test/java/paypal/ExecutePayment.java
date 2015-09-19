/**
 * 
 */
package paypal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.types.ap.ExecutePaymentRequest;
import com.paypal.svcs.types.ap.ExecutePaymentResponse;
import com.paypal.svcs.types.common.RequestEnvelope;

/**
 * @author SON
 *
 */
public class ExecutePayment {
	public static void main(String[] args) {
		RequestEnvelope requestEnvelope = new RequestEnvelope("vi_VN");
		ExecutePaymentRequest executePaymentRequest=new ExecutePaymentRequest();
		executePaymentRequest.setRequestEnvelope(requestEnvelope);
		executePaymentRequest.setPayKey("AP-52K43495RW262122F");
		executePaymentRequest.setActionType("PAY");
		try {
			ExecutePaymentResponse executePaymentResponse=APICredentials.getAdaptivePaymentsService().executePayment(executePaymentRequest);
			String value=executePaymentResponse.getResponseEnvelope().getAck().getValue();
			System.out.println(value);
			if("Failure".equals(value)){
				System.out.println(executePaymentResponse.getError().get(0).getMessage());
			}else {
				System.out.println(executePaymentResponse.getPaymentExecStatus());
			}
		} catch (SSLConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
