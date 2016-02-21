import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ConNetFour implements ActionListener{
	Player player1;
	Player player2;
	int [][] board;
	String winner = ""; 
	int playerTurn = 1;
	int movenumber = 0;
	DisplayPanel canvas;
	JFrame frame;
	JLabel display;
	int numGames = 0;
	int repeats = 1;
	
	public ConNetFour(){
		//super(new GridLayout(1,0));
		display = new JLabel("he y ");
		player1 = new HumanPlayer("Kevin");
		player2 = new AIPlayer("Janice");
		board = new int [6][7];
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				board[i][j] = 0;
		createGUI();
	}

	public void reset(){
		numGames = 0;
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				board[i][j] = 0;
		winner = "";
		movenumber = 0;
		playerTurn = 1;
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
		int col = 0;
		//canvas.repaint();
		while (numGames < repeats){
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				board[i][j] = 0;
		winner = "";
		movenumber = 0;
		playerTurn = 1;
			while (winner.equals("")){
				canvas.repaint();
				if (playerTurn == 1)
				{
					player1.updateData(board);
					if (player1 instanceof HumanPlayer) {
						if (DisplayPanel.isClicked()) {
							movenumber++;
							col = player1.makeMove();
							updateBoard(col);
							playerTurn++;
							DisplayPanel.unclick();
						}
					}
					else {
						movenumber++;
						col = player1.makeMove();
						updateBoard(col);
						playerTurn++;
					}
				}
				else
				{
					player2.updateData(board);
					if (player2 instanceof HumanPlayer) {
						if (DisplayPanel.isClicked()) {
							movenumber++;
							col = player2.makeMove();
							updateBoard(col);
							playerTurn--;
							DisplayPanel.unclick();
						}
					}
					else {
						movenumber++;
						col = player2.makeMove();
						updateBoard(col);
						playerTurn--;
					}
				}
				//printBoard();
			}
			numGames++;
	
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

	}
	
	public void createGUI(){
		frame = new JFrame ("GameBoard");
		frame.setSize(1600,1200);
		frame.setIconImage(new ImageIcon("defaultIcon.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		display.setText("OH hELLO");
		canvas = new DisplayPanel();
		frame.setJMenuBar(createMenuBar());
		frame.getContentPane().add(display, BorderLayout.NORTH);
		frame.getContentPane().add(canvas, BorderLayout.CENTER);

		//frame.pack();
		frame.setVisible(true);
	}

	public JMenuBar createMenuBar(){
		JMenuBar menuBar  = new JMenuBar();
		JMenu start = new JMenu("Start");
		JMenu custom = new JMenu("Customize");
		JMenuItem reset = new JMenu("Reset AI");
		JMenu newAi = new JMenu("AI vs AI");
		JMenuItem one = new JMenuItem("1 round");
		JMenuItem hundred = new JMenuItem("100 rounds");
		JMenuItem newHuman = new JMenuItem("Play against AI");
		
		one.addActionListener(this);
		hundred.addActionListener(this);
		newHuman.addActionListener(this);
		reset.addActionListener(this);
		
		menuBar.add(start);
		menuBar.add(custom);
		menuBar.add(reset);
		start.add(newAi);
		newAi.add(one);
		newAi.add(hundred);
		start.add(newHuman);

		return menuBar;
	}
	public void actionPerformed(ActionEvent e) {
       		JMenuItem source = (JMenuItem)(e.getSource());
       		String s = source.getText();
		if (s.equals("1 round")){
			repeats = 1;
			reset();
			this.play();
		}
		else if (s.equals("100 rounds")){
			repeats = 100;
			reset();
			this.play();
		}
		display.setText(s);
        }
	public void updateBoard(int col){
		for (int i = 5; i >= 0; i--){
			if (board[i][col] == 0){
				board[i][col] = playerTurn;
				//System.out.println(i + ", " + col);
				canvas.update(board);
				canvas.repaint();
				checkForWinner(i, col);
				i=-1;
			}
		}
	}
	
	public void checkForWinner(int row, int col){
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