package com.jpw.app;

import java.util.*;

import com.jpw.dto.EventComment;
import com.jpw.dto.User;

public class MemoryPersistenceService 
{
	private final static List<EventComment> emptyEventIdToCommentList = new ArrayList<EventComment>(0);
	private final static Map<Integer, List<EventComment>> eventIdToCommentMap = new HashMap<Integer, List<EventComment>>( );
	
	private final static List<Integer> emptyUserIdToEventIdsList = new ArrayList<Integer>(0);
	private final static Map<String, List<Integer>> userIdToEventIdsMap = new HashMap<String, List<Integer>>( );
	
	private final static Map<String,User> userIdToUserMap = new HashMap<String,User>();
	
	private final static Map<Integer,EventTemp> eventIdToEventMap = new HashMap<Integer,EventTemp>();
	
	private final static List<String> emptyUserIdToFriendIdList = new ArrayList<String>(0);
	private final static Map<String,List<String>> userIdToFriendIdMap = new HashMap<String,List<String>>();
	
	
	public static void addEvent( EventTemp event )
	{
		eventIdToEventMap.put( event.getEventId(), event );
	}
	
	public static EventTemp getEvent( Integer eventId )
	{
		return eventIdToEventMap.get( eventId );
	}
	
	public static void addComment( Integer eventId, EventComment eventComment )
	{
		if( eventIdToCommentMap.containsKey(eventId) )
		{
			List<EventComment> commentList = eventIdToCommentMap.get(eventId);
			commentList.add(eventComment);
			System.out.println("add comment returning list of " + commentList.size() + " comments.");
		}
		else
		{
			System.out.println( "add comment creating first comment for eventId = " + eventId );
			List<EventComment> commentList = new ArrayList<EventComment>();
			commentList.add(eventComment);
			eventIdToCommentMap.put(eventId, commentList);
		}
		
		System.out.println( "CommentMap:\n" + eventIdToCommentMap.toString());
	}
	
	public static List<EventComment> getComments( Integer eventId )
	{
		List<EventComment> commentList = emptyEventIdToCommentList;
		
		if( eventIdToCommentMap.containsKey(eventId) )
		{
			commentList = eventIdToCommentMap.get(eventId);
		}
		else
		{
			System.out.println(" get comments returning empty list.");
		}
		
		System.out.println( "CommentMap:\n" + eventIdToCommentMap.toString());
		return commentList;
	}

	public static int getNumberCommentsOnEvent( Integer eventId )
	{
		if( eventIdToCommentMap.containsKey(eventId) )
		{
			return eventIdToCommentMap.get(eventId).size();
		}
		else
		{
			return 0;
		}
	}
	
	public static void addUserToEvent( String user, Integer eventId )
	{
		if( eventIdToCommentMap.containsKey( user ) )
		{
			List<Integer> eventIdList = userIdToEventIdsMap.get(user);
			eventIdList.add(eventId);
			System.out.println("add event returning list of " + eventIdList.size() + " events.");
		}
		else
		{
			System.out.println( "add event creating first event for user = " + user );
			List<Integer> eventIdList = new ArrayList<Integer>();
			eventIdList.add(eventId);
			userIdToEventIdsMap.put(user, eventIdList);
		}
		
		System.out.println( "EventMap:\n" + userIdToEventIdsMap.toString());
	}
	
	public static List<Integer> getEventIdsForUser( String user )
	{
		List<Integer> eventIdList = emptyUserIdToEventIdsList;
		
		if( userIdToEventIdsMap.containsKey(user) )
		{
			eventIdList = userIdToEventIdsMap.get(user);
		}
		else
		{
			System.out.println(" get events returning empty list.");
		}
		
		System.out.println( "EventMap:\n" + userIdToEventIdsMap.toString());
		return eventIdList;
	}
	
	public static void clearEvents( )
	{
		clearComments();
		System.out.println( "Clearing events for " + userIdToEventIdsMap.size() + " users.");
		userIdToEventIdsMap.clear();
		
	}
	
	public static void clearComments()
	{
		System.out.println( "Clearing comments on " + eventIdToCommentMap.size() + " events.");
		eventIdToCommentMap.clear();
	}
	
	public static User getUser( String userId )
	{
		return userIdToUserMap.get( userId );
	}
	
	public static void addUser( User user )
	{
		userIdToUserMap.put(user.getUserId(), user);
	}
	
	public static void clearUsers( )
	{
		userIdToUserMap.clear();
	}
	
	public static Set<String> getAllUserIds( )
	{
		Set<String> allUserIds = userIdToUserMap.keySet();
		return allUserIds;
	}
	
	public static List<String> getFriends( String userId )
	{
		List<String> friendList = emptyUserIdToFriendIdList;
		
		if( userIdToFriendIdMap.containsKey(userId) )
		{
			friendList = userIdToFriendIdMap.get(userId);
		}
		else
		{
			System.out.println(" get friends returning empty list.");
		}
		
		System.out.println( "FriendMap:\n" + userIdToEventIdsMap.toString());
		return friendList;
	}
	
	public static void addFriend( String userId, String friend )
	{
		if( userIdToFriendIdMap.containsKey( userId ) )
		{
			List<String> friendList = userIdToFriendIdMap.get(userId);
			friendList.add(friend);
			System.out.println("add friend returning list of " + friendList.size() + " friends.");
		}
		else
		{
			System.out.println( "add friend creating first friend " + friend + " for user = " + userId );
			List<String> friendList = new ArrayList<String>();
			friendList.add(friend);
			userIdToFriendIdMap.put(userId, friendList);
		}
		
		System.out.println( "EventMap:\n" + userIdToEventIdsMap.toString());
	}
	
	public static String dumpUserIdToEventIdMap( )
	{
		StringBuffer buf = new StringBuffer();
		
		for( Map.Entry<String,List<Integer>> eventEntry : userIdToEventIdsMap.entrySet())
		{
			buf.append("User:  " + eventEntry.getKey() + "\n");
			List<Integer> eventIdList = eventEntry.getValue();
			
			for( Integer eventId : eventIdList)
			{
				buf.append(     "EventId = " + eventId );
			}
		}
		return buf.toString();
	}
	
	public static String dumpCommentMap( )
	{
		StringBuffer buf = new StringBuffer();

		for( Map.Entry<Integer,List<EventComment>> commentEntry : eventIdToCommentMap.entrySet())
		{
			buf.append("Event:  " + commentEntry.getKey() + "\n");
			List<EventComment> commentList = commentEntry.getValue();

			for( EventComment eventComment : commentList)
			{
				buf.append(     "Comment:  " + eventComment);
			}
		}
		return buf.toString();
	}
	
	public static String dumpUserIdToUserMap( )
	{
		StringBuffer buf = new StringBuffer();
		for(User user : userIdToUserMap.values())
		{
			buf.append("User:  " + user + "\n");
		}
		return buf.toString();
	}
	
	public static String dumpUserIdToFriendIdMap( )
	{
		StringBuffer buf = new StringBuffer();
		
		for( Map.Entry<String,List<String>> entry : userIdToFriendIdMap.entrySet())
		{
			buf.append("User:  " + entry.getKey() );
			
			for( String friend : entry.getValue())
			{
				buf.append( "     Friend:  " + friend );
			}
		}
		return buf.toString();
	}
	
}
