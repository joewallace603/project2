function createEvent( )
{
	newEvent = new Object( );

	var contactArray = $.map( $( "input:checkbox:checked" ), function( checkbox ,i ) 
			{
				return checkbox.value;
			});

	console.log( contactArray );
	newEvent.invitedContacts = contactArray;

	newEvent.eventCreateDate = new Date( );
	newEvent.title = document.getElementById("title").value;
	newEvent.eventImageName = document.getElementById("imageName").value;
	var eventDate = new Date( document.getElementById( "datetimepicker").value );
	newEvent.eventDate = eventDate.getTime( );
	var duration = document.getElementById("duration");
	newEvent.eventDuration = duration.options[duration.selectedIndex].value;
	newEvent.location = document.getElementById("location").value;
	newEvent.description = document.getElementById("desc").value;
	newEvent.creatorId = $.cookie("username");

	jQuery.ajax ({
		url: "rest/events",
		type: "POST",
		data: JSON.stringify( newEvent ),
		contentType: "application/json; charset=utf-8",
		success: function( )
			{ 
				//goToMonthView( ); 
				history.go( 0 );
//				document.getElementById('overlay').style.display='none';
//				document.getElementById('fade').style.display='none';
			},
		error: function( jqXHR, textStatus, errorThrown )
			{ 
				alert("Create Event Failed:  " + textStatus + ", " + errorThrown );
			}
	});
}