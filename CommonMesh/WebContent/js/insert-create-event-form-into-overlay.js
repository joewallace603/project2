function insertCreateEventFormIntoOverlay( )
{
	var contactTemplate ="";
	
	$.get( "templates/create-event-form-template.html", 
  	 		function( templates )
  	 		{
  	 			var createEventFormHtml = $( templates ).filter( "#createEventFormTemplate" ).html( );
  	 			contactTemplate = $( templates ).filter( "#contactTemplate" ).html( );
  	 		//	closeOverlayHtml = $( templates ).filter( "#closeOverlayTemplate" ).html( );
  	 			
  	 			$("#overlay").empty( );
  	 			//$("#overlay").append( closeOverlayHtml );
  	 			$("#overlay").append( createEventFormHtml );
  	 			
  	 			$('#datetimepicker').datetimepicker( {timepicker:true} );
  	 		   // $('#datetimepicker').appendDtpicker({'current' : '2014-03-01 10:00'});
  	 		});
	
	ajaxAuthorizationHeaderSetup( );
	
	var url = "rest/friends/" + $.cookie("username") ;

	$.getJSON(url, { "_": $.now() },
		 		function( data, status )
		  		{ 
		  			for( var i = 0; i < data.length; i++ )
		  			{
		  				var contactHtml = Mustache.to_html( contactTemplate, data[i] );
		  				$( "#contactList" ).append( contactHtml );
		  			}
		  			
		  			var checkbox = document.getElementById( $.cookie("username") );
					checkbox.setAttribute( "checked", "checked" );
		  			
		  			document.getElementById( 'overlay' ).style.display='block'; 
		  			document.getElementById( 'fade').style.display='block';
				}
	);
}

