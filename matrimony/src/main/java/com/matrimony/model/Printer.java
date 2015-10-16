/**
 * 
 */
package com.matrimony.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.Timestamp;

import javax.print.DocFlavor.INPUT_STREAM;

import com.matrimony.util.IOUtils;
import com.paypal.api.PaypalPayment;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;
import com.paypal.svcs.types.ap.PaymentInfo;

/**
 * @author SON
 *
 */
public class Printer {
	/**
	 * 
	 */
	private String billDocument;
	public Printer() {
		InputStream is=getClass().getResourceAsStream("/resoucres/billTemplate.txt");
		System.out.println(is);
		try {
			System.out.println(IOUtils.toString(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BufferedImage printBillPos(PaymentInfo pi, Timestamp time){
		PaymentDetailsResponse pdr=PaypalPayment.checkPaymentByPayKey("AP-8X8899913N648960S");
		pi=pdr.getPaymentInfoList().getPaymentInfo().get(0);
		System.out.println(pdr.getPaymentInfoList().getPaymentInfo().get(0).getSenderTransactionId());
		System.out.println(pdr.getPaymentInfoList().getPaymentInfo().get(0).getSenderTransactionStatus());
		System.out.println(pdr.getStatus());
		return null;
		
	}
	public static void main(String[] args) {
		Printer p=new Printer();
//		p.printBillPos(null, null);
	}
}
