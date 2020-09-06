package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B4485 {
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int value;

		public Node(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			
			return this.value-o.value;
		}
		

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		final int INF=Integer.MAX_VALUE;
		
		
		int index=1;
		while (true) {
			int N = Integer.parseInt(br.readLine());
			
			if(N==0) {
				break;
			}
			// input
			
			PriorityQueue<Node> PQ = new PriorityQueue<Node>();
			int[][] map = new int[N][N];
			int[][] cost=new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer stz = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stz.nextToken());
					cost[i][j]=INF;
				}
			}

			// solve
			
			//시작점 세팅
			int answer=0;
			cost[0][0]=map[0][0];
			PQ.add(new Node(0,0,map[0][0]));
			
			while(!PQ.isEmpty()) {
				Node node=PQ.poll();
				int x=node.x;
				int y=node.y;
				int value=node.value;
				
				if(cost[x][y]<value) {
					continue;
				}
				
				
				for(int i=0;i<4;i++) {
					int rdx=x+dx[i];
					int rdy=y+dy[i];
					
					if(rdx<0 || rdy<0 || rdx>=N || rdy>=N) {
						continue;
					}
					
					if(cost[rdx][rdy] > cost[x][y]+map[rdx][rdy]) {
						cost[rdx][rdy]=cost[x][y]+map[rdx][rdy];
						PQ.add(new Node(rdx,rdy,cost[rdx][rdy]));
					}
				}
				
				
			}
			answer=cost[N-1][N-1];
			System.out.println("Problem "+index+": "+answer);
			index++;
		}

	}

}
