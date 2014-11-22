function handleTimeLocationChange( userEventStatus )
{
	if( userEventStatus == "DATE_TIME_LOCATION_CHANGE" )
	{
		document.getElementById( "dateTimeAlert" ).style.display = "block";
		document.getElementById( "locationAlert" ).style.display = "block";
	}
	else if( userEventStatus == "DATE_TIME_CHANGE" )
	{
		document.getElementById( "dateTimeAlert" ).style.display = "block";
	}
	else if( userEventStatus == "LOCATION_CHANGE" )
	{
		document.getElementById( "locationAlert" ).style.display = "block";
	}
}