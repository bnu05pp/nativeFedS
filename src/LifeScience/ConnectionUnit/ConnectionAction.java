package LifeScience.ConnectionUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import org.openrdf.query.algebra.Str;

import LifeScience.UnityUnit.InitialHelper;

public class ConnectionAction {

	@SuppressWarnings("unchecked")
	public QueryList start(ArrayList<HashMap<String, ArrayList<String>>> UserInput, InitialHelper iHelper)
			throws Exception {
		QueryList qList = new QueryList();
		/**
		 * set the number of the keywords
		 */
		int n = UserInput.size();

		/**
		 * provide classNum in order to make starting point of the traverse
		 */

		ArrayList<ArrayList<Integer>> S = new ArrayList<>();

		/*ArrayList<String> temp = (ArrayList<String>) UserInput.get(0).keySet();
		for (int i = 0; i < temp.size(); i++) {
			ArrayList<Integer> tmpList = new ArrayList<Integer>();
			tmpList.add(iHelper.getClassN(temp.get(i)));
			S.add(tmpList);
		}
		for (int j = 1; j < n; j++) {

			temp = (ArrayList<String>) UserInput.get(j).keySet();
			for (int i = 0; i < temp.size(); i++) {
				ArrayList<Integer> tmpList = new ArrayList<Integer>();
				tmpList.add(iHelper.getClassN(temp.get(i)));
				S.add(tmpList);
			}
		}*/
		
		/*ArrayList<ArrayList<String>> aList = new ArrayList<>();
		for (int i=0;i<n;i++) {
			aList.add((ArrayList<String>) UserInput.get(0).keySet());
		}
		
		ArrayList<String> solu = new ArrayList<>();
		solu = getList(aList);
		for (String string : solu) {
			ArrayList<Integer> onesolu = new ArrayList<>();
			String s[] = string.split(" ");
			for (int i=0;i<s.length;i++) {
				onesolu.add(iHelper.getClassN(s[i]));
			}
			S.add(onesolu);
		}*/
		
		LinkedList<ArrayList<String>> resList = new LinkedList<ArrayList<String>>();
		ArrayList<String> typeList = new ArrayList<String>();
		typeList.addAll(UserInput.get(0).keySet());

		for (int i = 0; i < typeList.size(); i++) {
			ArrayList<String> aList = new ArrayList<String>();
			aList.add(typeList.get(i));
			resList.add(aList);
		}
		//System.out.println(resList);

		for (int i = 1; i < UserInput.size(); i++) {
			ArrayList<String> typeList1 = new ArrayList<String>();
			typeList1.addAll(UserInput.get(i).keySet());
			int resNum = resList.size();

			for (int j = 0; j < resNum; j++) {
				ArrayList<String> firstList = resList.poll();

				for (int k = 0; k < typeList1.size(); k++) {
					ArrayList<String> secondList = new ArrayList<String>(firstList);
					secondList.add(typeList1.get(k));
					resList.add(secondList);
				}
			}
		}
		
		for (int i=0;i<resList.size();i++) {
			ArrayList<String> tmp= resList.get(i);
			ArrayList<Integer> oneline = new ArrayList<>();
			for (String s:tmp) {
				oneline.add(iHelper.getClassN(s));
			}
			S.add(oneline);
		}
		
		
		Collections.sort(S, new SortByRank());
		print(S);
		qList.setStartPoint(S);
		return qList;
	}

	/*public ArrayList<String> getList(ArrayList<ArrayList<String>> a) {
		Random random = new Random();
		// System.out.println(a);
		ArrayList<String> strings = new ArrayList<>();
		int n = 1;
		for (int i = 0; i < a.size(); i++) {
			n *= a.get(i).size();
		}
		for (int i = 0; i < n; i++) {
			String cString = "";
			for (int j = 0; j < a.size(); j++) {
				int index = random.nextInt(a.get(j).size());
				cString += a.get(j).get(index) + " ";
			}
			if (strings.contains(cString)) {
				n++;
			} else {
				strings.add(cString);
			}
		}
		return strings;
	}*/

	public void print(ArrayList<ArrayList<Integer>> S) {
		for (int i = 0; i < S.size(); i++) {
			ArrayList<Integer> integers = S.get(i);
			for (int x : integers) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}

	@SuppressWarnings("rawtypes")
	class SortByRank implements Comparator {
		@SuppressWarnings("unchecked")
		public int compare(Object o1, Object o2) {
			ArrayList<Integer> s1 = (ArrayList<Integer>) o1;
			ArrayList<Integer> s2 = (ArrayList<Integer>) o2;
			int x1 = 0;
			for (int x : s1) {
				x1 += x;
			}
			int x2 = 0;
			for (int x : s2) {
				x2 += x;
			}
			if (x1 > x2)
				return 1;
			return -1;
		}
	}

	/*public static void main(String[] args) throws Exception {
		ArrayList<HashMap<String, String>> kwlist = new ArrayList<>();
		HashMap<String, String> h1 = new HashMap<>();
		h1.put("<http://bio2rdf.org/ns/kegg#Drug>", "Liquid");
		h1.put("<http://bio2rdf.org/ns/chebi#Compound>", "Liquid");
		h1.put("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/drug_interactions>", "Liquid");
		HashMap<String, String> h2 = new HashMap<>();
		h2.put("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/drugs>", "solid");
		h2.put("<http://www4.wiwiss.fu-berlin.de/drugbank/vocab/resource/class/Offer>", "solid");
		HashMap<String, String> h3 = new HashMap<>();
		h3.put("<http://www.w3.org/2002/07/owl#DatatypeProperty>", "Type");
		h3.put("<http://www.w3.org/2002/07/owl#ObjectProperty>", "Type");
		h3.put("<http://www.w3.org/2002/07/owl#Class>", "Type");
		kwlist.add(h1);
		kwlist.add(h2);
		// kwlist.add(h3);
		InitialHelper iHelper = new InitialHelper();
		iHelper.init();
		ConnectionAction cAction = new ConnectionAction();
		cAction.start(kwlist, iHelper);

	}*/
}
