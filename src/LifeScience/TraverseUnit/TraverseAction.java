package LifeScience.TraverseUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.openrdf.query.algebra.Str;

import LifeScience.ConnectionUnit.ConnectionAction;
import LifeScience.ConnectionUnit.QueryList;
import LifeScience.TraverseUnit.Graph.*;
import LifeScience.UnityUnit.InitialHelper;

public class TraverseAction {

	private HashMap<Integer, ArrayList<SGraphEdge>> multiQueryUtil = new HashMap<>();
	private ArrayList<HashMap<Integer, ArrayList<String>>> queryArray = new ArrayList<>();// this is what i need
	private ArrayList<ArrayList<String>> KeywordPoint = new ArrayList<>();

	public HashMap<Integer, ArrayList<SGraphEdge>> getMultiQueryUtil() {
		return multiQueryUtil;
	}

	public void setMultiQueryUtil(HashMap<Integer, ArrayList<SGraphEdge>> multiQueryUtil) {
		this.multiQueryUtil = multiQueryUtil;
	}

	public ArrayList<HashMap<Integer, ArrayList<String>>> getQueryArray() {
		return queryArray;
	}

	public void setQueryArray(ArrayList<HashMap<Integer, ArrayList<String>>> queryArray) {
		this.queryArray = queryArray;
	}

	public ArrayList<ArrayList<String>> getKeywordPoint() {
		return KeywordPoint;
	}

	public void setKeywordPoint(ArrayList<ArrayList<String>> keywordPoint) {
		KeywordPoint = keywordPoint;
	}

	public TraverseAction() {
		// TODO Auto-generated constructor stub
		ArrayList<SGraphEdge> a0 = new ArrayList<>();
		ArrayList<SGraphEdge> a1 = new ArrayList<>();
		ArrayList<SGraphEdge> a2 = new ArrayList<>();
		ArrayList<SGraphEdge> a3 = new ArrayList<>();

		multiQueryUtil.put(0, a0);
		multiQueryUtil.put(1, a1);
		multiQueryUtil.put(2, a2);
		multiQueryUtil.put(3, a3);
	}

