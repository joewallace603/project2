function updateEvent( )
{
	var updatedEvent = new Object( );

	var contactArray = $.map( $( "input:checkbox:checked" ), function( checkbox ,i ) 
			{
				return checkbox.value;
			});

	console.log( contactArray );
	updatedEvent.invitedContacts = contactArray;

	var forms = document.getElementsByTagName( "form" );
	updatedEvent.eventId = forms[0].id;

	updatedEvent.eventCreateDate = new Date( );
	updatedEvent.title = document.getElementById("title").value;
	updatedEvent.eventImageName = document.getElementById("imageName").value;
	var eventDate = new Date( document.getElementById( "datetimepicker").value );
	updatedEvent.eventDate = eventDate.getTime( );
	var duration = document.getElementById("duration");
	updatedEvent.eventDuration = duration.options[duration.selectedIndex].value;
	updatedEvent.location = document.getElementById("location").value;
	updatedEvent.description = document.getElementById("desc").value;
	updatedEvent.creatorId = $.cookie("username");

	jQuery.ajax ({
		url: "rest/events",
		type: "PUT",
		data: JSON.stringify( updatedEvent ),
		contentType: "application/json; charset=utf-8",
		success: function( )
			{ 
				history.go( 0 ); 
//				document.getElementById('overlay').style.display='none';
//				document.getElementById('fade').style.display='none';
			},
		error: function( response, textStatus, errorStatus ){ alert("Update Event Failed:  " + errorStatus + " - " + response.responseText );}
	});
}