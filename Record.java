//@author: Nickolas Pais and Chad Lim

public class Record {

	// The variables that make up record objects
	private String artist; 
	private String title; 
	private int year; 
	
	// Constructor for record objects, taking in artist, title, and year. 
	public Record(String artist, String title, int year){
		this.artist = artist; 
		this.title = title; 
		this.year = year; 
	}
	
	// toString method for records
	// Overridden by the toString methods from the FortyFive and LP classes
	public String toString(){
		String result = artist+" by "+title+", "+year; 
		return result; 
	}

	// Getters and setters for title, artist, and year
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}