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
		System.out.println("\tWelcome to my game!\n"
	+"\t===================");
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
		
		//boolean firstTurn=true;
		while(playing) {
			for(Player player : playerArray) {

					if(player.getEnergy()<=0) {
						for(int i=0;i<3;i++) {
							dice.rollDice();
							if(dice.isDouble()) {
								player.setEnergy(2);
							}
						}
					
					//if after 3 rolls still 0 energy, player is too weak to move so go to next player
					if(player.getEnergy()<=0) {
						continue;
					}
				}
				System.out.println("It is "+player.getName()+"'s turn");
				//player rolls dice
				dice.rollDice();
				if(dice.isDouble()) {
					player.setEnergy(2);
					System.out.println(player.getName()+" you rolled "+dice.toString());
					System.out.println("Congratulations you rolled double "+dice.getDieValue()+" your energy went up by 2 units");
				}else {
					System.out.println("You rolled "+dice.toString());
				}
				
				//calculate new potential location
				int potentialX=player.getEnergy()/board.getSize();
				int potentialY=player.getEnergy()%board.getSize();
				
				//check if potential position is off board
				if(player.getX()+potentialX<=board.getSize()&&player.getY()+potentialY<=board.getSize()) {
					player.setX(player.getX()+potentialX);
					player.setY(player.getY()+potentialY);	
				}else {
					player.setX(potentialX/board.getSize());
					player.setY(potentialY%board.getSize());
					if(player.getLevel()<board.getLevel()) {
						player.setLevel(player.getLevel()+1);
					}
				}
				
				//check if other player is at same potential position
				for(int i=0;i<playerArray.length;i++) {
					if(player.getName().compareTo(playerArray[i].getName())!=0) {
						if(player.getX()==playerArray[i].getX()&&player.getY()==playerArray[i].getY()&&player.getLevel()==playerArray[i].getLevel()) {
							//check if both are not at zero
							if(player.getX()!=0&&player.getY()!=0&&player.getLevel()!=0) {
								System.out.println("\nPlayer "
										+playerArray[i]
										+" is at your new location\n what do you want to do?\n"
										+ "0 - Challenge and risk loosing 50% of your energy units if you lose\n or move to a new location and get 50% of other players energy units\n"
										+"1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy units");
								boolean badChoice=true;
								String choice ="";
								while(badChoice) {
									choice = keyboard.next();
									if(choice.charAt(0)!='0'&&choice.charAt(0)!='1') {
										System.out.println("Sorry but "+choice+" is not a valid choice");
									}else {
										break;
									}
								}

								switch(Integer.parseInt(choice)) {
									case 0:
										if(new Random().nextInt(10)<6) {
											//swap players positions
											Player temp = player;
											player.moveTo(playerArray[i]);
											playerArray[i].moveTo(temp);
											player.setEnergy(playerArray[i].getEnergy()/2); //give half energy to winner
											playerArray[i].setEnergy(-playerArray[i].getEnergy()/2); //lose half energy
											System.out.print("Bravo!!! you won the challenge.");
										}else {
											player.setEnergy(player.getEnergy()/2); //lost challenge lose half energy
										}
										break;
									case 1:
										if(player.getLevel()>=0) {
											player.setX(0);
											player.setY(0);
										}else {
											player.setLevel(-1);
										}
										break;
									default:
										continue;
								}
							}
						}
					}
				}
				

				//adjust index
				int indeX=0;
				int indeY=0;
				int indeL=0;
				if(player.getLevel()>0&&player.getLevel()<=board.getLevel()) {
					indeL=1;
				}
				if(player.getX()>0&&player.getX()<=board.getSize()) {
					indeX=1;
				}
				if(player.getY()>0&&player.getY()<=board.getSize()) {
					indeY=1;
				}
				if(indeX!=0||indeY!=0||indeL!=0) {
					player.setEnergy(board.getEnergyAdj(player.getLevel()-indeL, player.getX()-indeX, player.getY()-indeY));
					System.out.println("Your energy is adjusted by "+board.getEnergyAdj(player.getLevel()-indeL, player.getX()-indeX, player.getY()-indeY)+" for landing at ("+player.getX()+","+player.getY()+")"+" at level "+player.getLevel());
				}
				System.out.println(player.toString()+"\n");
				if(playing) {
					System.out.println("At the end of this round:");
					System.out.println("\t"+playerArray[0].toString());
					System.out.println("\t"+playerArray[1].toString());
					System.out.print("Any key to continue to next round ...\n");
					//keyboard.next(); //uncomment to stop at each iteration
					System.out.println("");
				}
				if(player.won(board)) {
					System.out.println("The winner is player "+player.getName()+". Well done!!!");
					playing = false;
					break;
				}
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
