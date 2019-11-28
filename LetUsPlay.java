//-------------------------------------------------------
//Assignment 4
//Written by: Simon Fortier Drouin 27207328
//For COMP 248 Section FF – Fall 2019
//--------------------------------------------------------
import java.util.Random;
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
	Board board;
	
	boolean playing = true;
	
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
		System.out.print("Your 3D board has been set up and looks like this: ");
		for(int lvl=0;lvl<board.getLevel();lvl++) {
			System.out.println("\n\nLevel "+lvl+"\n"+"________");
			for(int row=0; row<board.getSize();row++) {
				if(row>=0) {
					System.out.println("");
				}
				for(int column=0; column<board.getSize();column++) {
					System.out.print(board.getEnergyAdj(lvl, row, column)+"\t");
				}
			}
		}

		Dice dice=new Dice();
		System.out.print("\n\nWhat is player 0's name (one word only): ");
		Player player1=new Player(keyboard.next());
		System.out.print("\nWhat is player 1's name (one word only): ");
		Player player2=new Player(keyboard.next());
		Player[] playerArray = {player1,player2};
		
		//who goes first?
		switch(new Random().nextInt(1)) {
			case 0:
				System.out.println("\nThe game has started "+playerArray[0].getName()+" goes first\n"+"=============================\n");
				playerArray[0]=player1;
				playerArray[1]=player2;
				break;
			case 1:
				System.out.println("\nThe game has started "+playerArray[1].getName()+" goes first\n"+"=============================\n");
				playerArray[0]=player2;
				playerArray[1]=player1;
				break;
		}
		
		
		while(playing) {
			for(Player player : playerArray) {
				if(player.getEnergy()<=0) {
					for(int i=0;i<3;i++) {
						dice.rollDice();
						
					}
				}
				System.out.println(player.toString());
			}
		}

		
		keyboard.close();
	}
	public static void main(String[] args) {
		LetUsPlay play = new LetUsPlay();
		displayBanner();
		play.gameSetup();
	}

}
