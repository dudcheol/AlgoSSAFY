package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 별자리만들기 {
	static int[] parent;
	static boolean[][] link;
	static int num;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		
		PriorityQueue<Node> q=new PriorityQueue<Node>();
		ArrayList<Node> al=new ArrayList<Node>();
		
		link =new boolean[num][num];//연결된것 체크
		parent=new int[num];//부모
		
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());

			al.add(new Node(a,b));//좌표는 인덱스로관리
		}
		
		for (int i = 0; i < num; i++) {
			parent[i]=i;
		}
		
		for (int i = 0; i < num; i++) {
			Node a=al.get(i);
			double x=a.x;
			double y=a.y;
			
			for (int j = i+1; j < num; j++) {
				Node b=al.get(j);
				double cost=Math.sqrt(Math.pow(Math.abs(x-b.x),2)+Math.pow(Math.abs(y-b.y),2));
				q.add(new Node(i,j,cost));
			}
		}
		
		double sum=0;
		int count=0;
		
		while(!q.isEmpty()) {
			Node n=q.poll();
			int index1=n.index1;
			int index2=n.index2;
			
			if(link[index1][index2])
				continue;
			
			if(union(index1,index2)) {
				link[index1][index2]=true;
				link[index2][index1]=true;
				sum+=n.cost;
				count++;
			}
			
			if(count==num-1)
				break;
			
		}
		System.out.println(Math.round(sum*100)/100.0);

	}

	public static boolean union(int a, int b) {
		{
			int x = find(a);
			int y = find(b);

			if (x != y) {
				parent[y]=x;
				return true;
			}
			return false;
		}
	}

	private static int find(int a) {
		if(parent[a]==a)
			return a;
		return parent[a]=find(parent[a]);
	}

}
class Node implements Comparable<Node>{
	double x;
	double y;
	double cost;
	int index1;
	int index2;
	
	public Node(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Node( int index1, int index2,double cost) {
		super();
		this.cost = cost;
		this.index1 = index1;
		this.index2 = index2;
	}


	@Override
	public int compareTo(Node o) {
		if (this.cost < o.cost) {
			return -1;
		}
		return 1;
	}
	
}
