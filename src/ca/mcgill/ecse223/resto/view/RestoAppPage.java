/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;

public class RestoAppPage extends JFrame {

	private static final long serialVersionUID = -2702005067769134471L;
	private static final int MAX_SEATS = 8;
	
    /************DECLARATIONS******************/
    private JPanel Image_panel;
    private JPanel app_panel;
    private JPanel buttons_panel;
    private JLabel jLabel2;
    private JPanel scroll_panel;
    private JScrollPane scroll_layout;
    private RestoVisualizer restoVisualizer;
    // UI elements
  	private JLabel errorMessage;
  	// table
	private Table selectedTable = null;
  	// data elements
  	private String error = null;
  	// table assignment
  	//...
	
 	public RestoAppPage() {
 		initComponents();
 		refreshData();
 	}
 	
 	/** This method is called from within the constructor to initialize the form.
	 */
    private void initComponents() {
    	
    	errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
        app_panel = new JPanel();
        scroll_panel = new JPanel();
        restoVisualizer = new RestoVisualizer();
        restoVisualizer.setMinimumSize(new Dimension(10000,10000));
        scroll_layout = new JScrollPane();
        Image_panel = new JPanel();
        jLabel2 = new JLabel();
        buttons_panel = new JPanel();
        new JButton();
        new JButton();
        
        RestoApp restoapp = RestoAppApplication.getRestoapp();
		restoVisualizer.setResto(restoapp);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        
        app_panel.setBackground(java.awt.Color.white);

        Image_panel.setBackground(java.awt.Color.white);

        jLabel2.setIcon(new ImageIcon(getClass().getResource("../resources/Screen Shot 2018-02-20 at 1.08.07 AM.png"))); // NOI18N
        
        //elements for addTableButton, reserveTableButton, billTableButton, displayMenuButton
        RoundButton addTableButton = new RoundButton();
        addTableButton.setBackground(new Color(255,255,255));
		try {
			Image img = ImageIO.read(getClass().getResource("../resources/add.bmp"));
			Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			addTableButton.setIcon(new ImageIcon(scaled));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addTableButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		addTableButtonActionPerformed(evt);
            }
        });
		
		RoundButton reserveTableButton = new RoundButton();
        reserveTableButton.setBackground(new Color(255,255,255));
		try {
			Image img = ImageIO.read(getClass().getResource("../resources/reserve.bmp"));
			Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			reserveTableButton.setIcon(new ImageIcon(scaled));
		} catch (IOException e) {
			e.printStackTrace();
		}
		reserveTableButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		reserveTableButtonActionPerformed(evt);
            }
        });
		 
		RoundButton billTableButton = new RoundButton();
		billTableButton.setBackground(new Color(255,255,255));
		try {
			Image img = ImageIO.read(getClass().getResource("../resources/bill.bmp"));
			Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			billTableButton.setIcon(new ImageIcon(scaled));
		} catch (IOException e) {
			e.printStackTrace();
		}
		billTableButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		billTableButtonActionPerformed(evt);
            }
        });
		
		RoundButton displayMenuButton = new RoundButton();
        displayMenuButton.setBackground(new Color(255,255,255));
		try {
			Image img = ImageIO.read(getClass().getResource("../resources/displayMenu.bmp"));
			Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			displayMenuButton.setIcon(new ImageIcon(scaled));
		} catch (IOException e) {
			e.printStackTrace();
		}
		displayMenuButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		displayMenuButtonActionPerformed(evt);
            }
        });
        
		/********************LAYOUT FOR INITIAL STATE*******************/
        GroupLayout scroll_panel_Layout = new GroupLayout(scroll_panel);
        scroll_panel.setLayout(scroll_panel_Layout);
        scroll_panel_Layout.setHorizontalGroup(
        		scroll_panel_Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(scroll_panel_Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(restoVisualizer)
                .addContainerGap(69, Short.MAX_VALUE))
            );
        scroll_panel_Layout.setVerticalGroup(
        		scroll_panel_Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(scroll_panel_Layout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(restoVisualizer)
                    .addContainerGap(179, Short.MAX_VALUE))
            );
        
        scroll_layout.setViewportView(scroll_panel);


        
        
        GroupLayout Image_panelLayout = new GroupLayout(Image_panel);
        Image_panel.setLayout(Image_panelLayout);
        Image_panelLayout.setHorizontalGroup(
            Image_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, Image_panelLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118))
        );
        Image_panelLayout.setVerticalGroup(
            Image_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Image_panelLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(139, 139, 139))
        );

        buttons_panel.setBackground(java.awt.Color.white);


        GroupLayout buttons_panelLayout = new GroupLayout(buttons_panel);
        buttons_panel.setLayout(buttons_panelLayout);
        buttons_panelLayout.setHorizontalGroup(
            buttons_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buttons_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addTableButton, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                .addComponent(reserveTableButton)
                .addComponent(billTableButton)
            	.addComponent(displayMenuButton))
        );

        buttons_panelLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addTableButton, reserveTableButton, displayMenuButton});

        buttons_panelLayout.setVerticalGroup(
            buttons_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buttons_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttons_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                	.addComponent(addTableButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                	.addComponent(reserveTableButton, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(billTableButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(displayMenuButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        GroupLayout app_panelLayout = new GroupLayout(app_panel);
        app_panel.setLayout(app_panelLayout);
        app_panelLayout.setHorizontalGroup(
            app_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(app_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(app_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_layout, GroupLayout.PREFERRED_SIZE, 497, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttons_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(Image_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        
        app_panelLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {Image_panel, scroll_layout});

        app_panelLayout.setVerticalGroup(
            app_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(app_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(app_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(Image_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(app_panelLayout.createSequentialGroup()
                        .addComponent(scroll_layout)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttons_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(app_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(app_panel, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
        );

        
        pack();
    }

    private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			//TODO: Implement error messages on screen
		}
		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}
    
    
    /**************ACTIONS*****************/
    private void addTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = null;
		try {
			
			RestoAppController.createTable();
			RestoApp restoapp = RestoAppApplication.getRestoapp();
			restoVisualizer.setResto(restoapp);
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
    }
    private void reserveTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	reservePopUp(2,2);
		RestoApp restoapp = RestoAppApplication.getRestoapp();
		restoVisualizer.setResto(restoapp);
    }
    private void billTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    private void displayMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
		menuPopUp(2,2,RestoAppController.getItemCategories(), null);
		RestoApp restoapp = RestoAppApplication.getRestoapp();
		restoVisualizer.setResto(restoapp);
	}
    
    private void tableCurrentSeatsChangeActionPerformed(ChangeEvent evt, int numSeats) {
    	try {
			RestoAppController.updateTable(selectedTable, selectedTable.getNumber(), numSeats);
			RestoApp restoapp = RestoAppApplication.getRestoapp();
			restoVisualizer.setResto(restoapp);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
    }
    
    private void tableNumberChangeActionPerformed(ActionEvent evt, String newTableNumber) {
		try {
			RestoAppController.updateTable(selectedTable, Integer.valueOf(newTableNumber), selectedTable.getCurrentSeats().size());
			RestoApp restoapp = RestoAppApplication.getRestoapp();
			restoVisualizer.setResto(restoapp);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
    }
    
    int x_move;
    int y_move;
    private void moveTableButtonActionPerformed(ActionEvent evt) {
    	error = null;
		restoVisualizer.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				x_move = e.getX();
				y_move = e.getY();
				try {
					RestoAppController.moveTable(selectedTable, x_move,y_move);
					RestoApp restoapp = RestoAppApplication.getRestoapp();
					restoVisualizer.setResto(restoapp);
				} catch (InvalidInputException e1) {
					e1.printStackTrace();
				} 	
			}
		}); 
	}
    
    /**
     * If selectedTable (global variable) is in the CurrentTables, calls the Controller method to remove
     */
	private void removeTableButtonActionPerformed(ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		if (!(selectedTable.getNumber() > 0))
			error = "Table needs to be selected for deletion!";
		
		if (error.length() == 0) {
			// call the controller
			try {
				Table toDelete = null;
				List<Table> currentTables = RestoAppApplication.getRestoapp().getCurrentTables();
				for(Table table : currentTables) {
					if(table.getNumber() == selectedTable.getNumber()) {
						toDelete = table;
					}
				}
				RestoAppController.removeTable(toDelete);
				RestoApp restoapp = RestoAppApplication.getRestoapp();
				restoVisualizer.setResto(restoapp);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		
		// update visuals
		refreshData();
	};
	
	/**
	 * Displays the proper popUp for the category of menu selected
	 */
	private void CategoryButtonActionPerformed(ActionEvent evt, ItemCategory itemCategory, List<ItemCategory> i) {
		try {
			menuPopUp(2, 2, i, RestoAppController.getMenuItems(itemCategory));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		RestoApp restoapp = RestoAppApplication.getRestoapp();
		restoVisualizer.setResto(restoapp);
	}

	/**
	 * Is called by the mouseListener in RestoVisualiser whenever a table is clicked. Makes the popup for that table appear
	 */
	public void tablePopUp(int x, int y, Table aTable) {

		selectedTable = aTable;
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setMinimumSize(new Dimension(3,3));
        popupMenu.setBackground(new Color(255,230,153));
        
        JPanel popupMenuItem1 = new JPanel();
        JPanel popupMenuItem2 = new JPanel();
        JPanel popupMenuItem3 = new JPanel();
        JPanel popupMenuItem4 = new JPanel();

        //Table Label
        JLabel tableName = new JLabel();
        tableName.setBackground(new Color(255,230,153));
        
        JTextField tableNumber = new JTextField();
        tableNumber.setBackground(new Color(255,230,153));
        tableNumber.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		String newTableNumber = tableNumber.getText();
        		tableNumberChangeActionPerformed(evt, newTableNumber);
            }
        });   
        
        JSlider tableSlider = new JSlider();
        tableSlider.setBackground(new Color(255,230,153));
        tableSlider.setMaximum(MAX_SEATS);
		tableSlider.setMinimum(1);
		tableSlider.setMajorTickSpacing(1);
		tableSlider.setPaintTicks(true);
		tableSlider.setPaintLabels(true);
		tableSlider.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent evt) {
        		int numSeats = tableSlider.getValue();
        		tableCurrentSeatsChangeActionPerformed(evt, numSeats);
            }
        });
        
		//Delete Button
        RoundButton removeTableButton = new RoundButton();
        removeTableButton.setBackground(new Color(255,230,153));
		try {
			Image img = ImageIO.read(getClass().getResource("../resources/remove.bmp"));
			Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			removeTableButton.setIcon(new ImageIcon(scaled));
		} catch (IOException e) {
			e.printStackTrace();
		}
		removeTableButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		removeTableButtonActionPerformed(evt);
        		popupMenu.setVisible(false);
            }
        });
		
		//Move Button
		RoundButton moveTableButton = new RoundButton();
        moveTableButton.setBackground(new Color(255,230,153));
        try {
			Image img = ImageIO.read(getClass().getResource("../resources/move.bmp"));
			Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			moveTableButton.setIcon(new ImageIcon(scaled));
		} catch (IOException e) {
			e.printStackTrace();
		}
        moveTableButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		moveTableButtonActionPerformed(evt);
            }

			
        });

        //Rotate button
        RoundButton rotateTableButton = new RoundButton();
        rotateTableButton.setBackground(new Color(255,230,153));
        try {
			Image img = ImageIO.read(getClass().getResource("../resources/rotate.bmp"));
			Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			rotateTableButton.setIcon(new ImageIcon(scaled));
		} catch (IOException e) {
			e.printStackTrace();
		}

        //inUse Button
        RoundButton inUseButton = new RoundButton();
        inUseButton.setBackground(new Color(255,230,153));
        try {
			Image img = ImageIO.read(getClass().getResource("../resources/inUse.bmp"));
			Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			inUseButton.setIcon(new ImageIcon(scaled));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        String selectedTableNumber = "-1";
		if(selectedTable != null) {
			selectedTableNumber = Integer.toString(selectedTable.getNumber());
		}
		
		tableName.setText("Table ");
		tableNumber.setText(selectedTableNumber);
		tableSlider.setValue(selectedTable.getCurrentSeats().size());
		
		popupMenu.setLayout(new BoxLayout(popupMenu, BoxLayout.PAGE_AXIS));
	    popupMenuItem1.setLayout(new BoxLayout(popupMenuItem1, BoxLayout.LINE_AXIS));
	    popupMenuItem2.setLayout(new BoxLayout(popupMenuItem2, BoxLayout.LINE_AXIS));
	    popupMenuItem3.setLayout(new BoxLayout(popupMenuItem3, BoxLayout.LINE_AXIS));
	    popupMenuItem4.setLayout(new BoxLayout(popupMenuItem4, BoxLayout.LINE_AXIS));
	        
	    popupMenuItem1.add(tableName);
	    popupMenuItem1.add(tableNumber);
	    popupMenuItem2.add(removeTableButton);
	    popupMenuItem2.add(moveTableButton);
	    popupMenuItem3.add(rotateTableButton);
	    popupMenuItem3.add(inUseButton);
	    popupMenuItem4.add(tableSlider);
	        
	    popupMenu.add(popupMenuItem1);
	    popupMenu.add(popupMenuItem2);
	    popupMenu.add(popupMenuItem3);
	    popupMenu.add(popupMenuItem4);
	        
		popupMenu.show(Image_panel, x, y);
	}

	/**
	 * Is called whenever a menu is selected. Makes the list of items for that menu appear.
	 */
	public void menuPopUp(int x, int y, List<ItemCategory> items, List<MenuItem> menuItems) {

        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setMinimumSize(new Dimension(50,50));
        popupMenu.setBackground(new Color(255,230,153));
        
        String c1 = items.get(0).toString();
        String c2 = items.get(1).toString();
        String c3 = items.get(2).toString();
        String c4 = items.get(3).toString();
        String c5 = items.get(4).toString();
        
        JPanel popupMenuItem1 = new JPanel();
        JPanel popupMenuItem2 = new JPanel();
        JPanel popupMenuItem3 = new JPanel();
        JPanel popupMenuItem4 = new JPanel();
        JPanel popupMenuItem5 = new JPanel();
        JPanel popupMenuItem6 = new JPanel();
        JPanel popupMenuItem7 = new JPanel();

        JLabel menuName = new JLabel();
        menuName.setBackground(new Color(255,230,153));
        
        //Category1
        JButton Category1Button = new JButton();
        Category1Button.setBackground(new Color(255,230,153));
		Category1Button.setText(c1);
		Category1Button.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		CategoryButtonActionPerformed(evt, items.get(0), items);
            }
        });
        
		//Category2
        JButton Category2Button = new JButton();
        Category2Button.setBackground(new Color(255,230,153));
		Category2Button.setText(c2);
		Category2Button.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		CategoryButtonActionPerformed(evt, items.get(1), items);
            }
        });

		//Category3
        JButton Category3Button = new JButton();
        Category3Button.setBackground(new Color(255,230,153));
		Category3Button.setText(c3);
		Category3Button.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		CategoryButtonActionPerformed(evt, items.get(2), items);
            }
        });

		//Category4
        JButton Category4Button = new JButton();
        Category4Button.setBackground(new Color(255,230,153));
		Category4Button.setText(c4);
		Category4Button.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		CategoryButtonActionPerformed(evt, items.get(3), items);
            }
        });
        
		//Category5
        JButton Category5Button = new JButton();
        Category5Button.setBackground(new Color(255,230,153));
		Category5Button.setText(c5);
		Category5Button.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		CategoryButtonActionPerformed(evt, items.get(4), items);
            }
        });
		
		if (menuItems != null) {
			int j = 0;
			for (MenuItem menuItem : menuItems) {
				j++;
				JButton MenuItemButton = new JButton();
				MenuItemButton.setBackground(new Color(255,230,153));
				MenuItemButton.setText(menuItem.getName());
				if (j < 5) {
					popupMenuItem3.add(MenuItemButton);
				}
				else if (j < 9) {
					popupMenuItem4.add(MenuItemButton);
				}
				else if (j < 13) {
					popupMenuItem5.add(MenuItemButton);
				}
				else if (j < 17) {
					popupMenuItem6.add(MenuItemButton);
				}
				else {
					popupMenuItem7.add(MenuItemButton);
				}
			}
		}
		
		menuName.setText("MENU ");
		
		
		popupMenu.setLayout(new BoxLayout(popupMenu, BoxLayout.PAGE_AXIS));
	    popupMenuItem1.setLayout(new BoxLayout(popupMenuItem1, BoxLayout.LINE_AXIS));
	    popupMenuItem2.setLayout(new BoxLayout(popupMenuItem2, BoxLayout.LINE_AXIS));
	    popupMenuItem3.setLayout(new BoxLayout(popupMenuItem3, BoxLayout.LINE_AXIS));
	    popupMenuItem4.setLayout(new BoxLayout(popupMenuItem4, BoxLayout.LINE_AXIS));
	    popupMenuItem5.setLayout(new BoxLayout(popupMenuItem5, BoxLayout.LINE_AXIS));
	    popupMenuItem6.setLayout(new BoxLayout(popupMenuItem6, BoxLayout.LINE_AXIS));
	    popupMenuItem7.setLayout(new BoxLayout(popupMenuItem7, BoxLayout.LINE_AXIS));
	        
	    popupMenuItem1.add(menuName);
	    popupMenuItem2.add(Category1Button);
	    popupMenuItem2.add(Category2Button);
	    popupMenuItem2.add(Category3Button);
	    popupMenuItem2.add(Category4Button);
	    popupMenuItem2.add(Category5Button);
	        
	    popupMenu.add(popupMenuItem1);
	    popupMenu.add(popupMenuItem2);
	    popupMenu.add(popupMenuItem3);
	    popupMenu.add(popupMenuItem4);
	    popupMenu.add(popupMenuItem5);
	    popupMenu.add(popupMenuItem6);
	    popupMenu.add(popupMenuItem7);
	    
		popupMenu.show(Image_panel, x, y);
	}
	
	public void reservePopUp(int x, int y) {

        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setMinimumSize(new Dimension(3,3));
        popupMenu.setBackground(new Color(255,230,153));
        
        JPanel popupMenuItem1 = new JPanel();
        JPanel popupMenuItem2 = new JPanel();
        JPanel popupMenuItem3 = new JPanel();
        JPanel popupMenuItem4 = new JPanel();
        JPanel popupMenuItem5 = new JPanel();
        JPanel popupMenuItem6 = new JPanel();
        JPanel popupMenuItem7 = new JPanel();
        JPanel popupMenuItem8 = new JPanel();
        JPanel popupMenuItem9 = new JPanel();
        JPanel popupMenuItem10 = new JPanel();
        JPanel popupMenuItem11 = new JPanel();
        JPanel popupMenuItem12 = new JPanel();
        JPanel popupMenuItem13 = new JPanel();
        JPanel popupMenuItem14 = new JPanel();

        //Reservation Labels
        
        JLabel makeReservation = new JLabel();
        makeReservation.setBackground(new Color(255,230,153));
        makeReservation.setText("Make a Reservation: ");
        
        JLabel cancelReservation = new JLabel();
        makeReservation.setBackground(new Color(255,230,153));
        makeReservation.setText("Cancel Reservation: ");
        
        JLabel reservationTable = new JLabel();
        reservationTable.setBackground(new Color(255,230,153));
        makeReservation.setText("Table: ");
        
        JLabel cancelTable = new JLabel();
        reservationTable.setBackground(new Color(255,230,153));
        makeReservation.setText("Table: ");
        
        JLabel reservationDate = new JLabel();
        reservationDate.setBackground(new Color(255,230,153));
        reservationDate.setText("Date: ");
        
        JLabel reservationTime = new JLabel();
        reservationTime.setBackground(new Color(255,230,153));
        reservationTime.setText("Time: ");
        
        JLabel reservationSize = new JLabel();
        reservationSize.setBackground(new Color(255,230,153));
        reservationSize.setText("Size: ");
        
        JLabel reservationName = new JLabel();
        reservationName.setBackground(new Color(255,230,153));
        reservationName.setText("Name: ");
        
        JLabel reservationMail = new JLabel();
        reservationMail.setBackground(new Color(255,230,153));
        reservationMail.setText("Email Address: ");
        
        JLabel reservationPhone = new JLabel();
        reservationPhone.setBackground(new Color(255,230,153));
        reservationPhone.setText("Phone Number: ");
        
        JLabel reservationNumber = new JLabel();
        reservationNumber.setBackground(new Color(255,230,153));
        reservationNumber.setText("Reservation Number: ");
        
        JLabel cancelNumber = new JLabel();
        reservationNumber.setBackground(new Color(255,230,153));
        reservationNumber.setText("Reservation Number: ");
        
        JTextField tableField = new JTextField();
        tableField.setBackground(new Color(255,230,153));
        
        JTextField dateField = new JTextField();
        dateField.setBackground(new Color(255,230,153));
        
        JTextField timeField = new JTextField();
        timeField.setBackground(new Color(255,230,153));

        JTextField sizeField = new JTextField();
        sizeField.setBackground(new Color(255,230,153));
        
        JTextField nameField = new JTextField();
        nameField.setBackground(new Color(255,230,153));
        
        JTextField mailField = new JTextField();
        mailField.setBackground(new Color(255,230,153));
        
        JTextField phoneField = new JTextField();
        phoneField.setBackground(new Color(255,230,153));
        
        JTextField numberField = new JTextField();
        numberField.setBackground(new Color(255,230,153));
        
        JTextField cancelTableField = new JTextField();
        numberField.setBackground(new Color(255,230,153));
        
        JTextField cancelNumberField = new JTextField();
        numberField.setBackground(new Color(255,230,153));
        
		//Delete Button
        JButton makeReservationButton = new JButton();
        makeReservationButton.setBackground(new Color(255,230,153));
        makeReservationButton.setText("Make Reservation");
        makeReservationButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		popupMenu.setVisible(false);
            }
        });
        
        JButton cancelReservationButton = new JButton();
        makeReservationButton.setBackground(new Color(255,230,153));
        makeReservationButton.setText("Cancel Reservation");
        makeReservationButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		popupMenu.setVisible(false);
            }
        });
		
		popupMenu.setLayout(new BoxLayout(popupMenu, BoxLayout.PAGE_AXIS));
	    popupMenuItem1.setLayout(new BoxLayout(popupMenuItem1, BoxLayout.LINE_AXIS));
	    popupMenuItem2.setLayout(new BoxLayout(popupMenuItem2, BoxLayout.LINE_AXIS));
	    popupMenuItem3.setLayout(new BoxLayout(popupMenuItem3, BoxLayout.LINE_AXIS));
	    popupMenuItem4.setLayout(new BoxLayout(popupMenuItem4, BoxLayout.LINE_AXIS));
	    popupMenuItem5.setLayout(new BoxLayout(popupMenuItem5, BoxLayout.LINE_AXIS));
	    popupMenuItem6.setLayout(new BoxLayout(popupMenuItem6, BoxLayout.LINE_AXIS));
	    popupMenuItem7.setLayout(new BoxLayout(popupMenuItem7, BoxLayout.LINE_AXIS));
	    popupMenuItem8.setLayout(new BoxLayout(popupMenuItem8, BoxLayout.LINE_AXIS));
	    popupMenuItem9.setLayout(new BoxLayout(popupMenuItem9, BoxLayout.LINE_AXIS));
	    popupMenuItem10.setLayout(new BoxLayout(popupMenuItem10, BoxLayout.LINE_AXIS));
	    popupMenuItem11.setLayout(new BoxLayout(popupMenuItem11, BoxLayout.LINE_AXIS));
	    popupMenuItem12.setLayout(new BoxLayout(popupMenuItem12, BoxLayout.LINE_AXIS));
	    popupMenuItem13.setLayout(new BoxLayout(popupMenuItem13, BoxLayout.LINE_AXIS));
	    popupMenuItem14.setLayout(new BoxLayout(popupMenuItem14, BoxLayout.LINE_AXIS));
	        
	    popupMenuItem1.add(makeReservation);
	    popupMenuItem2.add(reservationTable);
	    popupMenuItem2.add(tableField);
	    popupMenuItem3.add(reservationDate);
	    popupMenuItem3.add(dateField);
	    popupMenuItem4.add(reservationTime);
	    popupMenuItem4.add(timeField);
	    popupMenuItem5.add(reservationSize);
	    popupMenuItem5.add(sizeField);
	    popupMenuItem6.add(reservationName);
	    popupMenuItem6.add(nameField);
	    popupMenuItem7.add(reservationMail);
	    popupMenuItem7.add(mailField);
	    popupMenuItem8.add(reservationPhone);
	    popupMenuItem8.add(phoneField);
	    popupMenuItem9.add(reservationNumber);
	    popupMenuItem9.add(numberField);
	    popupMenuItem10.add(makeReservationButton);
	    popupMenuItem11.add(cancelReservation);
	    popupMenuItem12.add(cancelTable);
	    popupMenuItem12.add(cancelTableField);
	    popupMenuItem13.add(cancelNumber);
	    popupMenuItem13.add(cancelNumberField);
	    popupMenuItem14.add(cancelReservationButton);
	        
	    popupMenu.add(popupMenuItem1);
	    popupMenu.add(popupMenuItem2);
	    popupMenu.add(popupMenuItem3);
	    popupMenu.add(popupMenuItem4);
	    popupMenu.add(popupMenuItem5);
	    popupMenu.add(popupMenuItem6);
	    popupMenu.add(popupMenuItem7);
	    popupMenu.add(popupMenuItem8);
	    popupMenu.add(popupMenuItem9);
	    popupMenu.add(popupMenuItem10);
//	   	popupMenu.add(popupMenuItem11);
//	    popupMenu.add(popupMenuItem12);
//	    popupMenu.add(popupMenuItem13);
//	    popupMenu.add(popupMenuItem14);
    
		popupMenu.show(Image_panel, x, y);
	}
	
}
