/**
 * 
 */
package com.matrimony.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Timestamp;

import javax.imageio.ImageIO;
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
		try {
			billDocument=IOUtils.toString(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String printBillPos(PaymentInfo pi, Timestamp time){
		PaymentDetailsResponse pdr=PaypalPayment.checkPaymentByPayKey("AP-8X8899913N648960S");
		pi=pdr.getPaymentInfoList().getPaymentInfo().get(0);
		String billText=billDocument;
		System.out.println(billText.contains("totalMoney"));
		billText=billText.replaceAll("totalMoney", String.valueOf(pi.getReceiver().getAmount()));
		billText=billText.replace("status", pi.getSenderTransactionStatus());
		return billText;
		
	}
	public static void main(String[] args) {
		Printer p=new Printer();
		System.out.println(p.printBillPos(null, null));
	}
}
