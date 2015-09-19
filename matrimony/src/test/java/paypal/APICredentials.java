/**
 * 
 */
package paypal;


import java.util.Properties;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.paypal.svcs.services.AdaptivePaymentsService;


/**
 * @author SON
 *
 */
public class APICredentials {
	private final static String USERNAME_STRING="matrimony_master_api1.gmail.com";
	private final static String PASSWORD_STRING="VVHHZGZRV4FWZLVX";
	private final static String SIGNATURE_STRING="AiPC9BjkCyDFQXbSkoZcgqH3hpacArAciuAhnPhFDqUjrxDyeiJH4vpW";
	private final static String APP_ID_STRING="APP-80W284485P519543T";
	private static AdaptivePaymentsService adaptivePaymentsService;
	
	public static Properties getConfig(){
		Properties config = new Properties();
		config.put("mode", "sandbox"); // Load the map with all mandatory parameters
		config.put("acct1.UserName", USERNAME_STRING);
		config.put("acct1.Password", PASSWORD_STRING);
		config.put("acct1.Signature", SIGNATURE_STRING);
		config.put("acct1.AppId", APP_ID_STRING);
		return config;
	}

	public static AdaptivePaymentsService getAdaptivePaymentsService() {
		if(adaptivePaymentsService==null){
			 adaptivePaymentsService = new AdaptivePaymentsService(APICredentials.getConfig());
		}
		return adaptivePaymentsService;
	}
}
