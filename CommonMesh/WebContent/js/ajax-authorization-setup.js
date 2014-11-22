function ajaxAuthorizationHeaderSetup( )
{
	$.ajaxSetup(
	{
		headers: 
		{
			'Authorization': "Basic " + btoa($.cookie("username") + ":" + $.cookie("password") )
		}
	});
}