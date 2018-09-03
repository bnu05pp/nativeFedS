package LifeScience;

import java.util.ArrayList;
import java.util.HashMap;

import LifeScience.ConnectionUnit.ConnectionAction;
import LifeScience.ConnectionUnit.QueryList;
import LifeScience.TranslateUnit.TranslateAction;
import LifeScience.TraverseUnit.TraverseAction;
import LifeScience.UnityUnit.InitialHelper;

/**
 * @author daven
 *
 */
public class mainAction {

	ArrayList<HashMap<Integer, ArrayList<String>>> result = new ArrayList<>();
	
	@Override
	public String toString() {
		return "mainAction [result=" + result + "]";
	}

	public ArrayList<HashMap<Integer, ArrayList<String>>> doAction(ArrayList<HashMap<String, ArrayList<String>>> kwlist) throws Exception {
		InitialHelper iHelper = new InitialHelper();
		ConnectionAction cAction = new ConnectionAction();
		TraverseAction tAction = new TraverseAction();
		QueryList qList = new QueryList();
		TranslateAction trAction = new TranslateAction();
		
		/*ArrayList<HashMap<String, String>> kwlist = new ArrayList<>();
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
		// kwlist.add(h3);*/

		iHelper.init();
		qList = cAction.start(kwlist, iHelper);
		tAction.start(kwlist, qList, iHelper);
		trAction.endpoint(tAction.getQueryArray());
//		trAction.printQuery(trAction.sparqlArray);
		return trAction.sparqlArray;
	}
	
	/*public static void main(String[] args) throws Exception {
		mainAction mAction = new mainAction();
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
		mAction.result = mAction.doAction(kwlist);
		System.out.println(mAction.toString());
	}*/
}
