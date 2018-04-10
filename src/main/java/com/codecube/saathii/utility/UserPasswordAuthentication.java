package com.codecube.saathii.utility;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserPasswordAuthentication {
	
	
	@Autowired
	PasswordUtility pas;
	
	 @Autowired
	SessionFactory sessionFactory;
	
	public boolean IsAuthenticate(String userId, String passord) throws Exception
	{
	String pasenc = pas.encryption(passord);
	System.out.println(pasenc);
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	boolean userFound = false;
	String SQL_QUERY ="select * from usertbls u where u.userid = :userid and password = :password";
	Query query = session.createNativeQuery(SQL_QUERY);
	query.setParameter("userid",userId);
	query.setParameter("password",pasenc);
	List list = query.list();
	if ((list != null) && (list.size() > 0)) {
		userFound= true;
		System.out.println("=============================================================================================");
		System.out.println("\t\tUSER FOUND");
		System.out.println("=============================================================================================");
	}
	else
	{
		System.out.println("=============================================================================================");
		System.out.println("\t\tUSER NOT FOUND");
		System.out.println("=============================================================================================");
	}
	tx.commit();
	session.close();
	return userFound;    	
	}
	
}
