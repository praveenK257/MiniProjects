package sudokuSolver_withoutGUI;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] input = //new int[9][9];
		
		 {
				{1,0,0,0,0,0,0,0,8},
				{0,0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0,5},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,1},
				{0,0,0,0,0,0,0,0,3},
				{0,0,0,0,0,0,0,0,7}
		}; 
		
		
		/*
		System.out.println("Enter the board values...Denote Empty boxes as \"0\"");
		
		//getting input
		for(int row=0; row<9; row++) {
			String[] ip = sc.nextLine().split(" ");
			for(int col=0; col<9; col++) {
				if(!ip[col].equals(" ")) {
					input[row][col] = Integer.parseInt(ip[col]); 
				}else {
					input[row][col] = 0;
				}
			}
		}
		*/
		
		
		//Creating board object
		Board board = new Board(input);
		board.printBoard();
		
		//seeing if the board can be solved. If not, the board is invalid.
		boolean isSolvable = board.solve();
		
		if(isSolvable) {
			System.out.println("\nYour puzzle has been solved. Your result is...");
			board.printBoard();
		}
		else {
			System.out.println("\nThe given sudoku looks to be invalid. Please try again with new values...");
		}
	}

}
