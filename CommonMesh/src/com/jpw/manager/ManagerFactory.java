package com.jpw.manager;

import com.jpw.interfaces.*;
import com.jpw.app.*;
import com.jpw.manager.*;

public class ManagerFactory 
{
	private static UserManager userManager = null;
	
	private static EventManager eventManager = null;
	
	private static FriendManager friendManager = null;
	
	public static UserManager getUserManager( )
	{
		
		return userManager == null? new UserManagerImpl( ) : userManager ;
	}
	
	public static EventManager getEventManager( )
	{
		return eventManager == null? new EventManagerImpl( ) : eventManager;
	}
	
	public static FriendManager getFriendManager( )
	{
		return friendManager == null? new FriendManagerImpl( ) : friendManager;
	}
}
