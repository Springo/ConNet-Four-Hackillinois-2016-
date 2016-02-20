import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel{
	int [] [] pieces;

	public DisplayPanel(){
		setBackground(Color.white);
		pieces = new int [7][6];
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		double scale = Math.min(this.getHeight()/1200., this.getWidth()/1600.);

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
			{
				if(pieces[i][j] == 1)
					g.drawImage((new ImageIcon("Blue_Piece.png")).getImage(), (int)((52+j*225)*scale),(int)((27+i*200)*scale), (int)(150*scale), (int)(150*scale),null);
				else if (pieces[i][j] == 2)
					g.drawImage((new ImageIcon("Red_Piece.png")).getImage(), (int)((52+j*225)*scale),(int)((27+i*200)*scale), (int)(150*scale), (int)(150*scale),null);
			}
		g.drawImage((new ImageIcon("Board.png")).getImage(), 0 ,0, (int)(1600*scale), (int)(1200*scale), null);
	}
	
	public void update(int [][] pieces){
		this.pieces = pieces;
	}
}