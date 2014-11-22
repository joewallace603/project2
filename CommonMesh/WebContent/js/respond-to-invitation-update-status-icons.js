function respondToInvitationRefreshStatusIcons( eventId )
{
	document.getElementById( "dateTimeAlert" ).style.display = "none";
	document.getElementById( "locationAlert" ).style.display = "none";
	
	var response = "DECLINED";
	
	if( !$.browser.mobile )
	{
		if( $(this).hasClass( "goingButton" ) )
		{
			response = "GOING";
		}
		else if( $(this).hasClass( "tentativeButton" ) )
		{
			response = "TENTATIVE";
		}
	}
	else
	{
		var radioButtons = document.getElementsByName( "response" );
	
		for( var i = 0; i < radioButtons.length; i++ )
		{
			if( radioButtons[i].checked )
			{
				response = radioButtons[i].value;
			}
		}
	}
	
	var url = "rest/invitationstatus/" + $.cookie("username") +
    "/"+ eventId + "/" + response;
	
	
	$.ajax ({
	    url: url ,
	    type: "PUT",
	    contentType: "application/json; charset=utf-8",
	    success: function()
	    {
	    	refreshStatusIcons( eventId );
	    },
	    error: function( jqXHR, textStatus, errorThrown ){ alert("Response Failed:  " + textStatus + ", " + errorThrown );}
	    });
	
	//TODO   update just the status portion of the view.
	
}