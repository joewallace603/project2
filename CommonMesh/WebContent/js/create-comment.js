function createComment( eventId )
{
	newComment = new Object( );
			
	newComment.comment = document.getElementById("commentText" + eventId ).value;
	newComment.userId = $.cookie( "username" );
	newComment.date = new Date( );
	newComment.eventId = eventId;
	
	document.getElementById( "commentText" + eventId ).value = '';
			
	/*
	var jsonString = JSON.stringify( newComment );
	alert( "Json string:  " + jsonString );
	*/
			
	jQuery.ajax ({
		url: "rest/comments/" ,
		type: "POST",
		data: JSON.stringify( newComment ),
		contentType: "application/json; charset=utf-8",
			   // success: function(){alert("comment sent");},
		success: function()
		{
			getComments( eventId );
		},
		error: function( jqXHR, textStatus, errorThrown ){ alert("Create User Failed:  " + textStatus + ", " + errorThrown );}
	});
}