import java.awt.*
import javax.swing.*

public class ConNetFour extends JPanel{
	Player player1;
	Player player2;
	int [6][7] board;
	boolean turnOne; //true for player1, false for player2
	String winner = ""; 

	//need images for pieces and board
	
	public ConNetFour(){
		super(new GridLayout(1,0));
		player1 = new DefaultPlayer("player1");
		player2 = new DefaultPlayer("player2");
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				board[i][j] = 0;
	}

	public static void main(){
		play();
	}
	
	public void play(){
		createGUI();
		int col = 0;
		while (winner.equals("")){
			if (turnOne)
				col = player1.makeMove();
			else
				col = player2.makeMove();
			updateBoard(col);
			turnOne = !turnOne;
		}
	}
	
	public void createGUI(){
		JFrame frame = new JFrame ("GameBoard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void redraw(){
	}

	public void updateBoard(int col){
		for (int i = 5; i >= 0; i--){
			if (board[i][col] == 0){
				if (turnOne)
					board[i][col] = 1;
				else
					board[i][col] = 2;
				i=6;
				checkForWinner(i, col);
			}
		}
		redraw();
	}
	
	public void checkForWinner(int row, int col){
		//check in every dir from that point for a 4 in a row
		//ughidshfodsihfodsifhds
		//check the row
		//check the col
		//check diag in both directions
		if (turnOne){
		}
		else
			
	}
}