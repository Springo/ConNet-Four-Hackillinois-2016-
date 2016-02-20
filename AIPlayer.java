//ConNetFour 2/20/2016
//Kevin Xia, Janice Huang, Nikhil Dave

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class AIPlayer implements Player {
	private String name;
	private HashMap<String, String> brain;
	private ArrayList<String> experience;
	
	public AIPlayer(String name) {
		this.name = name;
		brain = new HashMap<String, String>();
		experience = new ArrayList<String>();
	}
	
	//Following methods only for testing.
	public static void main(String [] args) {
		AIPlayer p = new AIPlayer("Bob");
		p.addnew("0 0 0 0 0 1");
		p.addnew("0 0 0 1 0 0");
		p.addnew("0 0 1 0 1 1");
		p.writeBrain(p.getName() + "Brain.txt");
		p.getBrain(p.getName() + "Brain.txt");
		System.out.println(p.getMap("0 0 0 0 "));
		System.out.println(p.getMap("0 0 0 1 "));
		System.out.println(p.getMap("0 0 1 0 "));
	}
	
	public void addnew(String input) {
		experience.add(input);
	}
	
	public String getMap(String key) {
		return brain.get(key);
	}
	//End test methods.
	
	public String getName() {
		return name;
	}
	
	public int makeMove() {
		return (int)(Math.random() * 7);
	}
	
	//Reads from brain file and stores information into brain hashmap.
	public void getBrain(String filename) {
		File f = new File(filename);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String node;
			while ((node = br.readLine()) != null) {
				brain.put(node.substring(0, node.length() - 3), node.substring(node.length() - 3));
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	//Stores new information from experience into brain file.
	public void writeBrain(String filename) {
		File f = new File(filename);
		try {
			f.createNewFile();
			PrintWriter pw = new PrintWriter(new FileOutputStream(f));
			for (int i = 0; i < experience.size(); i++) {
				pw.println(experience.get(i));
			}
			pw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}