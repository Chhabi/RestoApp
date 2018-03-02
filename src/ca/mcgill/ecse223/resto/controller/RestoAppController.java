package ca.mcgill.ecse223.resto.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

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



public class RestoAppController {
	
	public static final int SEAT_DIAMETER = 20;
	
	public static final int TABLE_SPACING = 9 * SEAT_DIAMETER;

	public RestoAppController() {
	}
	
	public static void createTable() throws InvalidInputException
	{	
		int newTableNumber =0;
		int aX;
		int aY;
		Table lastTable;
		Table secondLastTable;
		RestoApp restoapp = RestoAppApplication.getRestoApp();
		List<Table> currentTables = restoapp.getCurrentTables();
		if(currentTables.size() != 0) {
		Table highestNumberedTable = currentTables.stream().max(Comparator.comparing(Table::getNumber)).get();
		newTableNumber = highestNumberedTable.getNumber() + 1;
		}
		else {
			newTableNumber =1 ;
		}
		
		int numberOfSeats = 4;
		int tableWidth = 3*SEAT_DIAMETER;
		int tableLength;
		if (numberOfSeats%2 ==0) {
			tableLength = (numberOfSeats-1)*SEAT_DIAMETER;
		}
		else
		{
			tableLength = (numberOfSeats) * SEAT_DIAMETER;
		}
		
		if(restoapp.getCurrentTables().size() == 0) {
			aX = 30;
			aY = 30;
		}
		else {
			lastTable = restoapp.getCurrentTables().get(restoapp.getCurrentTables().size()-1);
			//secondLastTable = restoapp.getCurrentTables().get(restoapp.getCurrentTables().size()-2);
			if(newTableNumber%2==0) {
				aX= lastTable.getX()+lastTable.getWidth()+TABLE_SPACING;
				aY = lastTable.getY();
			}
			else {
				aX =lastTable.getX();
				aY =lastTable.getY()+lastTable.getLength()+TABLE_SPACING;;
			}
			
		}
		
		try
		{	
			System.out.println("in createTable");
			Table newtable = restoapp.addTable(newTableNumber, aX,aY, tableWidth, tableLength);
			restoapp.addCurrentTable(newtable);
			System.out.println(restoapp.getCurrentTables());
			for (int seatCount = 1; seatCount <= numberOfSeats; seatCount++ )
			{
				newtable.addCurrentSeat(newtable.addSeat());
			}
			//RestoAppApplication.save();
		}
		catch (RuntimeException e)
		{
			throw new InvalidInputException(e.getMessage());
		}
		
	}
	
	//code for create/delete operation should look something like this
//	public static void createTable(int number, int x, int y, int width, int length, int numberOfSeats) throws InvalidInputException {
//		RestoApp restoApp = RestoAppApplication.getRestoApp();
//		try {
//			restoApp.addTable(number, x, y, width, length);
//			//num seats?
//			RestoAppApplication.save();
//		}
//		catch (RuntimeException e) {
//			throw new InvalidInputException(e.getMessage());
//		}
//	}
	
	
//	//code for list should look something like this
//	//JUST AN EXAMPLE
//	public static List<ItemCategory> getItemCategories() {
//		
//		RestoApp restoApp = RestoAppApplication.getRestoApp();
//		ArrayList<ItemCategory> result = new ArrayList<ItemCategory>();
//		for (ItemCategory itemCategory : restoApp.getItemCategories()) {
//			if (true) 
//				result.add(itemCategory);
//		}
//		return result;
}
