	function getEventsForDisplayMonth( )
	{
		var timeRangeMarker = $.cookie("timeRangeMarker" );
		//alert( "eventMonth.html username = " + $.cookie("username" ) + ", password = " + $.cookie( "password"));

		if( timeRangeMarker == null )
		{
		    var today = new Date( );
		    timeRangeMarker = today.getTime( );
		    $.cookie( "timeRangeMarker", timeRangeMarker, {path: '/'} );
		}
		
		var daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		var date = new Date(  );
		date.setTime( timeRangeMarker );
		
		date.setDate( 1 );
		var daysOfPreviousMonthNeeded = date.getDay( );
		
		// 12am first day of month
		date.setHours(0, 0, 0, 0);
		
		var millisInDay = 1000 * 60 * 60 *24;
		
		var startTime = date.getTime( ) - millisInDay * daysOfPreviousMonthNeeded;
		
		// 12am last day of the month
		date.setDate( daysInMonth[date.getMonth( )] );
		var daysOfNextMonthNeeded = 7 - date.getDay( );
		
		var stopTime = date.getTime( ) + millisInDay * daysOfNextMonthNeeded;
		
		/* This does not yet handle the case where 6 display weeks are needed to show the
		 * entire month
		 */
		
		var url = "rest/events/" + $.cookie("username") +
		          "/"+ startTime +"/" + stopTime;
		
		$.getJSON(url,
				  { "_": $.now() },
				  function( data, status )
				  { 
				  	generateDaysOfMonth( data, timeRangeMarker );
				  });
	}


	function generateDaysOfMonth( events, timeRangeMarker )	
	{	
		/*
		if( events == null )
		{
			alert( "events = null" );
		}
		else
		{ 
			alert("event length = " + events.length );
		}
		*/
		
		
		// For now assuming events are in chronological order.  Server should guarantee that.
		var eventListIndex = 0;
		var today = new Date( );
		
		//today.setMonth(9);
		//today.setDate( 30 );
		
		var timeRangeDate = new Date( );
		timeRangeDate.setTime( timeRangeMarker );
		var monthOfYear = timeRangeDate.getMonth();
		var year = timeRangeDate.getUTCFullYear( );
		timeRangeDate.setDate( 1 );
		var firstDayOfMonth = timeRangeDate.getDay( );

		var daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		var month = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

		var data = new Object( );
		data.monthName = month[monthOfYear];
		data.year = year;

		var template = $('#monthYearTemplate').html();

		var myhtml = Mustache.to_html(template, data);
		
		$( "#calheader" ).remove( );

		$( "#calcontainer").before( myhtml );	
		
		
		// What's the first day to show in location 1-1 of the calendar?

		var displayDate = 1;
		var offset = firstDayOfMonth - 1;
		var nextMonth = false;
		var nextMonthDate = 1;

		var dayTemplate = $('#dayOfMonthTemplate').html();  // day
		
		var dayClass = "day";
		var lastDayOfWeekClass = "day lastDayOfWeek";
		var todayClass = "day today";
		var todayLastDayOfWeekClass = "day today lastDayOfWeek";
		var dayOfOtherMonthClass = "day otherMonth";
		var lastDayOfWeekOtherMonthClass = "day lastDayOfWeek otherMonth";

		var output = "<div class=\"week\">";
		var isToday = false;
		
		var previousMonth = false;
		var currentMonth = monthOfYear;
		
		
		for (var i = 0; i < 35; i++) 
		{
			if (i < firstDayOfMonth) 
			{
				previousMonth = true;
				
				if( monthOfYear == 0 )
				{
					currentMonth = 11;
				}
				else
				{
					currentMonth = monthOfYear - 1;
				}
				
				var x = firstDayOfMonth - i;
				displayDate = daysInMonth[currentMonth] - (x - 1);
			}
			else if( nextMonth )
			{
				if( monthOfYear == 11 )
				{
					currentMonth = 0;
				}
				else
				{
					currentMonth = monthOfYear + 1;
				}
				
				displayDate = nextMonthDate++;
			}
			else
			{
				currentMonth = monthOfYear;
				previousMonth = false;
				displayDate = i - offset;

				
			}
			
			if( displayDate == today.getDate( )  &&
					currentMonth == today.getMonth( ) &&
					year == today.getFullYear( ) )
			{
				isToday = true;
			}

			var data = new Object( );
			data.displayDate = displayDate;
			var firsttime = true;
			
			/*<ul>
			 * 	<li style="background-image:url(../img/{{imageName}})">{{eventUserStatus}}</li>
			 * 	<li ......
			 * </ul>
			 */
			
			/* <ul>
			 *   <li class="yellow l2 a9"><p>09:00 History: examination</p></li>
		     *   <li class="green l2 a9"><p>19:00 Camp meeting</p></li>
			 *</ul>
			 */
			
			var eventListHtml = null;
			var eventListOpenHtml = null;
			var mayHaveEvent =  true;
			var hourOfEndOfPreviousEvent = 0;
			
			
			while( eventListIndex < events.length && mayHaveEvent )
			{
				var event = events[eventListIndex];
				var date = new Date( event.eventDate );
				
				if(date.getMonth( ) == currentMonth && date.getDate( ) == displayDate )
				{
					//alert( "found an event for display date = " + displayDate + ", date = " + date.getTime( ) + ", hours = " + date.getHours( ) + ", minutes = " + date.getMinutes( ) + ", date of month =" + date.getDate( ));
					if( firsttime )
					{
						firsttime = false;
						eventListHtml = "<ul class=\"eventOrderedList\">";
						eventListOpenHtml = "<ul>";
					}
					
//					eventListHtml += "<li id=\"" + event.eventId + "\" class=\"" + "blue" + "\" style=\"background-image: url('img/" + event.eventImageName + "')\"></li>";
//					eventListOpenHtml += "<li class=\"" + "blue l2 a9" + "\" ><p>" + event.title + " @ " + date.toLocaleTimeString( ) + "</P></li>";
			
					if( hourOfEndOfPreviousEvent > 0 )
					{
						hoursBetweenEvents = date.getHours( ) - hourOfEndOfPreviousEvent;
					}
					else
					{
						hoursBetweenEvents = date.getHours( );
					}
					
					hourOfEndOfPreviousEvent = date.getHours( ) + event.eventDuration;
					
				    var iconTitle = event.title + " on " + date.toLocaleString( ) + " at " + event.location + "." ;
					
					//TODO use a template here
					eventListHtml += "<li id=\"" + event.eventId + "\" class=\"" + getUserEventStatusColor( event.userEventStatus ) + 
					"\" style=\"border-radius: 5px; background-image: url('img/" + event.eventImageName + "')\" " + "title=\"" + iconTitle + "\"></li>";
					eventListOpenHtml += "<li class=\"" + "blue" + " l" +  event.eventDuraton +  " a" + hoursBetweenEvents + "\" ><p>" + event.title + " @ " + date.toLocaleTimeString( ) + "</P></li>";
					
					
				//	alert( "the event html:  " + eventListHtml );
					eventListIndex++;
				}
				else
				{
					//Since events are in chronological order, if we reach one that 
					//we're not interested in we're done after closing out the list element
					
					mayHaveEvent = false;
					
					if( !firsttime )
					{
						eventListHtml += "</ul>";
						eventListOpenHtml += "</ul>";
					}
					
					break;
				}
			}
			
			data.eventListHtml = eventListHtml;
			data.eventListOpenHtml = eventListOpenHtml;
			
			var daysOutput;
			
			if( ( i + 1 ) % 7 == 0 )
			{
				// last day of week.
				if( isToday )
				{
					//daysOutput = Mustache.to_html(todayLastDayOfWeekTemplate, data);
					data.classes = todayLastDayOfWeekClass;
				}
				else if( nextMonth )
				{
					//daysOutput = Mustache.to_html(lastDayOfWeekOtherMonthTemplate, data );
					data.classes = lastDayOfWeekOtherMonthClass;
				}
				else
				{
					//daysOutput = Mustache.to_html(lastDayOfWeekTemplate, data);
					data.classes = lastDayOfWeekClass;
				}
				
				daysOutput = Mustache.to_html(dayTemplate, data);

				if( i != 34 )
				{
					daysOutput += "</div> <div class=\"week\">";
				}
				else
				{
					daysOutput += "</div>";
				}
			}
			else
			{
				if( isToday )
				{
					//daysOutput = Mustache.to_html( todayTemplate, data);
					data.classes = todayClass;
				}
				else if( previousMonth || nextMonth )
				{
					//daysOutput = Mustache.to_html( dayOfOtherMonthTemplate, data );
					data.classes = dayOfOtherMonthClass;
				}
				else
				{
					//daysOutput = Mustache.to_html( dayTemplate, data);
					data.classes = dayClass;
				}
				
				daysOutput = Mustache.to_html(dayTemplate, data);
			}

			output += daysOutput;
			
			if( isToday )
			{
				isToday = false;
			}
			
			if( !previousMonth && displayDate == daysInMonth[monthOfYear] )
			{
				nextMonth = true;
			}
		}
		
		$( "#daysmonth" ).empty( );
		
		$( "#daysmonth" ).append( output );
		
		/*
		
		for( var i = 0; i < events.length; i++ )
		{
			$("#" + events[0].eventId ).css( {"background-image": "url('img/" + events[0].eventImageName + "')\"" ,
				                                )
			}
		}
		*/

		//initMenu( );
		$('.open').hide();
		
		$( ".eventImageList" ).each( function( index, eventImageList )
				{
					eventImageList.addEventListener( 'click', function( )
					{
						//goToCreateEventView( );
						insertCreateEventFormIntoOverlay( );
					});
				});
		
		$(".eventOrderedList").each( function( index, eventOrderedList )
		{
			var eventListItems = eventOrderedList.getElementsByTagName('li');
							
			for( var i = 0; i < eventListItems.length; i++)
			{
				eventListItems[i].addEventListener( 'click', function( event )
				{
					event.stopPropagation( );
					
					var eventViewURL = "event_view.html?eventid=" + 
					                   this.getAttribute( "id" ) ;
					
					window.open( eventViewURL, "_self" );
				});
			}
		});
		
		
	}
