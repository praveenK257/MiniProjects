package sudokuSolver_GUI;

public class Sudoku {
	
	//array to store the puzzle values
	protected int[][] input = new int[9][9];
	
	//initialize board as the user input
	public Sudoku(int[][] input) {
		for(int row=0;row<input.length;row++) {
			for(int col=0;col<input.length;col++) {
				this.input[row][col] = input[row][col];
			}
		}
	}
  
	
	public boolean solve() {
		
		//geting the current 1st empty position to be filled
		int[] pos = getEmptyPos();
		
		//if there is no empty spots, then we would have solved the board. So return false to end the recursion.
		//This will be our base case
		if(pos[0]==-1) {
			return true;
		}
		
		//if there is some empty space, we need to place values 1-9 int that position & check if its valid
		for(int val=1; val<10; val++) {
			
			//if that val is valid at current position,
			if(isValid(val,pos)) {
				//put that val at pos
				input[pos[0]][pos[1]] = val;
				
				//call solve again.
				//if it returns true, then we would have solved the board at the final recursive call.
				if(solve()) {
					return true;	
				}
				//if it returns false, we need to put other values in curr pos, because the current val is invalid at curr pos
				//So backtracking comes into play here in this way
				else {
					//hence, change that pos back to 0
					input[pos[0]][pos[1]] = 0;
				}
			}
		}
		
		//the execution reaches this return statement, if 1 to 9 is not placeable in current position
		//So we need to return false, inorder to maintain the recursive chain
		return false;
	}
	
	public int[] getEmptyPos() {
		
		int[] pos = new int[2];
		
		//to return the 1st empty space in board (row-major order)
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[0].length; j++) {
				if(input[i][j] == 0) {
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		
		//returning [-1,-1] if no empty spaces
		pos[0] = pos[1] = -1;
		return pos;
	}
	
	public boolean isValid(int val,int[] pos) {
		
		//check if that val is in the same row as val. pos[0] denotes row
		for(int i=0; i<input.length; i++) {
			if(input[pos[0]][i] == val && i!=pos[1]) {
				return false;
			}
		}

		//check if that val is in the same col as val. pos[1] denotes col
		for(int i=0; i<input.length; i++) {
			if(input[i][pos[1]] == val && i!=pos[0]) {
				return false;
			}
		}
		
		//Now we should check if that value is already in the same subSquare as val in the board
		
		int row = pos[0]/3, col = pos[1]/3;
		
		for(int i=row*3; i<=row*3 + 2; i++) {
			for(int j=col*3; j<=col*3 + 2 ; j++) {
				if(input[i][j] == val && i!=pos[0] && j!=pos[1]) {
					return false;
				}
			}
		}
					
		//else return true, as val is valid at that position
 		return true;
	}
	
}
