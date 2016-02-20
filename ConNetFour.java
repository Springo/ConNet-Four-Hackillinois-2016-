import java.awt.*
import javax.swing.*

public class ConNetFour extend JPanel{

	Player player1;
	Player player2;
	int [6][7] board;
	boolean turn; //true for player1, false for player2
	String winner; 

	//need images for pieces and board
	
	public ConNetFour(){
		super(new GridLayout(1,0));
		player1 = new Player();
		player2 = new Player();
	}

	public static void main(){
		play();
	}
	
	public void play(){
		makeMove();
		draw();
	}

	public void draw(int x, int y){
	}
}