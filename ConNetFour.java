import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ConNetFour extends JPanel{
	Player player1;
	Player player2;
	int [][] board;
	String winner = ""; 
	int playerTurn = 1;
	int movenumber = 0;
	DisplayPanel canvas;
	JFrame frame;

	//need images for pieces and board
	
	public ConNetFour(){
		super(new GridLayout(1,0));
		player1 = new DefaultPlayer("player1");
		player2 = new AIPlayer("Janice");
		board = new int [6][7];
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				board[i][j] = 0;
	}

	public static void main(String [] args){
		ConNetFour newgame = new ConNetFour();
		newgame.play();
	}
	
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void play(){
		createGUI();
		int col = 0;
		while (winner.equals("")){
			if (playerTurn == 1)
			{
				player1.updateData(board);
				col = player1.makeMove();
				movenumber++;
				updateBoard(col);
				playerTurn++;
			}
			else
			{
				player2.updateData(board);
				col = player2.makeMove();
				movenumber++;
				updateBoard(col);
				playerTurn--;
			}
			printBoard();
		}
		System.out.println("WINNER! Player " + winner);
		if (movenumber >= 42) {
			player1.won(0);
			player2.won(0);
		}
		else {
			if (playerTurn == 2){
				player1.won(1);
				player2.won(-1);
			}
			else{
				player1.won(-1);
				player2.won(1);
			}
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
				System.out.println(i + ", " + col);
				canvas.update(board);
				canvas.repaint();
				checkForWinner(i, col);
				i=-1;
			}
		}
	}
	
	public void checkForWinner(int row, int col){// check is board is full - > draw  (at the end of this method)
		//check in every dir from that point for a 4 in a row
		//ughidshfodsihfodsifhds
		//check the row
		//check the col
		//check diag in both directions
		//if (turnOne){
	//	}
		//else
		int side = board[row][col];
		int combo = 1;
		for (int i = 1; i < 4; i++) {
			if (row + i > 5)
				break;
			if (board[row + i][col] == side)
				combo++;
			else
				break;
		}
		for (int i = 1; i < 4; i++) {
			if (row - i < 0)
				break;
			if (board[row - i][col] == side)
				combo++;
			else
				break;
		}
		if (combo >= 4) {
			winner += side;
			return;
		}
		combo = 1;
		for (int i = 1; i < 4; i++) {
			if (col + i > 6)
				break;
			if (board[row][col + i] == side)
				combo++;
			else
				break;
		}
		for (int i = 1; i < 4; i++) {
			if (col - i < 0)
				break;
			if (board[row][col - i] == side)
				combo++;
			else
				break;
		}
		if (combo >= 4) {
			winner += side;
			return;
		}
		combo = 1;
		for (int i = 1; i < 4; i++) {
			if (row + i > 5 || col + i > 6)
				break;
			if (board[row + i][col + i] == side)
				combo++;
			else
				break;
		}
		for (int i = 1; i < 4; i++) {
			if (row - i < 0 || col - i < 0)
				break;
			if (board[row - i][col - i] == side)
				combo++;
			else
				break;
		}
		if (combo >= 4) {
			winner += side;
			return;
		}
		combo = 1;
		for (int i = 1; i < 4; i++) {
			if (row + i > 5 || col - i < 0)
				break;
			if (board[row + i][col - i] == side)
				combo++;
			else
				break;
		}
		for (int i = 1; i < 4; i++) {
			if (row - i < 0 || col + i > 6)
				break;
			if (board[row - i][col + i] == side)
				combo++;
			else
				break;
		}
		if (combo >= 4) {
			winner += side;
			return;
		}
		if (movenumber >= 42) {
			winner += "0";
			return;
		}
	}
}