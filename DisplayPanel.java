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
		int pieceSize = this.getHeight()/6;
		int backgroundWidth = this.getWidth()*1200/1600;
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
			{
				if(pieces[i][j] == 1)
					g.drawImage((new ImageIcon("Blue_Piece.png")).getImage(), backgroundWidth*j/7,(this.getHeight()+10)*i/6, pieceSize, pieceSize,null);
				else if (pieces[i][j] == 2)
					g.drawImage((new ImageIcon("Red_Piece.png")).getImage(), backgroundWidth*j/7,(this.getHeight()+10)*i/6, pieceSize, pieceSize,null);
			}
		g.drawImage((new ImageIcon("Board.png")).getImage(), 0 ,0, backgroundWidth, this.getHeight(), null);
	}
	
	public void update(int [][] pieces){
		this.pieces = pieces;
	}
}