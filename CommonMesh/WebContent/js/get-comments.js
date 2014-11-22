
		function getComments( eventId )
		{
			var url = "rest/comments/" + eventId;

			$.getJSON( url,
	 		{ "_": $.now() },
			function(data, status )
			{
				var template = $('#commentTemplate').html();
				
				$( "#comments").empty( );
				
				var commentsHtml = "";
				
				for( var i =0; i < data.length; i++)
				{
					/*
					if( i % 2 == 0)
					{
						data[i].class = "even";
					}
					else
					{
						data[i].class = "odd";
					}
					*/
					data[i].time = jQuery.timeago( data[i].date );
					commentsHtml += Mustache.to_html(template, data[i]);
			        
			    }
				
				$( "#comments" ).append( commentsHtml );
				
				var commentsArea = document.getElementById( "comments" );
				commentsArea.scrollTop = commentsArea.scrollHeight;
			    
			});
		};