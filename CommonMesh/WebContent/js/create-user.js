function createUser( )
		{
			insertDummyAuthorization( );
			
			newUser = new Object();
			newUser.userId = document.getElementById("newUsername").value;
			newUser.password = document.getElementById("newPassword").value;
			newUser.firstName = document.getElementById("firstName").value;
			newUser.lastName = document.getElementById("lastName").value;
			newUser.userImageName = document.getElementById("userImageName").value;
			newUser.isMale = document.getElementById("male").checked;
			newUser.dateOfBirth = $("#birthDatePicker").datepicker( 'getDate' );
			newUser.zipCode = document.getElementById("zipCode").value;
			
			jQuery.ajax ({
			    url: "rest/admin/users",
			    type: "POST",
			    data: JSON.stringify( newUser ),
			    contentType: "application/json",
			    success: function( )
			    { 
			    	$.cookie("username", document.getElementById("newUsername").value, { path: '/'}); 
			    	$.cookie("password", document.getElementById("newPassword").value, { path: '/'}); 
			    	goToMonthView( );
			    },
			    error: function( textStatus, errorThrown )
			    { 
			    	alert("Create User Failed:  " + textStatus + ", " + errorThrown );
			    }
			});
		}

function insertDummyAuthorization( )
{
	//TODO This is a huge security hole!!!!  Address with Jersey Filter for authorization.  
	//Create user should be the only post not requiring username and password.
	$.ajaxSetup(
	{
		headers: 
		{
			'Authorization': "Basic " + btoa("dummyName" + ":" + "dummyPassword" )
		}
	});
}