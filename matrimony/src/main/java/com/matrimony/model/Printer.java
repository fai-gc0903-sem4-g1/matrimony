/**
 * 
 */
package com.matrimony.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
	public String printBillPos(PaymentInfo pi, Timestamp time, int month){
		PaymentDetailsResponse pdr=PaypalPayment.checkPaymentByPayKey("AP-8X8899913N648960S");
		pi=pdr.getPaymentInfoList().getPaymentInfo().get(0);
		String billText=billDocument;
		System.out.println(billText.contains("totalMoney"));
		billText=billText.replaceAll("totalMoney", String.valueOf(pi.getReceiver().getAmount()));
		billText=billText.replace("status", pi.getSenderTransactionStatus());
		SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df2=new SimpleDateFormat("HH:mm:ss");
		billText=billText.replace("payDate", df.format(time));
		billText=billText.replace("payTime", df2.format(time));
		billText=billText.replace("payMonth", String.valueOf(month));
		return billText;
	}
	public static void main(String[] args) {
		Printer p=new Printer();
		System.out.println(p.printBillPos(null, new Timestamp(System.currentTimeMillis()), 12));
	}
}
