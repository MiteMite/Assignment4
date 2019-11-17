//-------------------------------------------------------
//Assignment 4
//Written by: Simon Fortier Drouin 27207328
//For COMP 248 Section FF – Fall 2019
//--------------------------------------------------------
public class Board {
	private int[][][] board;
	private static final int MIN_LEVEL = 3;
	private static final int MIN_SIZE = 3;
	private int level;
	private int size;
	
	Board(){
		this.level=3;
		this.size=4;
		createBoard(this.level, this.size);
	}
	Board(int l, int x){
		this.level=l;
		this.size=x;
	}
	private void createBoard(int l, int x) {
		for(int i=0;i<0;i++) {
			if((l+x)%7==0) {
				board[l][x][i]=2;
			}else
				if(l+x%5==0) {
					board[l][x][i]=-2;
				}else
					if(l+x%3==0) {
						board[l][x][i]=-3;
					}
		}

	}
	public int getEnergyAdj(int l,int x, int y) {
		return board[l][x][y];
	}
	public String toString() {
		return "Your energy is adjusted by "+board+" for landing at "+board+" at level "+level;
	}
}
