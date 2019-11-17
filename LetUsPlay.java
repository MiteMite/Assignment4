//-------------------------------------------------------
//Assignment 4
//Written by: Simon Fortier Drouin 27207328
//For COMP 248 Section FF – Fall 2019
//--------------------------------------------------------
import java.util.Scanner;

public class LetUsPlay {
	private static void displayBanner() {
		System.out.println("Welcome to my game!");
	}
	private static void gameSetup() {
		System.out.print("==> What do you want to do? ");
		Scanner keyboard = new Scanner(system.in);
		Player player1=new Player();
		Player player2=new Player();
		Board board=new Board();
		Dice dice=new Dice();
	}
	public static void main(String[] args) {
		displayBanner();
		gameSetup();
	}

}
