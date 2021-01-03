package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 집합의표현 {
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(st.nextToken())+1;
		int m=Integer.parseInt(st.nextToken());
		
		parent=new int[n];
		for (int i = 0; i < n; i++) {
			parent[i]=i;
		}
		
		for (int i = 0; i < m; i++) {
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			if(num==0) {
				union(x,y);
			}else {
				sb.append(check(x,y)?"YES":"NO").append("\n");
			}
		}
		System.out.println(sb);
		
	}
	
	private static boolean check(int x, int y) {
		int px=find(x);
		int py=find(y);
		if(px==py)
			return true;
		return false;
	}

	private static void union(int x, int y) {
		if(!check(x,y)) {
			parent[parent[y]]=parent[parent[x]];
		}
	}

	private static int find(int x) {
		if(parent[x]==x)
			return x;
		return parent[x]=find(parent[x]);
	}

}
