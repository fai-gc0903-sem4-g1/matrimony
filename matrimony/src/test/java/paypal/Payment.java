/**
 * 
 */
package paypal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
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
	public final static String SAND_BOX_STRING="https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey=";

	public static void main(String[] args) {
		RequestEnvelope env = new RequestEnvelope();
		env.setErrorLanguage("vi_VN");
		
		List<Receiver> receivers = new ArrayList<Receiver>();
		Receiver receiver = new Receiver();
		receiver.setAmount(26.00);
		receiver.setEmail("matrimony_master@gmail.com");
//		PhoneNumberType phoneNumberType=new PhoneNumberType("84", "966377095");
//		receiver.setPhone(phoneNumberType);
		receivers.add(receiver);
		
		ReceiverList receiverlst = new ReceiverList(receivers);

		PayRequest payRequest = new PayRequest();
		payRequest.setReceiverList(receiverlst);
		payRequest.setRequestEnvelope(env);
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl("http://www.google.com");
		payRequest.setReturnUrl("http://www.facebook.com");
		payRequest.setCurrencyCode("USD");
		
//		FundingConstraint fundingConstraint = new FundingConstraint();
//		List<FundingTypeInfo> fundingTypeInfoList = new ArrayList<FundingTypeInfo>();
//		
//		fundingTypeInfoList.add(new FundingTypeInfo("CREDITCARD"));
//		FundingTypeList fundingTypeList=new FundingTypeList(fundingTypeInfoList);
//		fundingConstraint.setAllowedFundingType(fundingTypeList);
//		payRequest.setFundingConstraint(fundingConstraint);
		

		
		try {
			PayResponse payResponse = APICredentials.getAdaptivePaymentsService().pay(payRequest);
			System.out.println(payResponse.getResponseEnvelope().getAck());
			if("SUCCESS".equals(payResponse.getResponseEnvelope().getAck().toString())){
				System.out.println(SAND_BOX_STRING+payResponse.getPayKey());
			}
			else{
				System.out.println(payResponse.getError().get(0).getMessage());
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
