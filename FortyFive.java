//@author: Nickolas Pais and Chad Lim

public class FortyFive extends Record{

	// Constructor for FortyFive objects that takes in the same 
	// parameters as albums. It extends from the Record class. 
	public FortyFive(String artist, String title, int year){
		super(artist, title, year); 
	}
	
	// To string method for the FortyFive class the overrides the toString method
	// from the Record class
	public String toString(){
		String artist = getArtist(); 
		String title = getTitle(); 
		int year = getYear(); 
		String result = "45: "+title+" by "+artist+", "+year; 
		return result; 
	}
}
