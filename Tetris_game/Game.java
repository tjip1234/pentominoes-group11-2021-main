import java.util.Scanner;
import javax.swing.Timer;
import java.awt.event.*;
//import javax.lang.model.util.ElementScanner14;

//import java.io.FileNotFoundException;
//mport java.io.UnsupportedEncodingException;
//import java.lang.reflect.Field;
import java.util.Arrays;

public class Game
{
	
	
    public static final int horizontalGridSize = 5;
    public static final int verticalGridSize = 13;

    //public static final char[] input = { 'W', 'Y', 'I', 'T', 'Z', 'L', 'X', 'U', 'V', 'P', 'N', 'F'};
    //Static UI class to display the board
	public static int[][] activefield;
    public static UI ui;
	public static ActivePentomino activepentomino;
    public static void gameLoop(int[][] field)
    {
		Scanner scanner = new Scanner(System.in);
		boolean pieceplaced = false;
		while(checkIfColumnFull(field)){
			activepentomino = new ActivePentomino();
			pieceplaced = false;
			addPiece(activefield, activepentomino.getPiece(), activepentomino.getPieceId(), activepentomino.GetXPosition(), activepentomino.GetYPosition());
			ui.setState(activefield);
			while (!pieceplaced) {
				char input = scanner.nextLine().charAt(0);
				pieceplaced = shiftRotatePiece(field, inputToInt(input));
				ui.setState(activefield);
			}
			checkIfRowFull(field);
			ui.setState(field);
		}
		scanner.close();
    }
public static int inputToInt(char input){
	switch (input) {
		case 'a':
			return 0;
		case 's':
			return 2;
		case 'd':
			return 1;
		case ' ':
			return 3;	
		case 'f':
			return 4;	
		case 'g':
			return 5;
	}
	return -1;
}
	/**
 * user inputs
 * @param field the main field
 * @param direction 0 for left, 1 for right, 2 for down, 3 for all the way down 4 clockwise rotation 5 anti clockwise rotation
 */
public static boolean shiftRotatePiece(int[][] field, int direction){
	int preY = activepentomino.GetYPosition();
	int preX = activepentomino.GetXPosition();
	switch (direction) {
		case 0:
			activepentomino.shiftLeft();
			return checkPiecePosition(field, preX, preY);
		case 1:
			activepentomino.shiftRight();
			return checkPiecePosition(field, preX, preY);
		case 2:
			activepentomino.shiftDown();
			return checkPiecePosition(field, preX, preY);
		case 3:
		case 4:
			activepentomino.rotateClockwise();
			return checkPiecePositionNormalcorrect(field, preX, preY);
		case 5:
			activepentomino.rotateAntiClockwise();
			return checkPiecePositionAnticorrect(field, preX, preY);
	}
	return false;
}
/**
 * TODO make it so that it ignores the vertical sides with placing the piece
 * @param field
 * @param preX
 * @param preY
 * @return
 */
public static boolean checkPiecePosition(int[][] field, int preX, int preY){
	int x = activepentomino.GetXPosition();
	int [][] piece = activepentomino.getPiece();

		if (checkIfFits(field, activepentomino.getPiece(), activepentomino.GetXPosition(), activepentomino.GetYPosition())) {
			activefield = cloneTwoDimensional(field);
			addPiece(activefield, activepentomino.getPiece(), activepentomino.getPieceId(), activepentomino.GetXPosition(), activepentomino.GetYPosition());
			return false;
		}
		else if(x + piece.length > field.length || x < 0) {
			activefield = cloneTwoDimensional(field);
			activepentomino.takeXPositon(preX);
			activepentomino.takeYPositon(preY);
			addPiece(activefield, activepentomino.getPiece(), activepentomino.getPieceId(), preX, preY);
			System.out.println(Arrays.deepToString(field));
			return false;
		}		
		else{
			addPiece(field, activepentomino.getPiece(), activepentomino.getPieceId(), preX, preY);
			activepentomino.takeXPositon(preX);
			activepentomino.takeYPositon(preY);
			System.out.println("Is it working");
			return true;
		}
}
public static boolean checkPiecePositionAnticorrect(int[][] field, int preX, int preY){
	if (checkIfFits(field, activepentomino.getPiece(), activepentomino.GetXPosition(), activepentomino.GetYPosition())) {
		activefield = cloneTwoDimensional(field);
		addPiece(activefield, activepentomino.getPiece(), activepentomino.getPieceId(), activepentomino.GetXPosition(), activepentomino.GetYPosition());
		return false;
	}
	else{
		activepentomino.rotateClockwise();
		addPiece(field, activepentomino.getPiece(), activepentomino.getPieceId(), preX, preY);
		activepentomino.takeXPositon(preX);
		activepentomino.takeXPositon(preY);
		return true;
	}
}
public static boolean checkPiecePositionNormalcorrect(int[][] field, int preX, int preY){
	if (checkIfFits(field, activepentomino.getPiece(), activepentomino.GetXPosition(), activepentomino.GetYPosition())) {
		activefield = cloneTwoDimensional(field);
		addPiece(activefield, activepentomino.getPiece(), activepentomino.getPieceId(), activepentomino.GetXPosition(), activepentomino.GetYPosition());
		return false;
	}
	else{
		activepentomino.rotateAntiClockwise();
		addPiece(field, activepentomino.getPiece(), activepentomino.getPieceId(), preX, preY);
		activepentomino.takeXPositon(preX);
		activepentomino.takeXPositon(preY);
		return true;
	}
}
/** 
 * generates a random piece on top of the field 
 * @param field the main field
*/
/*
returns true when no clomun is completely filled
*///TODO make the function and implement in main loop
public static boolean checkIfColumnFull(int[][] field){
	return true;
}
/** 
 * the number of the row which is filled, if there is no row filled return 0
 * @param field the field
 * @return the int of the row
 * TODO make the function and implement in main loop
*/
public static void checkIfRowFull(int[][] field){
	for(int j = 0; j < verticalGridSize; j++){
		int filledCounter = 0;
		for(int i = 0; i < horizontalGridSize; i++)
		{
			if(field[i][j] > -1) filledCounter++;
		}
		// if the whole row is filled we need to remove it
		if(filledCounter == horizontalGridSize){
			
			for(int k = j; k > 0; k--){
				for(int i = horizontalGridSize - 1; i >= 0; i--){
					field[i][k] = field[i][k-1];
				}
			}
			for(int i = 0; i < horizontalGridSize; i++)
				field[i][0] = -1;
		}
	}
}