	public void start(ArrayList<HashMap<String, ArrayList<String>>> UserInput, QueryList qList, InitialHelper iHelper)
			throws Exception {

		ArrayList<ArrayList<Integer>> StartPoint = new ArrayList<>();
		StartPoint = qList.getStartPoint();
		System.out.println(StartPoint.size());
		int possibleQuery = StartPoint.size();
		StructQuery gSubgraph = new StructQuery();
		// ArrayList<Integer> sp = new ArrayList<>();
		for (int index = 0; index < possibleQuery; index++) {
			System.out.println("**************************************************");
			gSubgraph.done(iHelper, StartPoint.get(index));
			/**
			 * SGraphEdge ed = new SGraphEdge(); ed.first = 220; ed.second = 1;
			 * gSubgraph.TheSubGraph.add(ed);
			 */
			// System.out.println("here we start");
			for (int i = 0; i < gSubgraph.TheSubGraph.size(); i++) {
				// System.out.println(gSubgraph.TheSubGraph.get(i).first + "--" +
				// gSubgraph.TheSubGraph.get(i).second);
				if (!iHelper.ifDirRight(Integer.valueOf(gSubgraph.TheSubGraph.get(i).first),
						Integer.valueOf(gSubgraph.TheSubGraph.get(i).second))) {
					int temp = gSubgraph.TheSubGraph.get(i).first;
					gSubgraph.TheSubGraph.get(i).first = gSubgraph.TheSubGraph.get(i).second;
					gSubgraph.TheSubGraph.get(i).second = temp;
				}
				// System.out.println(gSubgraph.TheSubGraph.get(i).first + "--" +
				// gSubgraph.TheSubGraph.get(i).second);
			}
			// System.out.println(gSubgraph.TheSubGraph);
			clear();
			for (int i = 0; i < gSubgraph.TheSubGraph.size(); i++) {

				// 获取类所在的数据集
				int thisdb = iHelper.getDN(gSubgraph.TheSubGraph.get(i).first);
				// System.out.println(thisdb);
				multiQueryUtil.get(thisdb).add(gSubgraph.TheSubGraph.get(i));
			}
			System.out.println("0:" + multiQueryUtil.get(0));
			System.out.println("1:" + multiQueryUtil.get(1));
			System.out.println("2:" + multiQueryUtil.get(2));
			System.out.println("3:" + multiQueryUtil.get(3));

			// *****************************************************************************************************************************************************

			HashMap<Integer, ArrayList<String>> querylist = new HashMap<>();
			for (int i = 0; i < 4; i++) {
				ArrayList<SGraphEdge> edgeset = multiQueryUtil.get(i);
				ArrayList<ArrayList<String>> multiEdge = new ArrayList<>();
				for (int k = 0; k < edgeset.size(); k++) {
					ArrayList<String> preSet = new ArrayList<>();
					ArrayList<Integer> onList = iHelper.preSetPair.get(edgeset.get(k).first).get(edgeset.get(k).second);

					for (int p = 0; p < onList.size(); p++) {
						if (!(StartPoint.get(index).contains(edgeset.get(k).first))
								&& !(StartPoint.get(index).contains(edgeset.get(k).second))) {
							String eString = " ?e" + edgeset.get(k).first + " " + iHelper.getPreR(onList.get(p)) + " ?e"
									+ edgeset.get(k).second + " .\r\n";
							preSet.add(eString);
						}

						else if (!(StartPoint.get(index).contains(edgeset.get(k).first))
								&& (StartPoint.get(index).contains(edgeset.get(k).second))) {
							String eString = " ?e" + edgeset.get(k).first + " " + iHelper.getPreR(onList.get(p)) + " "
									+ " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).second) + " .\r\n";
							eString += "filter(" +  " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).second) +"=";
							for (int j = 0; j < UserInput
									.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).second))
									.get(iHelper.getClassR(edgeset.get(k).second)).size(); j++) {
								if (j != 0)
									eString += "||" + " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).second) +"=";
								eString += UserInput
										.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).second))
										.get(iHelper.getClassR(edgeset.get(k).second)).get(j);
							}
							eString += ") ";
							preSet.add(eString);
						} else if (((StartPoint.get(index).contains(edgeset.get(k).first))
								&& !(StartPoint.get(index).contains(edgeset.get(k).second)))) {

							String eString = " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).first) + " "
									+ iHelper.getPreR(onList.get(p)) + " ?e" + edgeset.get(k).second + " .\r\n";
							eString += "filter(" + " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).first) +"=";
							//System.out.println(
							//		UserInput.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).first)));
							//System.out.println(iHelper.getClassR(edgeset.get(k).first));
							for (int j = 0; j < UserInput
									.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).first))
									.get(iHelper.getClassR(edgeset.get(k).first)).size(); j++) {
								if (j != 0)
									eString += "||" + " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).first) +"=";
								eString += UserInput.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).first))
										.get(iHelper.getClassR(edgeset.get(k).first)).get(j);
							}
							eString += ") ";
							preSet.add(eString);

						}
						
						else {

							String eString = " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).first) + " "
									+ iHelper.getPreR(onList.get(p)) + " " + " ?k"
									+ qList.getStartPoint().get(index).indexOf(edgeset.get(k).second) + " .\r\n";

							eString += "filter(" + " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).first) +"=";
							for (int j = 0; j < UserInput
									.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).first))
									.get(iHelper.getClassR(edgeset.get(k).first)).size(); j++) {
								if (j != 0)
									eString += "||" + " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).first) +"=";
								eString += UserInput
										.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).first))
										.get(iHelper.getClassR(edgeset.get(k).first)).get(j);
							}
							eString += ") ";
							eString += "filter(" + " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).second) +"=";
							for (int j = 0; j < UserInput
									.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).second))
									.get(iHelper.getClassR(edgeset.get(k).second)).size(); j++) {
								if (j != 0)
									eString += "||" + " ?k" + qList.getStartPoint().get(index).indexOf(edgeset.get(k).second) +"=";
								eString += UserInput.get(qList.getStartPoint().get(index).indexOf(edgeset.get(k).second))
										.get(iHelper.getClassR(edgeset.get(k).second)).get(j);
							}
							eString += ") ";
							preSet.add(eString);

						}
					}

					multiEdge.add(preSet);
				}
				ArrayList<String> oneSetDeal = getList(multiEdge);
				// System.out.println(i + " onSetDeal:" + oneSetDeal);
				querylist.put(i, oneSetDeal);
			}
			int flag = 0;
			for (int j = 0; j < 4; j++) {
				if (querylist.get(j).isEmpty())
					flag++;
			}
			if (flag <= 3)
				queryArray.add(querylist);

			/*
			 * sp = StartPoint.get(index); ArrayList<String> ks = new ArrayList<>(); for
			 * (int i=0;i<sp.size();i++) {
			 * ks.add(kwlist.get(i).get(iHelper.getClassR(sp.get(i)))); }
			 * KeywordPoint.add(ks);
			 */
		}

		System.out.println("**************************************************");
		System.out.println("test output");
		System.out.println(queryArray.size());
		System.out.println(KeywordPoint);
		System.out.println("**************************************************");
		for (int i = 0; i < queryArray.size(); i++) {
			HashMap<Integer, ArrayList<String>> qlist = queryArray.get(i);
			System.out.println(i + " situation: ");
			for (int j = 0; j < 4; j++) {
				System.out.println(qlist.get(j));
			}
		}
		System.out.println("**************************************************");

	}

	public ArrayList<String> getList(ArrayList<ArrayList<String>> a) {
		Random random = new Random();
		ArrayList<String> strings = new ArrayList<>();
		int n = 1;
		for (int i = 0; i < a.size(); i++) {
			n *= a.get(i).size();
		}
		for (int i = 0; i < n; i++) {
			String cString = "";
			for (int j = 0; j < a.size(); j++) {
				int index = random.nextInt(a.get(j).size());
				cString += a.get(j).get(index);
			}
			if (strings.contains(cString)) {
				n++;
			} else {
				strings.add(cString);
			}
		}
		return strings;
	}

	public void clear() {
		multiQueryUtil.get(0).clear();
		multiQueryUtil.get(1).clear();
		multiQueryUtil.get(2).clear();
		multiQueryUtil.get(3).clear();
	}

	public static void main(String[] args) throws Exception {
		InitialHelper iHelper = new InitialHelper();
		ConnectionAction cAction = new ConnectionAction();
		TraverseAction tAction = new TraverseAction();

		ArrayList<HashMap<String, ArrayList<String>>> kwlist = new ArrayList<>();

		HashMap<String, ArrayList<String>> h1 = new HashMap<>();
		ArrayList<String> oArrayList11 = new ArrayList<>();
		oArrayList11.add("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01397>");
		oArrayList11.add("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01398>");
		oArrayList11.add("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01399>");
		h1.put("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/drugs>", oArrayList11);

		ArrayList<String> oArrayList1 = new ArrayList<>();
		oArrayList1.add("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01397>");
		oArrayList1.add("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01398>");
		oArrayList1.add("<http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01399>");
		h1.put("<http://www4.wiwiss.fu-berlin.de/drugbank/vocab/resource/class/Offer>", oArrayList1);

		HashMap<String, ArrayList<String>> h2 = new HashMap<>();
		ArrayList<String> oArrayList22 = new ArrayList<>();
		oArrayList22.add("<http://dbpedia.org/resource/John_Robert_Vane>");
		h2.put("<http://dbpedia.org/ontology/Scientist>", oArrayList22);

		ArrayList<String> oArrayList2 = new ArrayList<>();
		oArrayList2.add("<http://dbpedia.org/resource/John_Robert_Vane>");
		h2.put("<http://dbpedia.org/ontology/Person>", oArrayList2);

		kwlist.add(h1);
		kwlist.add(h2);
		QueryList qList = new QueryList();
		iHelper.init();
		qList = cAction.start(kwlist, iHelper);
		tAction.start(kwlist, qList, iHelper);
	}
}
