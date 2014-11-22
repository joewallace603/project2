package com.jpw.ws;

import com.jpw.app.*;
import com.jpw.manager.*;
import com.jpw.interfaces.*;

import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jpw.app.EventTemp;
import com.jpw.dto.Event;

@Path( "eventsmock" )
public class WSEventsMock
{
	@SuppressWarnings( "unused" )
	@Context
	private UriInfo context;
	private static final ObjectMapper objectMapper = new ObjectMapper( );

	/**
	 * Default constructor.
	 */
	public WSEventsMock()
	{
	}

	/**
	 * Retrieves representation of an instance of GetEventsWeek
	 * 
	 * @return an instance of String
	 */
	@GET
	@Produces( "application/json" )
	public String getMockEvents( )
	{
		
		String eventsString = "";
		
		List<EventTemp> events = new ArrayList<EventTemp>( );

		EventTemp a = new EventTemp();

		a.setEventId(10);
		a.setTitle("Boys MYA Basketball Game");
		a.setDesc("Lori Wallace says:  Join us to watch the boys first MYA basketball game.");
		a.setDate(new GregorianCalendar(2013, 12, 5, 5, 30).getTime());
		a.setStatus(0);
		a.setLocation(" 7 School St, Merrimack, NH 03054 ");
		a.setGoing(Arrays.asList("Joe", "Lori"));
		a.setOut(Arrays.asList("Nana", "Grandpa"));
		a.setMaybe(Arrays.asList("Bob", "Dawn"));
		a.setInvited(Arrays.asList("Joe", "Lori", "Nana", "Grandpa", "Bob",
				"Dawn"));
		a.setNumberOfComments(3567);
		a.setImage("mya.bmp");
		a.setReminder(new GregorianCalendar(2013, 12, 5, 4, 30).getTime());
		a.setCreator("Lori");

		events.add(a);

		EventTemp b = new EventTemp();

		b.setEventId(20);
		b.setTitle("Guys night out");
		b.setDesc("Joe Wallace says:  Dinner at the Daughter.  Maybe a stoagie at Castro's afterwards.");
		b.setDate(new GregorianCalendar(2013, 12, 7, 7, 30).getTime());
		b.setStatus(1);
		b.setLocation(" 7 School St, Merrimack, NH 03054 ");
		b.setGoing(Arrays.asList("Joe", "Fabio"));
		b.setOut(Arrays.asList("Mario", "Tim"));
		b.setMaybe(Arrays.asList("Bob", "Scott"));
		b.setInvited(Arrays.asList("Joe", "Fabio", "Mario", "Tim", "Bob",
				"Scott"));
		b.setNumberOfComments(5);
		b.setImage("joe.bmp");
		b.setReminder(new GregorianCalendar(2013, 12, 7, 6, 30).getTime());
		b.setCreator("Joe");

		events.add(b);

		EventTemp c = new EventTemp();

		c.setEventId(30);
		c.setTitle("Team Training Ride");
		c.setDesc("Fabio Piergentili says:  Let's meet at the Bedford Library.");
		c.setDate(new GregorianCalendar(2013, 12, 7, 4, 30).getTime());
		c.setStatus(1);
		c.setLocation(" 7 School St, Merrimack, NH 03054 ");
		c.setGoing(Arrays.asList("Fabio", "Gene", "Mario", "Bob"));
		c.setOut(Arrays.asList("Tim"));
		c.setMaybe(Arrays.asList("Bob", "Scott"));
		c.setInvited(Arrays.asList("Fabio", "Mario", "Gene", "Bob", "Scott"));
		c.setNumberOfComments(14);
		c.setImage("bike_team.bmp");
		c.setReminder(new GregorianCalendar(2013, 12, 7, 3, 30).getTime());
		c.setCreator("Fabio");

		events.add(c);

		try
		{
			eventsString = objectMapper.writeValueAsString( events );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}

		return eventsString;
	}

}