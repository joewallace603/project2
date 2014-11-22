function getContactsCheckBoxList( contactTemplateHtml, event )
{
	ajaxAuthorizationHeaderSetup( );
	    	
	var url = "rest/friends/" + $.cookie("username") ;
			
	$.getJSON(url, { "_": $.now() },
		function( data, status )
		{ 
			for( var i = 0; i < data.length; i++ )
			{
				var friendHtml = Mustache.to_html( contactTemplateHtml, data[i] );
				$( "#contactList" ).append( friendHtml );
			}
			
			var invitationList = event.clientEventInvitationList;

			var invitees = invitationList.hasNotResponded.concat( invitationList.going, invitationList.tentative, invitationList.declined );

			for( var i = 0; i < invitees.length; i++ )
			{
				var checkbox = document.getElementById( invitees[i].userId );
				checkbox.setAttribute( "checked", "checked" );
			}
		}
	);
}