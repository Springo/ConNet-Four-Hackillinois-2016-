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
	int repeats = -1;
	int mode = 0;

	public ConNetFour(){
		//super(new GridLayout(1,0));
		display = new JLabel("Use the start menu to select a gamemode!");
		player1 = new HumanPlayer("Human");
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
		while (true) {
		int col = 0;

		canvas.repaint();
		while (numGames < repeats){
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				board[i][j] = 0;
		winner = "";
		movenumber = 0;
		playerTurn = 1;
		if (mode == 0 || mode == 1) {
			player1 = new AIPlayer("Kevin");
			player2 = new AIPlayer("Janice");
		}
		else {
			player1 = new HumanPlayer("Kevin");
			player2 = new AIPlayer("Janice");
		}
			DisplayPanel.unclick();
			while (winner.equals("")){
						if (mode == 0)
		{
			try{
				Thread.sleep(500);
			}catch(InterruptedException ex){
				System.out.println("exception happened!");
			}
		}
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
	display.setText("Player 1 Wins!");
			}
			else{
				player1.won(-1);
				player2.won(1);
		
	display.setText("Player 2 Wins!");
			}
		}
		}
		
	}
	}

	
	public void createGUI(){
		frame = new JFrame ("GameBoard");
		frame.setSize(1600,1200);
		frame.setIconImage(new ImageIcon("defaultIcon.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		JMenu newAi = new JMenu("AI vs AI");
		JMenu about = new JMenu("About");
		JMenuItem janice = new JMenuItem("JANICE SPENT FOREVER ON THIS GUI");
		JMenuItem kevin = new JMenuItem("KEVIN SPENT LIKE 10 MINUTES ON HIS ALGORITHM");
		JMenuItem nikhil = new JMenuItem("NIKHIL'S ART IS DOPE");
		JMenuItem one = new JMenuItem("1 round");
		JMenuItem hundred = new JMenuItem("100 rounds");
		JMenuItem newHuman = new JMenuItem("Play against AI");
		
		one.addActionListener(this);
		hundred.addActionListener(this);
		newHuman.addActionListener(this);
		
		menuBar.add(start);
		menuBar.add(about);
		about.add(janice);
		about.add(kevin);
		about.add(nikhil);
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
			
		display.setText("Game in Progress");
			mode = 0;
			repeats = 1;
			reset();
		}
		else if (s.equals("100 rounds")){
			
		display.setText("Game in Progress");
			mode = 1;
			repeats = 100;
			reset();
		}
		else if (s.equals("Play against AI")){
		
		display.setText("Game in Progress");
			mode = 2;
			repeats = 1;
			reset();
		}
		
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