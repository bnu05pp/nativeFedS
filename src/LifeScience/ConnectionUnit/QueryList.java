package LifeScience.ConnectionUnit;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryList {

	private int KeyNum = 0;
	private ArrayList<ArrayList<Integer>> StartPoint = new ArrayList<>();
	public ArrayList<HashMap<String, ArrayList<String>>> UserInput;
	
	public QueryList() {
		
	}
	public QueryList(ArrayList<HashMap<String, ArrayList<String>>> UserInput) {
		// TODO Auto-generated constructor stub
		this.UserInput = UserInput;
	}
	public int getKeyNum() {
		return KeyNum;
	}

	public void setKeyNum(int keyNum) {
		KeyNum = keyNum;
	}

	public ArrayList<ArrayList<Integer>> getStartPoint() {
		return StartPoint;
	}

	public void setStartPoint(ArrayList<ArrayList<Integer>> startPoint) {
		StartPoint = startPoint;
	}

}
