//ConNetFour - 2/20/2016
//Kevin Xia, Janice Huang, Nikhil Dave

public interface Player {
	String getName();	//returns name of player
	int makeMove();	//returns column of specified move of player
	void updateData(int[][] board);	//sends board information to player
	void won(int result); //1 is win, -1 is loss, 0 is draw
}