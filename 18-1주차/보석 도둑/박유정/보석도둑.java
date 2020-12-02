package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보석도둑 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Gem> q=new PriorityQueue<Gem>();
		PriorityQueue<Integer> bag=new PriorityQueue<Integer>(Collections.reverseOrder());
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st= new StringTokenizer(br.readLine());
			int m=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			q.add(new Gem(m,v));
		}
		for (int i = 0; i < k; i++) {
			int m=Integer.parseInt(br.readLine());
			bag.add(m);
		}
		int answer=0;
		
		while(!bag.isEmpty()&&!q.isEmpty()) {
			int b=bag.poll();
			boolean flag=false;
			Gem g=q.poll();
			
			if(g.m>b) {
				while(!q.isEmpty()) {
					g=q.poll();
					if(g.m<=b) {
						flag=true;
						break;
					}
				}
			}else {
				flag=true;
			}
			if(flag) {
				answer+=g.v;
			}
		}
		System.out.println(answer);
		
	}
}
class Gem implements Comparable<Gem>{
	int m;
	int v;
	
	public Gem(int m, int v) {
		super();
		this.m = m;
		this.v = v;
	}
	
	@Override
	public int compareTo(Gem o) {
		if(this.v==o.v) {
			return o.m-this.m;
		}
		return o.v-this.v;
	}
}
