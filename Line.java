/*Class is used to represent a horizontal or vertical line in an array.
 * It stores the rows and columns of the end points of the line.
 */
public class Line {
	
	//Integer Arrays
	private int[] start;
	/*Start array stores the row and column of the start point*/
	private int[] end; 
	/*The end array stores the row and column of the end point.*/
//	private int length;
	
	
	
	public Line(int row, int col, boolean horizontal, int length) { // Constructor
		//Parameter used to initialize the instance variables
		
		start = new int[2]; //
		//if the line is horizontal: 
				/*end point will be on the same row as the 
				 *  start point, and its column will be the start * column plus length of the line * *
				 *   if (horizontal) {
				 *    * 	end = new int[2]; 
				 *    * 	end[0] = row; 
				 *    * 	end[1] = col + length -1; * 
				 *  if (!horzontal) { * 
				 *  		end[0] = row + length - 1; 
				 *  * 		end[1] = col */
		start[0] = row;
		start[1] = col;
	
		if (horizontal == true) {
			end = new int[2]; // Initialize the array.
			end[0] = row;
			end[1] = col + length -1; //If the line is horizontal, row stays the same, and the column 
										// is incremented by the length of the line
		}else if (horizontal != true) {
			end = new int[2];
			end[0] = row + length - 1;
			end[1] = col;
		}
		
	}
	
	public int[] getStart() { /*Double check*/
		//Purpose is to return a copy of the start array
		int[] newArray = new int[2];
		// Use a loop to accomplish that by iterating
		// through each element of newArray, and setting it 
		// equal to the start array
		int i;
		for (i = 0; i < newArray.length; ++i) {
			newArray[i] = start[i];
		}
		return newArray;
	}
	
	public int length() { /*Double check */
		int length = 0;
		
	 //implement length calculation by using the coordinates of row and column
		if (end[0] ==start[0]) { // meaning that the line is horizontal
			if (end[1] >= start[1]) {
				length = (end[1] - start[1]) + 1;
			}
			else if (end[1] < start[1]) {
				length = (start[1] - end[1]) + 1;
			}
			return length;
		}
		
		else{
			if (end[0] >= start[0]) {
				length = (end[0] - start[0]) + 1;
			}else if (end[0] < start[0]) {
				length = (start[0] - end[0]) + 1;	
			}
			return length;
			// too many conditionals... :'(
		}
	}
		
	public boolean isHorizontal() {
		// Use if-else conditional statements to check if the end and start of the line are on the same row.
		if (end[0] == start[0]) { 	//	Means that the row of the start point is equal to the end point
			// Thus the row remains constant and does not change, meaning the line is horizontal.
			return true;
		}else {
			return false;
		}
	}
	public boolean inLine(int row, int col) {
		if (start[1] == end[1]) {//Checks if line is vertical
			// Columns remain constant, row changes
			if ((row >= start[0]) && (row <= end[0]) && (col >= start[1]) && (col <= end[1])) { //Checks if the line is in between the rows of the grid
				return true;
//						
			}else{
				return false;
			}
		}else if (start[0] == end[0]) { //Checks if line is horizontal
			//Rows remain constant, column changes
			if ((col >= start[1]) && (col <= end[1]) && (row >= start[0]) && (row <= end[0])) { //Checks if the line is in between the columns of the grid
				return true;	
			}else { //If not it returns false
				return false;
			}
		}else {
			return false;
		}
	}
			
	public String toString() { 
		return "Line:[" + start[0] +"," +  start[1] + "]->[" + end[0] + "," + end[1] + "]" ;
	}
	
}