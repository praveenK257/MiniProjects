/* 

Marble Solitaire Game (Command Line Game)

By,

18D078 - Praveen Kumar A

THE GAME:

-> Marble Solitaire is a game, we all would have played in our childhood. 
-> It involves a circular board filled with marbles and some empty slots , we call them as holes.
-> The game starts with an empty hole at the centre and marbles all over the other slots. 
-> If we can move a marble over another marble to an empty hole, then the marble that was jumped over gets removed from the board, 
leaving a hole. 
-> The goal of the game is to test our skill to make the right moves to achieve a position where the is only one marble on the board.

THE BOARD:

        0 0 0
        0 0 0
  0 0 0 0 0 0 0 0 0
  0 0 0 0 - 0 0 0 0
  0 0 0 0 0 0 0 0 0
        0 0 0
        0 0 0
Starting position
0 -> marbles
- -> hole

We win, if we can reverse the board's contents...

*/

#include <stdio.h>
#include <stdlib.h>

//Copy of the board
char tempboard[8][8]= {{' ','1','2','3','4','5','6','7'},{'1',' ',' ','O','O','O',' ',' '},{'2',' ',' ','O','O','O',' ',' '},{'3','O','O','O','O','O','O','O'},{'4','O','O','O','X','O','O','O'},{'5','O','O','O','O','O','O','O'},{'6',' ',' ','O','O','O',' ',' '},{'7',' ',' ','O','O','O',' ',' '}};	
		
//row&col size of the board
const int M=8;
const int N=8;

int moves=0;

//function to display the board
void printBoard(char tempboard[][M])
{
	int i,j;
	
	for(i=0;i<M;i++)
	{
		for(j=0;j<M;j++)
		{
			printf("%c ",tempboard[i][j]);
		}
		printf("\n");
	}
}	

//function to apply the move to the board , after validating
void makeMove(char tempboard[][M],int Mrow,int Mcol,int Hrow,int Hcol)
{
	
	int midR=(Hrow>Mrow)? Hrow-1 : Mrow-1;
	int midC=(Hcol>Mcol)? Hcol-1 : Mcol-1;
	
	tempboard[Mrow][Mcol]='X';
	tempboard[Hrow][Hcol]='O';
	
	if(Hrow==Mrow && Hcol!=Mcol)
		tempboard[Hrow][midC]='X';
	
	if(Hrow!=Mrow && Hcol==Mcol)
		tempboard[midR][Hcol]='X';
	
}

//function to get the move, as input from the user
void getMove(int* Mrow,int* Mcol,int* Hrow,int* Hcol)
{
	int temp;
	
	printf("\nSelect Marble to move by entering its Row and Column...\n ");
	printf("  Row - ");
	scanf("%d",&temp);
	*Mrow=temp;
	printf("\nColumn - ");
	scanf("%d",&temp);
	*Mcol=temp;
	
	printf("\nSelect Hole, where to move the marble by entering its Row and Column...\n ");
	printf("  Row - ");
	scanf("%d",&temp);
	*Hrow=temp;
	
	printf("\nColumn - ");
	scanf("%d",&temp);
	*Hcol=temp;
}

//Helper function to call makeMove after validation
void playMove(char tempboard[][M],int Mrow,int Mcol,int Hrow,int Hcol,int* flag)
{
	int check=validateMove(tempboard,Mrow,Mcol,Hrow,Hcol);
	
	if(check)
	{
		makeMove(tempboard,Mrow,Mcol,Hrow,Hcol);
	}
	else
	{
		*flag=1;
		printf("\nInvalid Move. Try entering again..\n");
	}
}

