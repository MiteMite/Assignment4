//-------------------------------------------------------
//Assignment 4
//Written by: Simon Fortier Drouin 27207328
//For COMP 248 Section FF – Fall 2019
//--------------------------------------------------------
public class Board {
	//private static final int MIN_LEVEL = 3;
	//private static final int MIN_SIZE = 3;
	private int[][][] board ;
	private int level = 0;
	private int size = 0;
	
	Board(){
		this.level=3;
		this.size=4;
		board = new int[this.level][this.size][this.size];
		createBoard(this.level, this.size);
	}
	Board(int l, int x){
		this.level=l;
		this.size=x;
		board = new int[this.level][this.size][this.size];
		createBoard(this.level, this.size);
	}
	private void createBoard(int l, int x) {
		
		for(int lvl=0;lvl<l;lvl++) {
			for(int row=0;row<x;row++) {
				for(int column=0;column<x;column++) {
						if((lvl+row+column)%7==0) {
							this.board[lvl][row][column]=2;
						}else {
							if((lvl+row+column)%5==0){
								this.board[lvl][row][column]=-2;
						}else
							if((lvl+row+column)%3==0) {
								this.board[lvl][row][column]=-3;
						}else {
							this.board[lvl][row][column]=0;
						}
					}
				}
			}
		}
	}
	public int getEnergyAdj(int l,int x, int y) {
		return this.board[l][x][y];
	}
	public String toString() {
		return "Your energy is adjusted by "+this.board+" for landing at "+this.board+" at level "+this.level;
	}
	public int getLevel() {
		return this.level;
	}
	public int getSize() {
		return this.size;
	}
}
