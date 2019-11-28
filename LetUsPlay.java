//-------------------------------------------------------
//Assignment 4
//Written by: Simon Fortier Drouin 27207328
//For COMP 248 Section FF – Fall 2019
//--------------------------------------------------------
import java.util.Scanner;

public class LetUsPlay {
	Player player1;
	Player player2;
	int levelSelection;
	int sizeSelection;
	boolean boardSelection=true;
	int levelNumber;
	//boolean invalidLevel=true;
	//boolean invalidSize=true;
	Board board = new Board();
	private static void displayBanner() {
		System.out.println("Welcome to my game!");
	}
	private void gameSetup() {
		
		System.out.print("==> What do you want to do? ");
		Scanner keyboard = new Scanner(System.in);

		//check for valid level amount
		while(boardSelection) {
			levelSelection=keyboard.nextInt();
			if(levelSelection==0||levelSelection==-1) {
				if(levelSelection==0) {
					board=new Board();
				}
				if(levelSelection==-1) {
					//check for valid level amount
					boolean invalidLevel=true;
					while(invalidLevel) {
						System.out.print("How many levels would you like? (minimum size 3, max 10) ");
						levelNumber = keyboard.nextInt();
						if(levelNumber<3||levelNumber>10) {
							System.out.println("Sorry but "+levelNumber+" is not a legal choice.");
						}else {
							invalidLevel=false;
						}
					}
					//check for valid board size
					boolean invalidSize=true;
					while(invalidSize) {
						System.out.print("What size do you want the nxn boards on each level to be? \n");
						System.out.println("Minimum size is 4x4, max is 10x10. ");
						System.out.print("==> enter the value of n: ");
						sizeSelection=keyboard.nextInt();
						if(sizeSelection<4||sizeSelection>10) {
							System.out.println("Sorry but  "+sizeSelection+" is not a legal choice.");
						}else {
							invalidSize=false;
						}
					}
					board = new Board(levelNumber,sizeSelection);
				}
				boardSelection=false;
			}else {
				System.out.println("Sorry but "+levelSelection+" is not a legal choice");
			}

		}

		
		//display 3D board
		System.out.println("Your 3D board has been set up and looks like this: \n");
		System.out.println("size of instanced board "+board.getSize());
		for(int level=0;level<board.getLevel();level++) {
			System.out.println("Level "+level+"\n"+"________\n");
			for(int row=0; row<board.getSize()-1;row++) {
				for(int column=0; column<board.getSize()-1;column++) {
					System.out.print(board.getEnergyAdj(level, row, column)+"\t");
				}
			}
		}

		Dice dice=new Dice();
		Player player1=new Player(keyboard.next());
		Player player2=new Player(keyboard.next());

		System.out.println(player1.toString());
		System.out.println(player2.toString());
		keyboard.close();
	}
	public static void main(String[] args) {
		LetUsPlay play = new LetUsPlay();
		displayBanner();
		play.gameSetup();
	}

}
