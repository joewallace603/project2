function insertEditEventFormIntoOverlay( eventId )
{
	ajaxAuthorizationHeaderSetup( );
	
	// first get the event
	var url = "rest/events/" + eventId + "/" + $.cookie( "username" );
	
	$.getJSON(url,
		{ "_": $.now() },
	  	function( data, status )
	  	{ 
	  	 	if( data.length > 0 )
	  	 	{
	  	 		data[0].date = new Date( data[0].eventDate );
	  	 		
	  	 		$.get( "templates/edit-event-form-template.html", 
	  	 		function( templates )
	  	 		{
	  	 			var editEventFormTemplate = $( templates ).filter( "#editEventFormTemplate" ).html( );
	  	 			var contactTemplate = $( templates ).filter( "#contactTemplate" ).html( );
	  	 			
	  	 			var editEventFormHtml = Mustache.to_html( editEventFormTemplate, data[0] );
	  	 			
	  	 			$("#overlay").empty( );
	  	 			$("#overlay").append( editEventFormHtml );
	  	 			
	  	 			getContactsCheckBoxList( contactTemplate, data[0] );
	  	 			
	  	 			/*
	  	 			
	  	 			var invitees = data[0].usersNotResponded.concat( data[0].usersGoing, data[0].usersTentative, data[0].usersDeclined );
	  	 			
	  	 			for( var i = 0; i < invitees.length; i++ )
	  	 			{
	  	 				var checkbox = document.getElementById( invitees[i] );
						checkbox.setAttribute( "checked", "checked" );
	  	 			}
	  	 			*/
	  	 			
	  	 			$('#datetimepicker').datetimepicker( {timepicker:true} );
	  	 			
	  	 			document.getElementById( 'overlay' ).style.display='block'; 
		  			document.getElementById( 'fade').style.display='block';
	  	 			
	  	 		});
	  	 		
	  	 		
	  	 	}
		}
	);
}