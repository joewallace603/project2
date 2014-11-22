package com.jpw.interfaces;

import java.util.*;
import com.jpw.dto.*;
import com.jpw.client.*;

public interface FriendManager
{
	List<ClientContact> getUsers( String user );
}
