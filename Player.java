//-------------------------------------------------------
//Assignment 4
//Written by: Simon Fortier Drouin 27207328
//For COMP 248 Section FF – Fall 2019
//--------------------------------------------------------
public class Player {
	private String name;
	private int level,x,y,energy;
	Player() {		//constructor
		this.name="";
		this.energy=10;
		this.x=0;
		this.y=0;
	}
	Player(Player p){	//copy constructor
		
	}
	
	public void moveTo(Player p) {
		
	}
	public void won(Board b) {
		
	}
	public boolean equals(Player p) {
		return true;
	}
	public String toString() {
		return "";
	}
	//accessors
	public int getLevel() {
		return level;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getEnergy() {
		return energy;
	}
	
	//mutators
	public void setLevel(int l) {
		level=l;
	}
	public void setX(int row) {
		x=row;
	}
	public void setY(int column) {
		y=column;
	}
	public void setEnergy(int e) {
		energy=e;
	}
}
