function getEventById( url )
{
	$.getJSON(url,
			  { "_": $.now() },
			  function( data, status )
			  { 
			  	 if( data.length > 0 )
			  	 {
				     eventId = data[0].eventId;
				     
				     if( !$.browser.mobile && $.cookie("username") == data[0].creatorId )
				     {
// need to handle mobile here				    	
				    	 //document.getElementById( "edit" ).style.display = "block";
				     }
					 
					 var date = new Date( data[0].eventDate );
					 data[0].displayDate = date.toLocaleString( );
					 data[0].dayOfWeek = getDayOfWeek( date );
					 data[0].displayCreateDate = new Date( data[0].eventCreateDate ).toLocaleString( );
					 
					 var userEventStatus = data[0].userEventStatus;
					 data[0].color = getUserEventStatusColor( userEventStatus );
					 
					 
					 if( !$.browser.mobile )
					 {
						 $.get( "templates/m-event-details-template.html", 
						  	 		function( templates )
						 {
							 setUserStatusCheckOnButtons( userEventStatus );
							 
							 $("#title").empty( );
							 $("#title").append( data[0].title );

							 var mEventDetailsTemplate = $( templates ).filter( "#mEventDetailsTemplate" ).html( );

							 var eventDetailsHtml = Mustache.to_html( mEventDetailsTemplate, data[0] );

							 $("#eventDetail").empty( );
							 $("#eventDetail").append( eventDetailsHtml);
						 });
						  	 			
					 }
					 else
					 {
//						 Do desktop here
						 $( "#eventView" ).empty( );
						 var eventViewTemplate = $('#eventViewTemplate').html( );
						 var eventStatusTemplate = $('#eventStatusTemplate').html( );
						 var commentTemplate = $("#commentTemplate" ).html( );
						 var eventViewHtml = Mustache.to_html( eventViewTemplate, data[0] );
						 $("#eventView").append( eventViewHtml );

						 handleTimeLocationChange( userEventStatus );

						 var eventStatusHtml = Mustache.to_html( eventStatusTemplate, data[0].clientEventInvitationList );
						 $("#eventStatus").append( eventStatusHtml );

//						 if( userEventStatus == "DATE_TIME_LOCATION_CHANGE" )
//						 {
//						 document.getElementById( "dateTimeAlert" ).style.display = "block";
//						 document.getElementById( "locationAlert" ).style.display = "block";
//						 }
//						 else if( userEventStatus == "DATE_TIME_CHANGE" )
//						 {
//						 document.getElementById( "dateTimeAlert" ).style.display = "block";
//						 }
//						 else if( userEventStatus == "LOCATION_CHANGE" )
//						 {
//						 document.getElementById( "locationAlert" ).style.display = "block";
//						 }

						 //$("#image").css( "border-color", userEventStatusColor );

						 // alert("about to get comments" );

						 var eventComments = data[0].clientEventComments;

						 //alert( "the are " + eventComments.length + " comments." );
						 var commentsHtml = "";

						 for( var i = 0; i < eventComments.length; i++ )
						 {
							 //alert( "in loop" );
							 eventComments[i].time = jQuery.timeago( eventComments[i].date );
							 commentsHtml += Mustache.to_html( commentTemplate, eventComments[i] );
							 //alert( "comments = " + commentsHtml );
							 //alert("processing comment" );
						 }

						 if( eventComments.length > 0 )
						 {

							 //alert("about to append" );
							 $( "#comments" ).append( commentsHtml );

							 var commentsArea = document.getElementById( "comments" );
							 commentsArea.scrollTop = commentsArea.scrollHeight;
							 //alert( "append done" );
						 }
					 }

					 $( "#close" ).click( function( )
							 {
						 //TODO this dosen't work when navigating event to event
						 //history.go( -1 );
						 goToMonthView( );
							 });

					 $( ".invitation" ).click( function( )
							 {
						 var selectedTab = $(this).attr( 'id' );

						 var tabIndex = 0;

						 switch( selectedTab )
						 {
						 case "going":
							 tabIndex = 0;
							 break;

						 case "tentative":
							 tabIndex = 1;
							 break;

						 case "declined":
							 tabIndex = 2;
							 break;

						 case "notResponded":
							 tabIndex = 3;
							 break;

						 default:
							 tabIndex = 0;
						 break;
						 }
						 insertInvitationListIntoOverlay( tabIndex );
							 });
			  	 }
		  }
	);
}