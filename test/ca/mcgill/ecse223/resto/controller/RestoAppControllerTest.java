package ca.mcgill.ecse223.resto.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;


import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class RestoAppControllerTest {
	
	private static int nextTableNmber = 1;
	
	@Before
	public void setUp() 
	{
		// clear all data
		RestoApp restoapp = RestoAppApplication.getRestoApp();
		restoapp.delete();
	}

	@Test
	public void testCreateTableSuccess() 
	{
		fail("Not yet implemented");
	}

}
