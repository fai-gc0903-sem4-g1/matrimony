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
	public static AdaptivePaymentsService service(){
		AdaptivePaymentsService adaptivePaymentsService=new AdaptivePaymentsService(CredentialsConfiguration.getConfig());
		return adaptivePaymentsService;
	}
}
