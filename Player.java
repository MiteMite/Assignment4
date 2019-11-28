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
	//enter player name
	Player(String n){
		this.name = n;
	}
	Player(int l, int x, int y){
		this.name = "";
		this.level = l;
		this.x = x;
		this.y = y;
		this.energy = 10;
	}
	public void moveTo(Player p) {
		this.level = p.level;
		this.x = p.x;
		this.y = p.y;
	}
	//returns if the player has reached the last spot on the board
	public boolean won(Board b) {
		if(this.level == b.getLevel()&&this.x==b.getSize()&&this.y==b.getSize()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean equals(Player p) {
		return true;
	}
	public String toString() {
		return this.name+" is on level "+this.level+" at location ("+this.x+","+this.y+") "+"and has "+this.energy+" units of energy.";
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
