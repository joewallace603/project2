package com.jpw.app;

public enum UserInvitationStatus
{
	NO_RESPONSE, DECLINED, TENTATIVE, ACCEPTED, DATE_TIME_LOCATION_CHANGE, DATE_TIME_CHANGE, LOCATION_CHANGE;
	
	@Override
	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		
		switch( this )
		{
			case NO_RESPONSE:
				buf.append( "NO_RESPONSE" );
				break;
			case DECLINED:
				buf.append( "DECLINED" );
				break;
			case TENTATIVE:
				buf.append( "TENTATIVE" );
				break;
			case ACCEPTED:
				buf.append( "ACCEPTED" );
				break;
			case DATE_TIME_LOCATION_CHANGE:
				buf.append( "DATE_TIME_LOCATION_CHANGE");
				break;
			case DATE_TIME_CHANGE:
				buf.append( "DATE_TIME_CHANGE");
				break;
			case LOCATION_CHANGE:
				buf.append( "LOCATION_CHANGE" );
				break;
			default:
				buf.append( "UNDEFINED" );
		}
		
		return buf.toString( );
	}
};
