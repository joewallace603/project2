function getUserEventStatusColor( userEventStatus )
{
	var userEventStatusColor;
	
	
	switch( userEventStatus )
	{
	 	case "ACCEPTED":
	 	{
	 		userEventStatusColor = "green";
	 		break;
	 	}
	 	case "TENTATIVE":
	 	{
	 		userEventStatusColor = "yellow";
	 		break;
	 	}
	 	case "DECLINED":
	 	{
	 		userEventStatusColor = "gray";
	 		break;
	 	}
	 	case "NOT_RESPONDED":
	 	{
	 		userEventStatusColor = "blue";
	 		break;
	 	}
	 	case "DATE_TIME_LOCATION_CHANGE":
	 	case "DATE_TIME_CHANGE":
	 	case "LOCATION_CHANGE":
	 	{
	 		userEventStatusColor = "red";
	 		break;
	 	}
	 	default:
	 	{
	 		userEventStatusColor = "blue";
	 	}
	}

	return userEventStatusColor;
}

