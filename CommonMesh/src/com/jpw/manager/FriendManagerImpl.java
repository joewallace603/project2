package com.jpw.manager;

import java.util.*;

import com.jpw.dao.DAOFactory;
import com.jpw.interfaces.*;
import com.jpw.client.*;
import com.jpw.dto.*;

public class FriendManagerImpl implements FriendManager
{

	@Override
	public List<ClientContact> getUsers( String userId )
	{
		FriendDAO friendDAO = DAOFactory.getFriendDAO( );
		
		List<User> users = friendDAO.getUsers( userId );
		
		List<ClientContact> clientContacts = new ArrayList<ClientContact>( );
		
		for( User user : users )
		{
			ClientContact clientContact = new ClientContact( user );
			clientContacts.add( clientContact );
		}
		
		return clientContacts;
	}

}
