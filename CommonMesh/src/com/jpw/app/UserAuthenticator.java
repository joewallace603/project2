package com.jpw.app;

import com.jpw.dao.DAOFactory;
import com.jpw.interfaces.UserDAO;

public class UserAuthenticator 
{
	public boolean verifyCredentials( UserCredentials userCredentials )
	{
		UserDAO userDAO = DAOFactory.getUserDAO( );
		
		boolean success = userDAO.authenticateUser( userCredentials );
		
		return success;
	}
}
