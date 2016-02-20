//ConNetFour - 2/20/2016
//Kevin Xia, Janice Huang, Nikhil Dave

public interface Player {
	String getName();
	int makeMove();
	void updateData(int[][] board);
	void won(int result); //1 is win, -1 is loss, 0 is draw
}