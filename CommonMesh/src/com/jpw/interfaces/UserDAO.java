package com.jpw.interfaces;

import com.jpw.app.*;
import com.jpw.dto.User;

import java.util.*;

public interface UserDAO 
{
	public User getUser( Integer userId );
	
	public Set<User> getAllUsers( );
	
	public boolean createUser( User user );
	
	public void modifyUser( User user );
	
	public void deleteUser( Integer userId );
	
	public boolean authenticateUser( UserCredentials userCredentials );
}
