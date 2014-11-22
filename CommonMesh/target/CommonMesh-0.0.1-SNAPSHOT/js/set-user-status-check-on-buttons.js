function setUserStatusCheckOnButtons( userEventStatus )
{
	var classes = "ui-btn ui-icon-check ui-btn-icon-left";
	var removeClasses = "ui-icon-check ui-btn-icon-left";
	
	switch( userEventStatus )
	{
	
		case "ACCEPTED":
		{
			$("#goingButton").addClass( classes + " green" );
			
			$("#tentativeButton").removeClass( removeClasses + " green yellow gray" );
			$("#tentativeButton").css({ "background": "", "color": "white" });
			
			$("#declinedButton").removeClass( removeClasses + " green yellow gray" );
			$("#declinedButton").css({ "background": "", "color": "white" });
			break;
		}
		case "TENTATIVE":
		{
			
			$("#goingButton").removeClass( removeClasses + " green yellow gray" );
			$("#goingButton").css({ "background": "", "color": "white" });
			
			$("#tentativeButton").addClass( classes + " yellow" );
			
			$("#declinedButton").removeClass( removeClasses + " green yellow gray" );
			$("#declinedButton").css({ "background": "", "color": "white" });
			break;
		}
		case "DECLINED":
		{
			$("#goingButton").removeClass( removeClasses + " green yellow gray" );
			$("#goingButton").css({ "background": "", "color": "white" });
			
			$("#tentativeButton").removeClass( removeClasses + " green yellow gray" );
			$("#tentativeButton").css({ "background": "", "color": "white" });
			
			$("#declinedButton").addClass( classes + " gray" );
			
			break;
		}
		default:
		{
			$("#goingButton").removeClass( removeClasses + " green yellow gray" );
			$("#goingButton").css({ "background": "", "color": "white" });
			
			$("#tentativeButton").removeClass( removeClasses + " green yellow gray" );
			$("#tentativeButton").css({ "background": "", "color": "white" });
			
			$("#declinedButton").removeClass( removeClasses + " green yellow gray" );
			$("#declinedButton").css({ "background": "", "color": "white" });
			break;
		}
	}
}