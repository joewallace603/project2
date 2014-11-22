function getURLParamValue( paramName )
{
	var pageURL = window.location.search.substring(1);
	
	var paramValue = "";
	
	var urlParams = pageURL.split( '&' );
	
	for( var i = 0; i < urlParams.length; i++ )
	{
		var paramNameValue = urlParams[i].split( '=' );
		
		if( paramNameValue[0] == paramName )
		{
			paramValue = paramNameValue[1];
		}
	}
	
	return paramValue;
}