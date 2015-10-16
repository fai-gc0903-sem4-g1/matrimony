/**
 * 
 */
package com.matrimony.database;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import com.matrimony.entity.Transaction;
import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.exception.STException.TransactionAlready;
import com.matrimony.model.Printer;
import com.matrimony.util.HibernateUtil;
import com.paypal.api.PaypalPayment;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;

/**
 * @author SON
 *
 */
public class TransactionDAO {
	public static void add(Transaction transaction) throws TransactionAlready {
		if (findById(transaction.getId()) != null)
			throw new STException.TransactionAlready("TransactionAlready");
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		ss.save(transaction);
		ss.getTransaction().commit();
	}

	public static Transaction findById(String transactionId) {
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		Transaction tran = (Transaction) HibernateUtil.getCurrentSession().get(Transaction.class, transactionId);
		ss.getTransaction().commit();
		return tran;
	}

	public static Map<Timestamp, String> viewAllBillTransaction(String userId){
		System.out.println("Nhan user"+userId);
		User ssUser=UserDAO.findById(userId);
		Map<Timestamp, String> map=new HashMap<Timestamp, String>();
		Printer printer=new Printer();
		for(Transaction tran:ssUser.getTransactions()){
			PaymentDetailsResponse pdr=PaypalPayment.checkPaymentByTransactionId(tran.getId());
			String bill=printer.printBillPos(pdr, tran.getCreateAt());
			bill=bill.replaceAll(" ", "&nbsp;");
			bill=bill.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			bill=bill.replaceAll("\n", "<br/>");
			map.put(tran.getCreateAt(), bill);
		}
		return map;
	}
	
	
	public static void main(String[] args) {
//		Map<Timestamp, String> map=viewAllBillTransaction("990258dd506c243201506c24685b0000");
//		map.forEach((k,v)->System.out.println("ngay: "+k+"\n"+v));
		System.out.println(findById("4T751068EB580751G"));
	}
}
