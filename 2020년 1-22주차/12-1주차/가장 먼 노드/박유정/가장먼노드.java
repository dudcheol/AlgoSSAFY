package study2;

import java.util.*;

public class 가장먼노드 {
	public int solution(int n, int[][] edge) {
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 1; i <= n; i++) {
			hm.put(i, new ArrayList<Integer>());//노드별로 해쉬 생성
		}
		for (int i = 0; i < edge.length; i++) {
			int a = edge[i][0];
			int b = edge[i][1];

			ArrayList<Integer> a1 = hm.get(a);
			ArrayList<Integer> a2 = hm.get(b);
			//각각넣어줌
			a1.add(b);
			hm.put(a, a1);

			a2.add(a);
			hm.put(b, a2);

		}

		int[] node = new int[n + 1];//level 저장

		Queue<Pair> q = new LinkedList<Pair>();

		q.add(new Pair(1, 1));//출발점,level
		node[1] = 1;
		int level=0;
		
		while (!q.isEmpty()) {//bfs
			Pair p = q.poll();

			int num = p.node;
			level = p.level;
			
			ArrayList<Integer> al = hm.get(num);

			for (int i : al) {
				if (node[i]!=0)
					continue;

				q.add(new Pair(i, level + 1));
				node[i] = level+1;
			}
		}
		
		int count=0;
		for (int i :node) {
			if(i==level)//bfs니까 마지막level에 저장된값이 제일 큼 같은 level 인것들 count;
				count++;
		}
		return count;
	}
}

class Pair {
	int node;
	int level;

	public Pair(int node, int level) {
		super();
		this.node = node;
		this.level = level;
	}

}
