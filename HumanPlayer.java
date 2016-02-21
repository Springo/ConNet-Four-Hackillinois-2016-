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
		return DisplayPanel.getChosenMove();
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