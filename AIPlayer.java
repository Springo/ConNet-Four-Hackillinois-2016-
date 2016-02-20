//ConNetFour 2/20/2016
//Kevin Xia, Janice Huang, Nikhil Dave

import java.io.*;
import java.util.*;

public class AIPlayer implements Player {
	private String name;
	private Map<String, Integer> brain;
	private ArrayList<String> experience;
	
	public AIPlayer(String name) {
		this.name = name;
		brain = new HashMap<String, Integer>();
		experience = new ArrayList<String>();
	}
	
	//Following methods only for testing.
	public static void main(String [] args) {
		AIPlayer p = new AIPlayer("Bob");
		p.addnew("0 0 0 0 0 ", 10);
		p.addnew("0 0 0 1 0 ", 9);
		p.addnew("0 0 1 0 1 ", 8);
		p.writeBrain(p.getName() + "Brain.txt");
		p.getBrain(p.getName() + "Brain.txt");
		System.out.println(p.getMap("0 0 0 0 0 "));
		System.out.println(p.getMap("0 0 0 1 0 "));
		System.out.println(p.getMap("0 0 1 0 1 "));
	}
	
	public void addnew(String key, int input) {
		brain.put(key, input);
	}
	
	public Integer getMap(String key) {
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
				brain.put(node.substring(0, node.length() - 6), Integer.parseInt(node.substring(node.length() - 6)));
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
			Iterator it = brain.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				int value = (Integer)(pair.getValue());
				String zeroes = "";
				for (int i = 0; i < (6 - (Integer.toString(value)).length()); i++)
					zeroes += "0";
				pw.println((String)(pair.getKey()) + zeroes + (Integer)(pair.getValue()));
				it.remove();
			}
			pw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}