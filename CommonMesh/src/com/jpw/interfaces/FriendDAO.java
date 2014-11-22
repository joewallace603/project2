package com.jpw.interfaces;

import java.util.*;

import com.jpw.dto.*;

public interface FriendDAO
{
	public List<User> getUsers( String userId );
}
