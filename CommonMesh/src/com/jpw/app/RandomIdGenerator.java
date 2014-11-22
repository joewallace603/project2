package com.jpw.app;

import java.util.*;

public class RandomIdGenerator
{
	private static final Random random;

	static
	{
		random = new Random( );
	}

	public static int getNewId( )
	{
		return random.nextInt( );
	}
}
