import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel{
	int [] [] pieces;

	public DisplayPanel(){
		setBackground(Color.white);
		pieces = new int [6][7];
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage((new ImageIcon("Blue_Piece.png")).getImage(), 10, 10, this.getHeight()/6, this.getHeight()/6,null);
		g.drawImage((new ImageIcon("Board.png")).getImage(), 0 ,0, this.getWidth()*1200/1600, this.getHeight(), null);
	}
	
	public void update(int [][] pieces){
		this.pieces = pieces;
	}
}