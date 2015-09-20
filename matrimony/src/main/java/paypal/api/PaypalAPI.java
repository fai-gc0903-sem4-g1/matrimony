/**
 * 
 */
package paypal.api;

import com.paypal.svcs.services.AdaptivePaymentsService;

/**
 * @author SON
 *
 */
public class PaypalAPI {
	public final static String SUCCESS="SUCCESS";
	public final static String COMPLETED="COMPLETED";
	public static AdaptivePaymentsService getService(){
		AdaptivePaymentsService adaptivePaymentsService=new AdaptivePaymentsService(CredentialsConfiguration.getConfig());
		return adaptivePaymentsService;
	}
}
