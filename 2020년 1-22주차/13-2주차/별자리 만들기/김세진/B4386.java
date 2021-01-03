package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B4386 {

	static int[] parent;
	static int N;

	static class Node implements Comparable<Node> {
		double x;
		double y;
		double d;

		public Node(double x, double y, double d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		// A.compareTo(B)
		// A=B return 0
		// A>B return 1
		// A<B return -1

		// d에 관해서 오름차순
		@Override
		public int compareTo(Node o) {
			if (this.d > o.d) {
				return 1;
			} else {
				return -1;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		parent = new int[N];
		Node[] node = new Node[N];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		double cost = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(stz.nextToken());
			double y = Double.parseDouble(stz.nextToken());

			node[i] = new Node(x, y, 0);
		}

		for (int i = 0; i < N - 1; i++) {
			Node a = node[i];
			for (int j = i + 1; j < N; j++) {
				Node b = node[j];

				double dist = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
				pq.offer(new Node(i, j, dist));
			}
		}
		
		MakeSet();
		
		
		while(!pq.isEmpty()) {
			Node n=pq.poll();
			int x=(int)n.x;
			int y=(int)n.y;
			
			int a=getParent(x);
			int b=getParent(y);
			
			if(a==b) {
				continue;
			}
			
			union(x,y);
			cost+=n.d;
			
		}
		System.out.println(String.format("%.2f", cost));
	}

	public static void MakeSet() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	public static int find(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if (a == b) {
			return 1;
		} else {
			return 0;
		}

	}

	public static void union(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a < b) {
			parent[b] = a;
		}else{
            parent[a] = b;
        }
	}

	public static int getParent(int a) {
		if (parent[a] == a) {
			return a;
		}

		return parent[a]=getParent(parent[a]);
	}
}
