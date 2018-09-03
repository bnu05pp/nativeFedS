package LifeScience.TranslateUnit;

import java.util.ArrayList;
import java.util.HashMap;

public class TranslateAction {
	// ArrayList<HashMap<Integer, ArrayList<String>>> queryArray = new
	// ArrayList<>();// this is what i need
	public ArrayList<HashMap<Integer, ArrayList<String>>> sparqlArray = new ArrayList<>();

	public void printQuery(ArrayList<HashMap<Integer, ArrayList<String>>> sparqlArray) {
		for (int i = 0; i < sparqlArray.size(); i++) {

			for (int j = 0; j < 4; j++) {
				System.out.println("the " + (i + 1) + " query and the " + j + " db: \n" + sparqlArray.get(i).get(j));
			}
		}

		System.out.println("****************************************************");
	}

	/*
	 * update at 2018-5-25
	 */
	public void endpoint(ArrayList<HashMap<Integer, ArrayList<String>>> queryArray) {
		for (int index = 0; index < queryArray.size(); index++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < queryArray.get(index).get(i).size(); j++) {
					if (queryArray.get(index).get(i).get(j) != null) {
						String finalQuery = "SELECT * WHERE {\r\n " + queryArray.get(index).get(i).get(j) + "}";
						//System.out.println(finalQuery);
						queryArray.get(index).get(i).set(j, finalQuery);
					}
				}
			}
		}
		sparqlArray = queryArray;
	}

}
