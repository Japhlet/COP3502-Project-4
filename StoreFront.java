import java.util.Queue;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * This is a skeleton code provided for students' convenience. 
 * The public methods specified here are required. The private methods are optional.
 * Do not change names of member variables for this class. Do not change public method signatures.
 * All of those items will be tested by the teaching staff's JUnit testing. 
 * The private methods here (or ones that you create as part of your own design) will not be
 * JUnit tested. 
 * @author Dr. Boyer , Chad Lim, and Nickolas Pais
 *
 */
public class StoreFront {

	// Creates a new Queue that holds record types
	private Queue<Record> repairQueue = new LinkedList<Record>(); 

	// Creates new RecordStacks for the different types of records
	private RecordStack inventory45s = new RecordStack();
	private RecordStack inventoryLPs = new RecordStack();


	/**
	 * This main method takes two command-line arguments. 
	 * First it takes a string of the full path to a file from which Lucy's
	 * repair queue should be initialized.
	 * Second it takes a string of the full path to a file from which Lucy's
	 * inventory stacks be initialized. That file has LPs and 45s interleaved.
	 * The main method deals with that to create the two inventory stacks.
	 * @param args The full path to a file for the repair queue and a file for inventory
	 */

	public static void main(String[] args) {

		// Creates a new store object
		StoreFront store = new StoreFront();
		RecordStack inventory = new RecordStack(); 

		// Initializes the repair queue using the inventory populator and
		// command line arguments
		store.repairQueue = InventoryPopulator.getInitialRepairQueue(args[0]);

		// Initializes the inventory stack that holds all types of records
		// using command line arguments
		inventory = InventoryPopulator.getInitialInventory(args[1]); 

		// This loop cycles through the initial inventory stack and pushes
		// the album into either the 45 or LP stack.
		for (int i = 0; i < inventory.getData().length; i++){
			if (inventory.peek() instanceof LP){
				store.inventoryLPs.push(inventory.pop());
			}
			else if (inventory.peek() instanceof FortyFive){
				store.inventory45s.push(inventory.pop()); 
			}
			else{			
			}
		}

		// Prints a friendly greeting to Lucy and then prints the menu
		// repeatedly until the user selects the "Abandon all hope" option
		System.out.println("Hi Lucy! Hope your day is going well, what do you need?");
		while (true){
			store.displayMenu(); 
		}
	}

	/**
	 * StoreFront constructor. Initializes the repairQueue, inventory45s, and inventoryLPs
	 * to empty structures.
	 */
	public StoreFront() {


	}

	/**
	 * Adds the specified album to the end of the repair queue.
	 * Since there is no limit to the size of the repair queue this method just adds the record.
	 * @param r The record to add to the repair queue
	 */
	public void addAlbumToRepair(Record r){
		repairQueue.offer(r); 
	}

	/**
	 * Removes the album at the head of the repair queue.
	 * If the repair queue was already empty, this does nothing. 
	 */
	public void repairAlbum() {
		if (repairQueue.size() != 0){
			System.out.println(repairQueue.poll()); 

		}
		else{
			System.out.println("You have fixed everything! You rule the world!"); 
		}
	}

	/**
	 * Displays the repair queue, as a list of single albums per line. 
	 * Each album is displayed precisely as mandated for the Record.toString method. 
	 * There is no new line at the end of the returned String but there is a newline between
	 * each displayed album.
	 * @return
	 */
	public String printRepairQueue() {
		String result = ""; 
		if (repairQueue.size() > 1 || repairQueue.size() != 0){
			for(Record s: repairQueue){		
				result += s+"\n"; 
			}
		}
		else if (repairQueue.size() == 1){
			for (Record s: repairQueue){
				result += s; 
			}
		}
		else{
			System.out.print("You have fixed everything! You rule the world!");
			return ""; 
		}
		return result.trim(); 
	}

	/**
	 * Adds a new album to the top of the appropriate inventory stack.
	 * @param r The LP or 45 to add to the inventory. 
	 */
	public void addAlbumToSell(Record r) {

		if (r instanceof LP){
			inventoryLPs.push(r); 
		}
		else{
			inventory45s.push(r);
		}
	}

	/**
	 * Removes the topmost item from the inventory of LPs.
	 * If the inventory is empty, calling this method will cause a runtime exception.
	 * Your code must check for empty before calling this method. 
	 */
	public void sellLP() {
		System.out.println(inventoryLPs.pop()); 
	}

	/**
	 * Sells a 45. Removes the topmost 45 from the inventory stack.
	 */
	public void sell45() {

		System.out.println(inventory45s.pop()); 
	}

