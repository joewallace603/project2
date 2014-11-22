function shiftMonth( increment )
{
	var timeRangeMarker = $.cookie("timeRangeMarker" );

	if( timeRangeMarker == null )
	{
		alert("Error:  No existing Time Range Marker" );
	}
	else
	{
	    timeRangeDate = new Date(  );
	    timeRangeDate.setTime( timeRangeMarker );
	    timeRangeDate.setDate( 15 );
	    var month = timeRangeDate.getMonth( );
	    var year = timeRangeDate.getFullYear( );
	    
	    if( increment )
	    {
	    	if( month == 11 )
	    	{
	    		month = 0;
	    		year++;
	    	}
	    	else
	    	{
	    		month++;
	    	}
	    }
	    else
	    {
	    	if( month == 0 )
	    	{
	    		month = 11;
	    		year--;
	    	}
	    	else
	    	{
	    		month--;
	    	}
	    }
	    
	    timeRangeDate.setMonth( month );
		timeRangeDate.setYear( year );
    
    	var newTimeRangeMarker = timeRangeDate.getTime( );
    	$.cookie( "timeRangeMarker", newTimeRangeMarker );
    	
    	//location.reload( true );
    	getEventsForDisplayMonth( newTimeRangeMarker );
	}

}