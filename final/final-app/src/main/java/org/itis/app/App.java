package org.itis.app;

import org.itis.app.entity.LoginUser;
import org.itis.app.repository.LoginUserRepositoryImpl;
import org.itis.app.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try{
	    	LoginUserRepositoryImpl l=new LoginUserRepositoryImpl();
	    	LoginUser user = new LoginUser();
	    	user.setLogin("rishat");
	    	user.setPassword("12345");
	    	l.saveUser(user);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		HibernateUtil.shutdown();
    	}
    	
    }
}
