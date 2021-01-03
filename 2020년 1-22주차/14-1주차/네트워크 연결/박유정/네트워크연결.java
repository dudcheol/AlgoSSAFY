package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 네트워크연결 {//크루스칼
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int v = Integer.parseInt(br.readLine());

		PriorityQueue<Dot> pq = new PriorityQueue<Dot>();
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < v; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Dot(a, b, cost));
		}
		int count=0;
		int sum=0;
		while (!pq.isEmpty()) {
			Dot d = pq.poll();
			int x = d.x;
			int y = d.y;
			int cost = d.cost;
			
			if(union(x, y)) {
				count++;
				sum+=cost;
			}
			if(count==n-1)
				break;
		}
		System.out.println(sum);
	}

	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x!=y) {
			parent[x]=y;
			return true;
		}
		return false;
	}

	private static int find(int x) {
		if(parent[x]==x)
			return x;
		
		return parent[x]=find(parent[x]);
	}
}

class Dot implements Comparable<Dot> {
	int x;
	int y;
	int cost;

	public Dot(int x, int y, int cost) {
		super();
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(Dot o) {
		return this.cost - o.cost;
	}

}