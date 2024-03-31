
public class LetterCrush {
	// We want to initialize the grid for the letter crush game
	private char[][] grid; // Array uses characters
	public static final char EMPTY = ' ';
	
	public LetterCrush(int width, int height, String inital) { //Constructor
		grid = new char[height][width]; // Number of rows and columns - Grid dimensions
		//Use a for loop to iterate through each column and row of the grid and
		int stringCounter = 0;
		int i;
		for (i = 0; i < height; i++) { // Rows
			for(int j = 0; j < width; j++) { //Columns
        //StringCounter +=  1;// This counter is used as a placeholder for the letters in the array.
				grid[i][j] = EMPTY;
				// i keeps track of rows, j keeps track of columns
				
//		
				}
//				grid[i][j] = inital.charAt(stringCounter);
			}
		int rows;
		int cols;
		outerRows:
		for (rows = 0; rows < height; rows++) {
			for (cols = 0; cols <width; cols++) { // Keeps track of rows and columns in the code
				 // Counter to keep track of the characters in the string
					
				if (stringCounter < inital.length()) {  // If the counter is equal to the length of the string, it resets to 0
					grid[rows][cols] = inital.charAt(stringCounter);
					stringCounter++;
					// Adds the character of the string into the grid
				}else {
					break outerRows;
				}
			}
		}
	}
	
		
	public String toString() {
	
		String  str = "LetterCrush\n"; //Initialize string
		for (int rows = 0; rows < grid.length; rows++) { // Use for loops to iterate through the rows and columns of the grid.
			str += "|";
			for (int cols = 0; cols < grid[rows].length; cols++) {
				str += grid[rows][cols];
			}
			str+= "|" + rows + "\n";  // use /n to go the next line of the grid
		}
		str+= "+";
		for (int i = 0; i < grid[0].length; i++) {
			str +=  i; // Counter for the # of columns of letters in the grid
		}
		str+= "+";
		return str;		
	}
		
	

	public boolean isStable() {
		
		boolean stability = true;
		for (int i = grid.length - 1; i > 0 ; i --) { // Iterate through the rows starting at the bottom by setting i = to the length of the grid
			for (int j = 0; j < grid[i].length; j++) { // Iterate through each column starting at the first column.
				if (grid[i][j] == EMPTY) { // If the position at the grid is empty stability is set to true
					stability = true;
					
					if (grid[i-1][j] != EMPTY) { // If the position above the grid does not have an empty character
						stability = false;
					}
				}
				
			}
		}
		return stability;
	}
	public void applyGravity() {
		for (int i =  grid.length - 1; i > 0; i-- ) { // Starts at the bottom row and iterates if
			for (int j = 0; j < grid[i].length; j++) { // Starts at the first column and moves from left to right
				if (grid[i][j] == EMPTY) { // Checks if the position on the grid is empty
					grid[i][j] = grid[i-1][j]; // Replaces the position of the grid on the bottom with the position on the top
					grid[i-1][j] = EMPTY;
				}
			}
		}
	}	
	
	
	public boolean remove(Line theLine) {
		
		int[] start = theLine.getStart(); 
		// Create the start method and
		int[] end = new int[2];
		int length = theLine.length();
		
		
		if (theLine.isHorizontal() == true) { // First we need to determine the orientation of the line whether it is horizontal or vertical
			end[0] = start[0];  // Row remains constant
			end[1] = (start[1] + length) - 1 ; // Calculate end column.
		}else {
			end[0] = (start[0] + length) - 1; //Calculate end row.
			end[1] = start[1]; // Row remains constant.
		}
		boolean remove = true; // Then use if statements to check the validity of the line
		if ((start[0] < 0)) {
			remove = false;
		} if (end[0] >= grid.length) {
			remove = false;
		} if ((start[1] < 0)) {
			remove = false;
		}if (end[1] >= grid[0].length) {
			remove = false;
		}
		
		if (remove == true) {// If the line is valid check orientation of line, iterate through the grid 
			/*If the line is horizontal
			 * Goes through the columns. Set the columns of the grid to start at the start of the line
			 * and the conditional at the end of the line and increment col by one.
			 */ 
			if (theLine.isHorizontal() == true) {
				for (int col = start[1]; col <= end[1]; col++) {
					grid[start[0]][col] = EMPTY;// Replace the line with empty spaces
				}
				
				/*If line is vertical set the row = the start of the line and the conditional
				 * where row is less than the end point of the line
				 */
			}else if (theLine.isHorizontal() == false){  
				for (int row = start[0]; row <= end[0]; row++) {
					grid[row][start[1]] = EMPTY; //Replace where the line is on the grid with empty spaces
				}
			}
		}
		return remove;
	}
	
	public String toString(Line theLine) {
		String  str = "CrushLine\n"; //Initialize the grid similar to the toString method.
		for (int rows = 0; rows < grid.length; rows++) {
			str += "|";
			for (int cols = 0; cols < grid[rows].length; cols++) {
				if (theLine.inLine(rows, cols)) { // Checks if the characters is in the line.
					str += Character.toLowerCase(grid[rows][cols]);  // Changes the characters on the grid from upperCase to Lowercase,
				}else {
					str += grid[rows][cols];
				}
			}
			str+= "|" + rows + "\n";
		}
		str+= "+";
		for (int i = 0; i < grid[0].length; i++) {
			str +=  i;
		}
		str+= "+";	

		return str;
	}
	
	public Line longestLine() { // Creates a line a long the grid with a least 3 adjacent matching letters.
		Line longLine = new Line(0, 0, true, 1); // Create a line using the Line class.
		int largest = 0;
		
		
		
		for (int i = grid.length - 1; i >= 0; i--) { //Iterate through the rows starting at the bottom making its way to the top
			char letter = grid[i][0]; // Set the letter variable = to that specific position of the grid starting at the leftmost column through i rows
			int adjacent = 1;
			
			for (int j = 1; j < grid[0].length; j++) { // Rinse and repeat and iterate through the columns
				if ((grid[i][j] == letter) && (letter != EMPTY)) { // Checks if a letter is at the position
					adjacent++; // Increases adjacent with 1, and replace it with the largest value so we can keep track of the length of the longest line of characters
					if (adjacent > largest) {
						largest = adjacent; 
						longLine = new Line(i, j - adjacent + 1, true, adjacent); 
					}
				}else {
					letter = grid[i][j]; 
					adjacent = 1;
				}
			}
		}
		for (int j = 0; j < grid[0].length; j++) { // Iterates through the columns starting from left to right
			char letter = grid[grid.length - 1][j]; // 
			int adjacent = 1;
			
			for (int i = grid.length - 2; i >= 0; i--) {
				if (grid[i][j] == letter && letter != EMPTY){
					adjacent++;
					if (adjacent > largest) { 
						largest = adjacent; // 
						longLine = new Line(i, j, false, adjacent);
					}
					
				} else {
					letter = grid[i][j];
					adjacent = 1;
				}
			}
		}
		if (longLine.length() > 2) { // Checks if the length of the line is greater than 2 and then returns that line.
			return longLine;
		}else {
			return null; // If no other line that has a sequence of 3 characters exist in the grid. Than it returns null
		}
	}
	public void cascade() {
		while (longestLine() != null) { // Checks if the longest line exists using the longestLine method. i.e length > 2
			remove(longestLine()); // Afterwards it removes that line using the @public boolean remove method.
			while (!isStable()) // Use a nested while loop to continously check for stability after the line is removed, and then
			 	applyGravity();	 // Apply gravity to pull the empty spaces above down.
			}
			
		}
}

