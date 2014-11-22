function login( )
{
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	$.cookie("username", username, { path: '/'}); 
	$.cookie("password", password, { path: '/'}); 
	
	ajaxAuthorizationHeaderSetup( );
	
	//goToMonthView( );
	//goToWeekView( );
	
	//var eventViewURL = "m_event_view.html?eventid=76";
	//window.open( eventViewURL, "_self" );
	
	
	var url = "rest/admin/users/" + username;
	
	$.ajax ({
		url: url,
		type: "GET",
		success: function()
		{
			//goToMonthView( );
			alert( "going to week view" );
			goToWeekView( );
			
			/*
			var eventViewURL = "m_event_view.html?eventid=76";
			window.open( eventViewURL, "_self" );
			*/
		},
		error: function( jqXHR, textStatus, errorThrown )
		{ 
			alert("Verify User Failed:  " + textStatus + ", " + errorThrown );
			var errorMessage = document.getElementById( "errorMessage" );
			errorMessage.style.display = "block";
		}
	});
	
}