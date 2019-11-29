//-------------------------------------------------------
//Assignment 4
//Written by: Simon Fortier Drouin 27207328
//For COMP 248 Section FF – Fall 2019
//--------------------------------------------------------
import java.util.Random;

public class Dice {
	private int die1;
	private int die2;
	Dice(){
		die1=0;
		die2=0;
	}
	public int rollDice() {
		Random d1 = new Random();
		die1=d1.nextInt(6);
		Random d2 = new Random();
		die2=d2.nextInt(6);
		return die1+die2;
	}
	public boolean isDouble() {
		if(die1==die2) {
			return true;
		}else {
			return false;
		}
	}
	public String toString() {
		String message = "Die1: "+die1+" Die2: "+die2;
		return message;
	}
	public int getDieValue() {
		return die1;
	}
}
