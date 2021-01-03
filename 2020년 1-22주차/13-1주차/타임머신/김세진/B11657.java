package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11657 {

	public static class Node{
		int start;
		int end;
		int cost;
		
		public Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(stz.nextToken());
		int M=Integer.parseInt(stz.nextToken());

		Node[] nodeArr=new Node[M];
		long[] distance=new long[N+1];
		
		int INF=Integer.MAX_VALUE;
		
		for(int i=0;i<M;i++) {
			stz=new StringTokenizer(br.readLine());
			
			int start=Integer.parseInt(stz.nextToken());
			int end=Integer.parseInt(stz.nextToken());
			int cost=Integer.parseInt(stz.nextToken());
			
			nodeArr[i]=new Node(start,end,cost);
		}
		
		
		//solve
		
		
		//초기화
		
		//모든 노드 INF and 시작점 0
		for(int i=1;i<N+1;i++) {
			distance[i]=INF;
		}
		distance[1]=0;
		
		//노드에 대한 모든 간선 정보
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M;j++) {
				if(distance[nodeArr[j].start]==INF) {
					continue;
				}
				if(distance[nodeArr[j].end]>(distance[nodeArr[j].start])+nodeArr[j].cost) {
					distance[nodeArr[j].end]=(distance[nodeArr[j].start])+nodeArr[j].cost;
				}
			}
		}
		
		
		for(int i=0;i<M;i++) {
			if(distance[nodeArr[i].start]!=INF && distance[nodeArr[i].end]>(distance[nodeArr[i].start])+nodeArr[i].cost) {
				System.out.println(-1);
				return;
			}
		}
		
		
		for(int i=2;i<=N;i++) {
			if(distance[i]==INF) {
				System.out.println(-1);
			}else {
				System.out.println(distance[i]);
			}
		}
		
	}
	
	
}
