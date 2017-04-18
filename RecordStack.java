//@author: Nickolas Pais and Chad Lim

public class RecordStack {

	private Record[] data = new Record[0]; 

	// Points to the top of the stack
	// and can also be used as the size of the stack
	private int top = 0; 		


	// This method adds a new record to the stack
	// and also resizes the stack to compensate for overflow
	public void push (Record r) {

		if (isAtCapacity()){
			resizeStack(data.length+1); 
		}
		
		// Increases the value of top when another record
		// is added to the top of the stack
		data[top++] = r; 

	}

	// Peeks into and returns the record at the top of the stack
	// but does not remove the record from the top of the stack
	public Record peek(){
		return data[top-1]; 
	}

	// Checks to see if the stack is full by comparing the 
	// top to the length of the array
	// Returns true if the stack is at capacity
	private boolean isAtCapacity(){
		return top == data.length; 
	}

	// This method increases the size of the stack if its capacity
	// has been reached
	public void resizeStack(int newCapacity){
		Record[] newArray = new Record [newCapacity]; 
		for(int i = 0; i < top; i++){
			newArray[i] = data[i]; 
		}
		data = newArray; 
	}

	// Removes the record at the top of the stack and returns it
	public Record pop() {
		if(top !=0){
		Record poppedElement = data[--top]; 
		return poppedElement; }
		else{
			return null;
		}
	}

	// Checks to see if the stack is empty, and returns true if it is
	// and false if there are still records in the stack
	public boolean isEmpty(){
		if (top == 0){
			return true; 
		}
		else{
			return false; 
		}
	}

	// the toString method for this class
	// As per the requirements, it is not used, but
	// it returns a meaningful result
	public String toString(){
		String result = "Top record: "+data[top]; 
		return result; 
	}

	// Prints the entire record stack
	public void print(){
		for (int i = 0; i < top ; i++){
			System.out.print(data[i]+ " ");
		}
	}

	// Returns the top of the stack as an integer value
	// otherwise known as the current size of the stack
	public int size(){
		return top; 
	}

	// Getters and setters for the Record[] array
	public Record[] getData() {
		return data;
	}

	public void setData(Record[] data) {
		this.data = data;
	}

}