	/**
	 * This method displays the inventory. It is PROVIDED to students to avoid annoying
	 * test case failure due to small formatting problems. :) 
	 * It is JUnit testable for precise, correct output. 
	 * @return a String representation of the inventory. First LPs then 45s. 
	 * 		   Does not include a new line at the end of this string representation. 
	 */

	public String displayInventory() {
		String LPs = (this.inventoryLPs.size() == 1) ? " LP " : " LPs ";
		String FortyFives = (this.inventory45s.size() == 1) ? " 45." : " 45s.";
		return "You have " + this.inventoryLPs.size() + LPs + "and " + 
		this.inventory45s.size() + FortyFives;	
	}

	/*
	 * This method displays the storefront menu. Dr. Boyer thinks it is convenient to 
	 * display the menu to Lucy, get her choice, and then return that choice from this method.
	 * If your menu items are not ints then you would change the return type. 
	 * You can also just delete this method if it doesn't fit your design.  
	 * @return the selection that Lucy made, as an integer
	 */

	private void displayMenu() {

		// Try-Catch allows for the menu to continue printing
		// even if there is an InputMismatchException
		try{

			// Creates a new scanner used to check what the user has chosen
			// from the main menu
			Scanner input = new Scanner(System.in); 
			int userChoice = 0; 

			// Menu options
			System.out.println("1. Add a new album to be repaired");
			System.out.println("2. Repair an album");
			System.out.println("3. View repair queue");
			System.out.println("4. Add a new album to be sold");
			System.out.println("5. Sell an LP album");
			System.out.println("6. Sell a 45 album");
			System.out.println("7. View sales inventories");
			System.out.println("8. Abandon all hope");

			userChoice = input.nextInt(); 

			// Switch statement that takes in the user choice
			// Methods for each choice are called within each case
			switch (userChoice){

			case 1: System.out.println("Selection: Add a new album to be repaired"); 
			Record newAlbumToRepair = getRecordInfoFromLucy(); 
			if (newAlbumToRepair != null){
				addAlbumToRepair(newAlbumToRepair); 
			}
			else {

			}
			break;

			case 2: System.out.println("Selection: Repair an album"); 
			repairAlbum(); 
			break; 

			case 3: System.out.println("Selection: View repair queue"); 
			System.out.print(printRepairQueue());
			break; 

			case 4: System.out.println("Selection: Add a new album to be sold"); 
			Record newAlbumToSell = getRecordInfoFromLucy(); 
			if (newAlbumToSell != null){
				addAlbumToSell(newAlbumToSell); 
			}
			else{

			}
			break; 	

			case 5: System.out.println("Selection: Sell an LP album"); 

			// Checks to see if the inventory of LPs is empty
			// Results in a planetary explosion if it's empty
			// Sells the LP if not
			if (inventoryLPs.isEmpty() == true){
				System.out.println("It's empty! Planetary implosion!");
				System.exit(1);
			}
			else{
				sellLP();
			}
			break; 

			case 6:	System.out.println("Selection: Sell an EP album"); 

			// Checks to see if the inventory of 45s is empty
			// Results in a planetary explosion if it's empty
			// Sells the 45 if not
			if (inventory45s.isEmpty() == true){
				System.out.println("It's empty! Planetary implosion!");
				System.exit(1);
			}
			else{
				sell45();
			}
			break; 

			case 7: System.out.println("Selection: View sales inventories");
			System.out.println(displayInventory()); 
			break; 

			// If Lucy selects 8, the program terminates and the menu no longer appears
			case 8: System.out.println("Bon Voyage");
			System.exit(1);
			}
		}
		catch (InputMismatchException e){
		}
	}

	/*
	 * This is a service method within this class, provided only for your convenience. 
	 * You do not have to use it (you can delete this method  and structure your code
	 * however you like, including the ways in which you get the info for the new Record
	 * that Lucy wants to either add to inventory or add to the repair queue). 
	 * It is  up to your discretion how to ask Lucy for each new record's information
	 * This method will NOT be JUnit tested. 
	 * @return a new Record which will either be added to a queue or a stack, depending on context
	 */

	private Record getRecordInfoFromLucy() {
		try{
			Scanner s = new Scanner(System.in);
			System.out.println("Ok! What is the title of the album?");
			String title = s.nextLine();
			System.out.println("What is the artist name?");
			String artist = s.nextLine();
			System.out.println("What is the album's year?");
			int year = Integer.parseInt(s.nextLine().trim());
			System.out.println("Is it an LP or a 45? Type 'LP' or '45'.");
			String type = s.nextLine();
			if (type.equals("LP")) return new LP(artist, title,year);
			else if (type.equals("45")) return new FortyFive(artist,title,year);
			else return null;
		}
		catch (Exception e){
			return null; 
		}
	}
}