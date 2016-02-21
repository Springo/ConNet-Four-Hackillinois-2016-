import java.awt.*;
import javax.swing.*;

public class HumanPlayer implements Player{
	String name;

	public HumanPlayer (String name){
		this.name = name;
	}
	
	public static void main (String[] args) {
		HumanPlayer hp = new HumanPlayer("Bob");
		System.out.println(hp.makeMove());
	}

	public int makeMove(){
		Object[] possibleValues = { "1", "2", "3", "4", "5", "6", "7" };
		Object selectedValue = JOptionPane.showInputDialog(null, "Choose a column: ", name + "'s Move", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
		return Integer.parseInt((String)selectedValue) - 1;
	}
	
	public String getName(){
		return name;
	}

	public void updateData(int [][]board){
	}

	public void won(int i){
	}
}