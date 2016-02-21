import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel implements MouseListener, MouseMotionListener {
	int [] [] pieces;
	boolean isHuman = false;
	public static boolean clicked = false;
	public static int chosenmove = 0;
	int slot = 0;
	double scale = Math.min(this.getHeight()/1200., this.getWidth()/1600.);

	public DisplayPanel(){
		setBackground(Color.black);
		pieces = new int [6][7];
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		scale = Math.min(this.getHeight()/1200., this.getWidth()/1600.);
		g.drawImage((new ImageIcon("Board_Background.png")).getImage(), (int)((this.getWidth() - 1600 * scale) / 2) ,(int)((this.getHeight() - 1200 * scale) / 2), (int)(1600*scale), (int)(1200*scale), null);

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
			{
				if(pieces[i][j] == 1)
					g.drawImage((new ImageIcon("Green3D_Piece_3.png")).getImage(), (int)((52+j*225)*scale) + (int)((this.getWidth() - 1600 * scale) / 2),(int)((27+i*200)*scale) + (int)((this.getHeight() - 1200 * scale) / 2), (int)(150*scale), (int)(150*scale),null);
				else if (pieces[i][j] == 2)
					g.drawImage((new ImageIcon("Fiber_Piece_3.png")).getImage(), (int)((52+j*225)*scale) + (int)((this.getWidth() - 1600 * scale) / 2),(int)((27+i*200)*scale) + (int)((this.getHeight() - 1200 * scale) / 2), (int)(150*scale), (int)(150*scale),null);
			}
		g.drawImage((new ImageIcon("Board_Foreground.png")).getImage(), (int)((this.getWidth() - 1600 * scale) / 2) ,(int)((this.getHeight() - 1200 * scale) / 2), (int)(1600*scale), (int)(1200*scale), null);
		g.setColor(new Color(100, 100, 100, 100));
		g.fillRect((int)(scale * (slot * 225) + 13) + (int)((this.getWidth() - 1600 * scale) / 2), 0, (int)(scale * 225), this.getHeight());
	}
	
	public void update(int [][] pieces){
		this.pieces = pieces;
	}
	
	public static boolean isClicked() {
		try {
			Thread.sleep(100);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		return clicked;
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		chosenmove = (int)((((double)(e.getX()) - scale * 13) - (int)((this.getWidth() - 1600 * scale) / 2)) / (scale * 225));
		if (chosenmove > 6)
			chosenmove = 6;
		if (chosenmove < 0)
			chosenmove = 0;
		if (pieces[0][chosenmove] == 0)
			clicked = true;
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		slot = (int)((((double)(e.getX()) - scale * 13) - (int)((this.getWidth() - 1600 * scale) / 2)) / (scale * 225));
		if (slot > 6)
			slot = 6;
		if (slot < 0)
			slot = 0;
	}
	
	public static int getChosenMove() {
		return chosenmove;
	}
	
	public static void unclick() {
		clicked = false;
	}
}