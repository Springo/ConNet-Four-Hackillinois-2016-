//ConNetFour 2/20/2016
//Kevin Xia, Janice Huang, Nikhil Dave

import java.io.*;
import java.util.*;

public class AIPlayer implements Player {
	private String name;
	private String data;
	private Map<String, Integer> brain;
	private ArrayList<String> experience;
	
	public AIPlayer(String name) {
		this.name = name;
		brain = new HashMap<String, Integer>();
		experience = new ArrayList<String>();
		data = "";
		for (int i = 0; i < 42; i++)
			data += "0 ";
		getBrain(name + "Brain.txt");
	}
	
	//Following methods only for testing.
	public static void main(String [] args) {
		AIPlayer p = new AIPlayer("Bob");
		p.addnew("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ", -3);
		p.addnew("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 ", 3);
		p.addnew("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 ", -3);
		p.writeBrain(p.getName() + "Brain.txt");
		p.getBrain(p.getName() + "Brain.txt");
		int[][] testboard = new int[6][7];
		p.updateData(testboard);
		System.out.println(p.makeMove());
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
		int heuristic = 0;
		int bestH = -99999;
		int move = 0;
		String goodmoves = "";
		for (int i = 0; i < 7; i++) {
			if (Integer.parseInt("" + data.charAt(2 * i)) != 0)
				continue;
			Integer temp = brain.get(data + i + " ");
			if (temp == null)
				heuristic = 0;
			else
				heuristic = temp;
			if (heuristic == bestH) {
				goodmoves += "" + i;
			}
			if (heuristic > bestH) {
				bestH = heuristic;
				goodmoves = "" + i;
			}
		}
		move = (int)(Math.random() * goodmoves.length());
		move = Integer.parseInt("" + goodmoves.charAt(move));
		experience.add(data + move + " ");
		return move;
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
		catch (FileNotFoundException e) {
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
			PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
			Iterator it = brain.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				int value = (Integer)(pair.getValue());
				String zeroes = "";
				for (int i = 0; i < (6 - (Integer.toString(value)).length()); i++)
					zeroes += "0";
				if (value >= 0)
					pw.println((String)(pair.getKey()) + zeroes + (Integer)(pair.getValue()));
				else
					pw.println((String)(pair.getKey()) + "-" + zeroes + (0 - (Integer)(pair.getValue())));
				it.remove();
			}
			pw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void updateData(int[][] board) {
		data = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				data = data + board[i][j] + " ";
			}
		}
	}
	
	public void won(int result) {
		//System.out.println(result);
		for (int i = 0; i < experience.size(); i++) {
			String key = experience.get(i);
			if (brain.containsKey(key)) {
				brain.put(key, brain.get(key) + result);
			}
			else {
				brain.put(key, result);
			}
		}
		writeBrain(name + "Brain.txt");
	}
}