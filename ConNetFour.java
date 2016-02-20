import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ConNetFour extends JPanel{
	Player player1;
	Player player2;
	int [][] board;
	String winner = ""; 
	int playerTurn = 1;
	DisplayPanel canvas;
	JFrame frame;

	//need images for pieces and board
	
	public ConNetFour(){
		super(new GridLayout(1,0));
		player1 = new DefaultPlayer("player1");
		player2 = new DefaultPlayer("player2");
		board = new int [6][7];
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				board[i][j] = 0;
	}

	public static void main(String [] args){
		ConNetFour newgame = new ConNetFour();
		newgame.play();
	}
	
	public void play(){
		createGUI();
		int col = 0;
		while (winner.equals("")){
			if (playerTurn == 1)
			{
				player1.updateData(board);
				col = player1.makeMove();
				updateBoard(col);
				playerTurn++;
			}
			else
			{
				player2.updateData(board);
				col = player2.makeMove();
				updateBoard(col);
				playerTurn--;
			}
		}
		if (playerTurn == 1){
			player1.won(1);
			player2.won(-1);
		}
		else{
			player1.won(-1);
			player2.won(1);
		}
	}
	
	public void createGUI(){
		frame = new JFrame ("GameBoard");
		frame.setSize(1600,1200);
		frame.setIconImage(new ImageIcon("defaultIcon.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		canvas = new DisplayPanel();
	
		frame.getContentPane().add(canvas);

		//frame.pack();
		frame.setVisible(true);
	}

	public void updateBoard(int col){
		for (int i = 5; i >= 0; i--){
			if (board[i][col] == 0){
				board[i][col] = playerTurn;
				i=6;
				canvas.repaint();
				checkForWinner(i, col);
			}
		}
	}
	
	public void checkForWinner(int row, int col){
		//check in every dir from that point for a 4 in a row
		//ughidshfodsihfodsifhds
		//check the row
		//check the col
		//check diag in both directions
		//if (turnOne){
	//	}
		//else
			
	}
}