import java.awt.*;
import javax.swing.*;

public class HumanPlayer implements Player{
	String name;
	int[][] board = new int[6][7];

	public HumanPlayer (String name){
		this.name = name;
	}
	
	public static void main (String[] args) {
		HumanPlayer hp = new HumanPlayer("Bob");
		System.out.println(hp.makeMove());
	}

	public int makeMove(){
		int numMoves = 7;
		Object[] possibleValues = { "1", "2", "3", "4", "5", "6", "7" };
		for (int i = 0; i < 7; i++) {
			if (board[0][i] != 0) {
				possibleValues[i] = "0";
				numMoves--;
			}
		}
		Object[] allowedValues = new Object[numMoves];
		numMoves = 0;
		for (int i = 0; i < possibleValues.length; i++) {
			if (((String)possibleValues[i]).equals(0)) {
				continue;
			}
			else {
				allowedValues[numMoves] = possibleValues[i];
				numMoves++;
			}
		}
		Object selectedValue;
		do {
			selectedValue = JOptionPane.showInputDialog(null, "Choose a column: ", name + "'s Move", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
		} while (selectedValue == null);
		return Integer.parseInt((String)selectedValue) - 1;
	}
	
	public String getName(){
		return name;
	}

	public void updateData(int [][]board){
		this.board = board;
	}

	public void won(int i){
	}
}