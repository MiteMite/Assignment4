//-------------------------------------------------------
//Assignment 4
//Written by: Simon Fortier Drouin 27207328
//For COMP 248 Section FF – Fall 2019
//--------------------------------------------------------
import java.util.Scanner;

public class LetUsPlay {
	Player player1;
	Player player2;
	int sizeSelection;
	boolean invalid=true;
	Board board = new Board();
	private static void displayBanner() {
		System.out.println("Welcome to my game!");
	}
	private void gameSetup() {
		
		System.out.print("==> What do you want to do? ");
		Scanner keyboard = new Scanner(System.in);

		//check for valid board size
		while(invalid) {
			sizeSelection=keyboard.nextInt();
			if(sizeSelection==0||sizeSelection==-1) {
				if(sizeSelection==0) {
					board=new Board();
				}
				if(sizeSelection==-1) {
					boolean invalidSize=true;
					while(invalidSize) {
						System.out.print("How many levels would you like? (minimum size 3, max 10) ");
						int boardSize = keyboard.nextInt();
						if(boardSize<3||boardSize>10) {
							System.out.println("Sorry but "+boardSize+" is not a legal choice.");
						}else {
							board = new Board(boardSize,boardSize);
							invalidSize=false;
						}
					}

				}
				invalid=false;
			}else {
				System.out.println("Sorry but "+sizeSelection+" is not a legal choice");
			}
		}
		
		//display 3D board
		System.out.println("Your 3D board has been set up and looks like this: \n");
		System.out.println("size of instanced board "+board.getSize());
		for(int level=0;level<board.getLevel();++level) {
			System.out.println("Level "+level+"\n"+"________\n");
			for(int row=0; row<board.getSize()-1;++row) {
				for(int column=0; column<board.getSize()-1;++column) {
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
