public class DefaultPlayer implements Player{
	String name; 

	public Player(name){
		this.name = name;	
	}

	public int makeMove(){
		return Math.random()*7;
	}
	
	public String getName(){
		return name;
	}
}