//function to validate the move
int validateMove(char tempboard[][M],int Mrow,int Mcol,int Hrow,int Hcol)
{
	int temp,temp1;
	
	
	if(Hrow==Mrow)
	{
		temp= (Hcol>Mcol) ? Hcol-Mcol : Mcol-Hcol;
		if(temp==2)
		{
			temp1= (Hrow>Mrow) ? Hrow : Mrow;
			if(tempboard[Hrow][Hcol]=='X' && tempboard[Mrow][Mcol]=='O' && tempboard[Mrow][temp1-1]=='O' )
				return 1;
		}
		else
			return 0;
	}
	//Vertical
	else if(Hcol==Mcol)
	{
		temp= (Hrow>Mrow) ? Hrow-Mrow : Mrow-Hrow;
		if(temp==2)
		{
			temp1= (Hrow>Mrow) ? Hrow : Mrow;
			if(tempboard[Hrow][Hcol]=='X' && tempboard[Mrow][Mcol]=='O' && tempboard[temp1-1][Mcol]=='O' )
				return 1;
		}
		else
			return 0;	
	}
	else return 0;
}

//threshold of number of moves available
void checkValidMoves(int *MovesAvailable)
{
	*MovesAvailable=31-moves;
}

//Function to help start the game
void startGame(char tempboard[][M], int* canContinue)
{
	int flag=0, Mrow=0,Mcol=0,Hrow=0,Hcol=0,MovesAvailable=31-moves;
	
	getMove(&Mrow,&Mcol,&Hrow,&Hcol);
	
	playMove(tempboard,Mrow,Mcol,Hrow,Hcol,&flag);
	
	if(flag==1)
	{
		flag=0;
		startGame(tempboard,&canContinue);
	}
	
	moves++;
	printf("Move - %d \n",moves);
	printBoard(tempboard);
	
	checkValidMoves(&MovesAvailable);
	
	if(MovesAvailable>0)
		*canContinue=1;
	else
		*canContinue=0;
	
}

//function to print greeting message when user exits
void printresult(char tempboard[][M])
{
	int k,l,MarbleCount=0;
	
	for(k=0;k<M;k++)
	{
		for(l=0;l<M;l++)
		{
			if(tempboard[k][l]=='O')
				MarbleCount++;
		}
	}
	
	if(MarbleCount==1)
		printf("\n\n Congrats you have won the game....Thank you for playing.\n");
	else
		printf("\n\n You have ran out of moves...\n%d marbles left. Thank you for playing.\n Try again for a better result...\n",MarbleCount);

}

int main()
{
	int choice, canContinue=1;
	
	char board[8][8]= {{' ','1','2','3','4','5','6','7'},{'1',' ',' ','O','O','O',' ',' '},{'2',' ',' ','O','O','O',' ',' '},{'3','O','O','O','O','O','O','O'},{'4','O','O','O','X','O','O','O'},{'5','O','O','O','O','O','O','O'},{'6',' ',' ','O','O','O',' ',' '},{'7',' ',' ','O','O','O',' ',' '}};	
	printf("<<< @praveenK257 Creations ( DSA miniproject ) >>> \n\n<<< Welcome to the Marble Solitaire >>> \n\n");
	printf("<<< Here are the rules >>> \n\n 1) You can move one marble at a time. \n 2) O - denotes marbles.\n    X - denotes holes . \n 3) Move a marble over another marble to place it in a hole.\n 4) This leaves the position of the marble that is jumped over, with a hole.\n 5) Only Up-Down-Right-Left movements/jumps are allowed . \n 6) Ending the game with a single marble on the board == Victory .\n 7) You have only 31 moves to make it happen. \n\n    O - denotes marbles.\n    X - denotes holes . \n\nTest your brain. Lets goooo! .\n\n");
	
	printBoard(board);
	
  //loop to run the game ,till user want to play
	while(1)
	{	
		printf("\n\t1 - Play\n\t2 - Exit\n Enter your Choice... ");
		scanf("%d",&choice);
		printf("\n");
		switch(choice)
		{
			case 1:
				while(canContinue)
					startGame(tempboard,&canContinue);
				printresult(tempboard);
				break;
				
			case 2: return 0;
				
			default:
				printf("Invalid input. CHoose from the available options.\n");
		}
	}
	return 0;
}

