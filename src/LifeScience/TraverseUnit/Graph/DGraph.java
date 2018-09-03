package LifeScience.TraverseUnit.Graph;

import java.util.ArrayList;
import java.util.List;

public class DGraph {

	private int vexnum;
	private List<List<Integer>> matrix = new ArrayList<List<Integer>>();
	private ArrayList<EDistance> dist = new ArrayList<>();

	public List<List<Integer>> getMatrix() {
		return matrix;
	}

	public void setMatrix(List<List<Integer>> matrix) {
		this.matrix = matrix;
	}

	public ArrayList<EDistance> getDist() {
		return dist;
	}

	public void setDist(ArrayList<EDistance> dist) {
		this.dist = dist;
	}

	public DGraph(int vexnum) {
		// TODO Auto-generated constructor stub
		this.vexnum = vexnum;

		for (int i = 0; i < this.vexnum; i++) {
			List<Integer> newList = new ArrayList<>();
			for (int k = 0; k < this.vexnum; k++) {
				// 邻接矩阵初始化为无穷大
				newList.add(Integer.MAX_VALUE);
			}
			this.matrix.add(newList);
		}
	}

	public void createGraph(int a[][]) {
		int n = vexnum;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j] == 1) {
					matrix.get(i).set(j, 1);
				}
			}
		}
	}

	public void print() {
		for (int i = 0; i < vexnum; i++) {
			for (int j = 0; j < vexnum; j++) {
				System.out.print(matrix.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	public void Traverse(int begin) {
		for (int i = 0; i < vexnum; i++) {
			EDistance eDistance = new EDistance();
			eDistance.path = begin + " " + (i);
			eDistance.value = matrix.get(begin).get(i);
			dist.add(eDistance);
		}
		dist.get(begin).value = 0;
		dist.get(begin).visit = true;
		// if (begin == 1 || begin == 249) {
		// return;
		// }
		int count = 1;
		while (count != vexnum) {
			// temp用于保存当前dis数组中最小的那个下标
			// min记录的当前的最小值
			int temp = 0;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < vexnum; i++) {
				if (!dist.get(i).visit && dist.get(i).value < min) {
					min = dist.get(i).value;
					temp = i;
				}
			}
			dist.get(temp).visit = true;
			count++;
			for (int p = 0; p < vexnum; p++) {
				if (!dist.get(p).visit && (matrix.get(temp).get(p) != Integer.MAX_VALUE) && min != Integer.MAX_VALUE) {
					if ((dist.get(temp).value + matrix.get(temp).get(p)) < dist.get(p).value) {
						// 如果新得到的边可以影响其他为访问的顶点，那就就更新它的最短路径和长度
						dist.get(p).value = (dist.get(temp).value + matrix.get(temp).get(p));
						dist.get(p).path = dist.get(temp).path + " " + p;
					}
				}
			}
		}
	}

	public void printPath(int begin) {
		System.out.println("each shortest path begin with " + begin + " is: ");
		for (int i = 0; i < vexnum; i++) {
			if (dist.get(i).value != Integer.MAX_VALUE) {
				System.out.println(dist.get(i).path /* + " with the min: " + dist.get(i).value */);
				// System.out.print(dist.get(i).value+" ");
			} else {
				System.out.println("not exist ");
			}
		}
	}

	public SBrunch printTarge(int end) {
		SBrunch sBrunch = new SBrunch();
		String str = "";
		int r;
		if (dist.get(end).value != Integer.MAX_VALUE) {
			str += dist.get(end).path /* + " with:" + dist.get(end).value */;
			r = dist.get(end).value;
		} else {
			str += "not exist";
			r = -1;
		}
		sBrunch.brunchValue = r;
		sBrunch.road = str;
		return sBrunch;
	}
}
