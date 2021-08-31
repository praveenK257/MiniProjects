package sudokuSolver_GUI;

import javax.swing.*;
import java.awt.event.*;

public class Main{

	public static void main(String[] args) {
		
		/*
		 * @praveenK257
		 * 
		 */
		
		int[][] puzzle = new int[9][9];
		
		
		JFrame frame = new JFrame("Sudoku Solver - Praveen Kumar");
		frame.setSize(600,600);
		frame.setLayout(null);
		
		
		JButton SolveButton, ExitButton, ResetButton;
		
		SolveButton = new JButton("SOLVE");
		SolveButton.setBounds(230,500,100,30);
		
		ResetButton = new JButton("RESET");
		ResetButton.setBounds(40,500,100,30);

		ExitButton = new JButton("EXIT");
		ExitButton.setBounds(435,500,100,30);

		frame.add(ResetButton);
		frame.add(SolveButton);
		frame.add(ExitButton);
		
		int posX= 130, posY= 30;
		
		JTextField[][] sudokuEntry = new JTextField[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				sudokuEntry[i][j] = new JTextField(String.valueOf(0));
				sudokuEntry[i][j].setBounds(posX,posY,30,30);
				posX+=35;
				frame.add(sudokuEntry[i][j]);
			}
			posX=130;
			posY+=40;
		}
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				frame.add(sudokuEntry[i][j]);
			}
		}
		
		JTextField message = new JTextField(" ");
		message.setBounds(160,400,250,50);
		
		frame.add(message);
		frame.setVisible(true);

		SolveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				boolean invalidInput = false;

				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {
						puzzle[i][j] = Integer.valueOf(sudokuEntry[i][j].getText());
						if(! (puzzle[i][j]>=0 && puzzle[i][j]<=9 )) {
							invalidInput = true;
							break;
						}
					}
					if(invalidInput) {
						break;
					}
				}

				//Creating board object
				Sudoku s = new Sudoku(puzzle);
				
				boolean solvable;
				
				if(invalidInput) {
					solvable = false;
				}
				else {
					solvable = s.solve();
				}
				
				if(solvable) {
					
					message.setText("Your puzzle has been solved...");
					
					for(int i=0;i<9;i++) {
						for(int j=0;j<9;j++) {
							sudokuEntry[i][j].setText( String.valueOf(s.input[i][j]) );
						}
					}
				}	
				else {
					message.setText("Puzzle is invalid. Try again...");	
				}
			}
		});
		

		ResetButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {
						sudokuEntry[i][j].setText(String.valueOf(0));
					}
				}
				message.setText(" ");
			}
		
		});
		

		ExitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		
		});
		
		
	}

}
