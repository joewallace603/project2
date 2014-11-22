function refreshStatusIcons( eventId )
{
	var url = "rest/invitationstatus/" + eventId + "/" + $.cookie( "username" );

	$.getJSON(url,
	  { "_": $.now() },
	  	function( data, status )
	  	{ 
		     if( data.length > 0 )
		     {
				 var eventStatusTemplate = $('#eventStatusTemplate').html( );
				 var eventStatusHtml = Mustache.to_html( eventStatusTemplate, data[0].clientEventInvitationList );
				 $("#eventStatus").empty( );
				 $("#eventStatus").append( eventStatusHtml );
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
				// $("#image").css( "border-color", getUserEventStatusColor( data[0].userEventStatus ) );
				 document.getElementById( "image" ).className = getUserEventStatusColor( data[0].userEventStatus );
		     }
	  	});
}