function checkForCommentSubmit( event )
{
	if( event.keyCode == 13)
	{
		$("#commentForm").submit( function(){});
		//$("#commentInput").val('').placeholer();
	}
}