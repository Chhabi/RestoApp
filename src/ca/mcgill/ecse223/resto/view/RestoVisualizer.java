package ca.mcgill.ecse223.resto.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Ellipse2D; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class RestoVisualizer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3046970097697621942L;
	
	//UI elements
	private List<RoundRectangle2D> rectangles = new ArrayList<RoundRectangle2D>();
	private List<Ellipse2D> circles = new ArrayList<Ellipse2D>();
	private static final int LINEX = 30;
	private static final int LINETOPY = 30;
	
	private static final int SEAT_DIAMETER = 20;
	private static final int TABLE_WIDTH = 3 * SEAT_DIAMETER;
	private int tableLength ;
	
	// data elements
	private RestoApp restoapp;
	private Table selectedTable;
	private Seat selectedSeat;
	private HashMap<RoundRectangle2D, Table> tables;
	private HashMap<Ellipse2D, Seat> seats; 
	
	
	
	
	
	
	
	
	
	public RestoVisualizer() {
		super();
		init();
	}
	
	
	private void init() {
		restoapp = null;
		tables =new HashMap<RoundRectangle2D, Table>();
		seats = new HashMap<Ellipse2D, Seat>(); 
		selectedSeat = null;
		selectedTable = null;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int x = e.getX();
				int y = e.getY();
				for(RoundRectangle2D rectangle: rectangles)
				{
					if(rectangle.contains(x, y))
					{
						selectedTable = tables.get(rectangle);
					}
				}
				
			}
		});
		
		
	}
	
	public void setResto(RestoApp restoapp)
	{
		this.restoapp = restoapp;
		tables = new HashMap<RoundRectangle2D, Table>();
		seats = new HashMap<Ellipse2D, Seat>(); 
		selectedSeat = null;
		selectedTable  = null;
		System.out.println("in setResto");
		repaint();
		
	}
	private void doDrawing(Graphics g)
	{
		if(restoapp != null)
		{
			System.out.print("in doDrawing");
			
			int number = restoapp.getCurrentTables().size();
			
			Graphics2D g2d = (Graphics2D) g.create();
			
			
			
			BasicStroke thinStroke = new BasicStroke(2);
			g2d.setStroke(thinStroke);
			
			rectangles.clear();
			tables.clear();
			
			for (Table table: restoapp.getCurrentTables()) {
				RoundRectangle2D rectangle = new RoundRectangle2D.Double(table.getX(),table.getY(), table.getWidth(), table.getLength(), 10, 10);
				
				rectangles.add(rectangle);
				
				tables.put(rectangle, table);
				g2d.setColor(Color.GRAY);
				g2d.fill(rectangle);
				g2d.draw(rectangle);
				g2d.setColor(Color.BLACK);
				g2d.drawString(new Integer(table.getNumber()).toString(),(int) (table.getX() + (table.getWidth()/2.3)), (int)(table.getY() + (table.getLength()/1.8)) );
				System.out.println(table.getCurrentSeats().size());
				
				for(Seat seat: table.getCurrentSeats()) {
					Ellipse2D circle = new Ellipse2D.Double(table.getX()+SEAT_DIAMETER, table.getY()-1.5*(SEAT_DIAMETER), SEAT_DIAMETER, SEAT_DIAMETER);
					circles.add(circle);
					
					seats.put(circle, seat);
					
					g2d.setColor(Color.GRAY);
					g2d.fill(circle);
					g2d.draw(circle);
					
					
				}
				
				
			}
			
			
			
		}
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		doDrawing(g);
	}

}
