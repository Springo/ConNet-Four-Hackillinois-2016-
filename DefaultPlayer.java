public class DefaultPlayer implements Player{
	String name; 

	public DefaultPlayer(String name){
		this.name = name;	
	}

	public int makeMove(){
		return (int)(Math.random()*7);
	}
	
	public String getName(){
		return name;
	}

	public void updateData (int[][]board){
	}

	public void won(int i){
	}
}