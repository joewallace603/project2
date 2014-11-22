package com.jpw.auth;

import javax.ws.rs.container.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.*;
import java.util.*;

import com.jpw.app.*;

public class UserAuthorizationFilter implements ContainerRequestFilter
{

	@Override
	public void filter( ContainerRequestContext containerRequest )
	{

		// TODO Auto-generated method stub
		System.out.println( "This is where we add user authoriazation!!!" );
		System.err.println( "This is where we add user authoriazation!!!" );

		String userRequestAuthorization = containerRequest.getHeaderString( "authorization" );

		if ( userRequestAuthorization == null )
		{
			System.out.println( "no auth data so throwing exception" );
			throw new WebApplicationException( Response.Status.UNAUTHORIZED );
		}

		UserCredentials userCredentials = decode( userRequestAuthorization );

		System.out.println( "got user creds" );

		if ( userCredentials.getUserId( ) == null|| 
			 userCredentials.getPassword( ) == null )
		{
			throw new WebApplicationException( Response.Status.UNAUTHORIZED );
		}

		UserAuthenticator userAuthenticator = new UserAuthenticator( );

		boolean authenticated = userAuthenticator
				.verifyCredentials( userCredentials );

		System.out.println( "about to authenticate creds" );

		if ( !authenticated )
		{
			throw new WebApplicationException( Response.Status.UNAUTHORIZED );
		}
		else
		{
			System.out.println( "creds authenticayted ok" );
		}

		// TODO Possibly add userId to request if necessary.

	
	}

	protected UserCredentials decode( String userRequestAuthorization )
	{
		// get rid of "basic " before the actual base 64 encode
		// "userId:password"
		userRequestAuthorization = userRequestAuthorization.replaceFirst(
				"[B|b]asic ", "");

		UserCredentials userCredentials = new UserCredentials( );

		byte[] decodedBytes = DatatypeConverter
				.parseBase64Binary( userRequestAuthorization );

		if (!( decodedBytes == null ) && !( decodedBytes.length == 0 ) )
		{
			String autorization = new String( decodedBytes );

			System.out.println( "the authorization header is:  " + autorization );

			StringTokenizer tokenizer = new StringTokenizer( autorization, ":" );

			String userId = tokenizer.nextToken( );
			String password = tokenizer.nextToken( );

			userCredentials.setUserId( userId );
			userCredentials.setPassword( password );

			System.out.println( "userId:  " + userId + ", password:  "
					+ password );
		}

		return userCredentials;
	}

}
