
	function getEventsForWeek( )
	{
		//alert( "in get events for week" );
		var weekMarker = $.cookie("weekMarker" );

		if( weekMarker == null )
		{
		    var today = new Date( );
		    weekMarker = today.getTime( );
		    $.cookie( "weekMarker", weekMarker, {path: '/'} );
		}
		
		var date = new Date( );
		date.setTime( weekMarker );
		
		alert( "weekMarker = " + weekMarker );
		
		var dayOfWeek = date.getDay( );
		
		var millisInDay = 1000 * 60 * 60 *24;
		
		date.setHours( 0, 0, 0, 0 );
		
		var startTime = date.getTime( ) - dayOfWeek * millisInDay;
		
		var stopTime = date.getTime( ) + (7 - dayOfWeek ) * millisInDay;
		
		var url = "rest/events/" + $.cookie("username") +
		          "/"+ startTime +"/" + stopTime;
		
		//alert( "about to make event request" );
		
		$.getJSON(url,
				  { "_": $.now() },
				  function( data, status )
				  { 
					  alert( "got events");
				  	insertEventsIntoWeekView( data, weekMarker );
				  });

	}
	
	function insertEventsIntoWeekView( events, weekMarker )
	{
		alert( "in insert events...  weekMarker = " + weekMarker );
		alert( "We got " + events.length + " events! for week of " + new Date( weekMarker ) );
	}