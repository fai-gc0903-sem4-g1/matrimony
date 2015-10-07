/**
 * 
 */
package com.matrimony.database;

import org.hibernate.Session;

import com.matrimony.entity.Transaction;
import com.matrimony.exception.STException;
import com.matrimony.exception.STException.TransactionAlready;
import com.matrimony.util.HibernateUtil;

/**
 * @author SON
 *
 */
public class TransactionDAO {
	public static void add(Transaction transaction) throws TransactionAlready{
		if(findById(transaction.getId())!=null)
			throw new STException.TransactionAlready("TransactionAlready");
		Session ss=HibernateUtil.session;
		ss.getTransaction().begin();
		ss.save(transaction);
		ss.getTransaction().commit();
	}
	
	public static Transaction findById(String id){
		return (Transaction) HibernateUtil.session.get(Transaction.class, id);
	}
}
