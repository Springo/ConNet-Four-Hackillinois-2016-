public class DefaultPlayer implements Player{
	String name; 
	int[][] board = new int[6][7];
	//int alwaysmove = (int)(Math.random() * 7);

	public DefaultPlayer(String name){
		this.name = name;	
	}

	public int makeMove(){
		int move;
		/*if (board[0][alwaysmove] == 0)
			return alwaysmove;*/
		do {
			move = (int)(Math.random() * 7);
		} while (board[0][move] != 0);
		return (int)(Math.random()*7);
	}
	
	public String getName(){
		return name;
	}

	public void updateData (int[][]board){
		this.board = board;
	}

	public void won(int i){
	}
}