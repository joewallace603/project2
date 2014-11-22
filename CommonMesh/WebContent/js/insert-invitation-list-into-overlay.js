function insertInvitationListIntoOverlay( selectedTabIndex )
{
	//alert( this.Attr( "id" ) );
	
	ajaxAuthorizationHeaderSetup( );
	
	// first get the event
	var url = "rest/invitationstatus/" + eventId + "/" + $.cookie( "username" );
	
	$.getJSON(url,
			{ "_": $.now() },
		  	function( data, status )
		  	{ 
		  	 	if( data.length == 1 )
		  	 	{
		  	 		$.get( "templates/event-invitation-status-template.html", 
		  	 		function( templates )
		  	 		{
		  	 			var eventInvitationStatusTemplate = $( templates ).filter( "#eventInvitationStatusTemplate" ).html( );
		  	 			var inviteeTemplate = $( templates ).filter( "#inviteeTemplate" ).html( );
		  	 			
		  	 			var invitationList = data[0].clientEventInvitationList;
		  	 			
		  	 			var eventInvitationStatusHtml = Mustache.to_html( eventInvitationStatusTemplate, invitationList );
		  	 			
		  	 			$("#overlay").empty( );
		  	 			$("#overlay").append( eventInvitationStatusHtml );
		  	 			
		  	 			var goingListHtml = "";
		  	 			var tentativeListHtml = "";
		  	 			var declinedListHtml = "";
		  	 			var noResponseListHtml = "";
		  	 			
		  	 			for( var i = 0; i < invitationList.going.length; i++ )
		  	 			{
		  	 				goingListHtml += Mustache.to_html( inviteeTemplate, invitationList.going[i] );
		  	 			}
		  	 			
		  	 			for( var i = 0; i < invitationList.tentative.length; i++ )
		  	 			{
		  	 				tentativeListHtml += Mustache.to_html( inviteeTemplate, invitationList.tentative[i] );
		  	 			}
		  	 			
		  	 			for( var i = 0; i < invitationList.declined.length; i++ )
		  	 			{
		  	 				declinedListHtml += Mustache.to_html( inviteeTemplate, invitationList.declined[i] );
		  	 			}
		  	 			
		  	 			for( var i = 0; i < invitationList.hasNotResponded.length; i++ )
		  	 			{
		  	 				noResponseListHtml += Mustache.to_html( inviteeTemplate, invitationList.hasNotResponded[i] );
		  	 			}
		  	 			
		  	 			$( "#acceptedList" ).append( goingListHtml );
		  	 			$( "#tentativeList" ).append( tentativeListHtml );
		  	 			$( "#declinedList" ).append( declinedListHtml );
		  	 			$( "#noResponseList" ).append( noResponseListHtml );
		  	 			
		  	 			$("#tabs").tabs( { event: "mouseover", active: selectedTabIndex } );
		  	 			
		  	 			$( "#closeLink" ).click( function( )
		  	 			{ 
		  	 				document.getElementById( 'overlay' ).style.display='none'; 
				  			document.getElementById( 'fade').style.display='none';
		  	 			});
		  	 			
		  	 			document.getElementById( 'overlay' ).style.display='block'; 
			  			document.getElementById( 'fade').style.display='block';
		  	 			
		  	 		});
		  	 		
		  	 		
		  	 	}
			}
		);
	}