	/**
	 * Get as input the character representation of a pentomino and translate it into its corresponding numerical value (ID)
	 * @param character a character representating a pentomino
	 * @return	the corresponding ID (numerical value)
	 */
/*private static int characterToID(char character) {
    	int pentID = -1; 
    	if (character == 'X') {
    		pentID = 0;
    	} else if (character == 'I') {
    		pentID = 1;
    	} else if (character == 'Z') {
    		pentID = 2;
    	} else if (character == 'T') {
    		pentID = 3;
    	} else if (character == 'U') {
    		pentID = 4;
     	} else if (character == 'V') {
     		pentID = 5;
     	} else if (character == 'W') {
     		pentID = 6;
     	} else if (character == 'Y') {
     		pentID = 7;
    	} else if (character == 'L') {
    		pentID = 8;
    	} else if (character == 'P') {
    		pentID = 9;
    	} else if (character == 'N') {
    		pentID = 10;
    	} else if (character == 'F') {
    		pentID = 11;
    	} 
    	return pentID;
    }
	*/
/**
 *  checks if the piece fits 
 * @param field the field
 * @param piece the piece
 * @param x x coordinate
 * @param y y coordinate
 * @return
 *
 */
public static boolean checkIfFits(int[][] field, int[][] piece, int x, int y){
		for(int i = 0; i < piece.length; i++) // loop over y position of pentomino
		{
			for (int j = 0; j < piece[i].length; j++) // loop over x position of pentomino
			{   
				if(piece[i][j] == 1 && x < 0) {
					return false;
				}
				else if (piece[i][j] == 1 && (x + i >= field.length || y + j >= field[0].length || field[x + i][y + j] != -1)){
					//System.out.println(Arrays.deepToString(field));
					return false;
				}
			}
		}
		return true;
	}
public static int[][] cloneTwoDimensional(int[][] array){
		int[][] clone = new int[array.length][];
		for(int i = 0; i < array.length; i++){
			clone[i]=array[i].clone();
		}
		return clone;
	}  
	/**
	 * Adds a pentomino to the position on the field (overriding current board at that position)
	 * @param field a matrix representing the board to be fulfilled with pentominoes
	 * @param piece a matrix representing the pentomino to be placed in the board
	 * @param pieceID ID of the relevant pentomino
	 * @param x x position of the pentomino
	 * @param y y position of the pentomino
	 */
public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y)
    {
        for(int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[i].length; j++) // loop over y position of pentomino
            {
                if (piece[i][j] == 1)
                {
                    // Add the ID of the pentomino to the board if the pentomino occupies this square
					
                    field[x + i][y + j] = pieceID;
					System.out.println("x: " + x + " y:" + y);
                }
            }
        }
    }

	/**
	 * Main function. Needs to be executed to start the basic search algorithm
	 */
    public static void main(String[] args)
    {
		int[][] field = new int[horizontalGridSize][verticalGridSize];
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[i].length; j++)
            {
                // -1 in the state matrix corresponds to empty square
                // Any positive number identifies the ID of the pentomino
            	field[i][j] = -1;
            }
        }
		activefield = cloneTwoDimensional(field);
		ui = new UI(horizontalGridSize, verticalGridSize, 50);
        gameLoop(field);
				///////////////////////////////////
				class MoveDownAuto implements ActionListener{
					public MoveDownAuto(int initialCount){
						count = initialCount;
					}
					public void actionPerformed(ActionEvent event){
						activepentomino.takeXPositon(activepentomino.GetXPosition() - 1);
						activepentomino.takeYPositon(activepentomino.GetYPosition() - 1);
					}
					private int count;
				}
				System.out.println("Start");
				MoveDownAuto listener = new MoveDownAuto(10);
				final int DELAY = 1000;
				Timer t = new Timer(DELAY, listener);
				t.start();
				System.out.println("Done");
				while(true);
				//////////////////////////////////////////
    }
}
