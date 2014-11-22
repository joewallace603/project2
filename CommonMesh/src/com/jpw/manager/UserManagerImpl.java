package com.jpw.manager;

import com.jpw.interfaces.*;
import com.jpw.dao.*;
import com.jpw.dto.User;

import java.sql.*;

public class UserManagerImpl implements UserManager
{

	@Override
	public boolean createUser( User user )
	{
		Date createDate = new Date( new java.util.Date( ).getTime( ) );

		user.setCreateDate( createDate );

		UserDAO userDAO = DAOFactory.getUserDAO( );
		System.out.println( "UserManagerImpl:  user = " + user );

		boolean success = userDAO.createUser( user );

		return success;
	}

}
