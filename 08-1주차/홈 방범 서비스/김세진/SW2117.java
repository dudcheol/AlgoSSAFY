import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW2117 {
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static boolean[][] visit;
	static int[][] map;
	static int N,M;
	
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			// input
			StringTokenizer stz = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stz.nextToken());
			M = Integer.parseInt(stz.nextToken());

			map = new int[N][N];
			
			Queue<Node> qu=new LinkedList<Node>();
			
			for (int i = 0; i < N; i++) {
				stz = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stz.nextToken());
				}
			}
			
			// solve
			
			
			int max=Integer.MIN_VALUE;
			
			//전체 범위
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					
					//visit배열 초기화
					visit=new boolean[N][N];
					//계산한 가치 변수
					int value=0;
					
					//최소 땅의 크기
					int k=1;
					//최초 위치 in
					qu.offer(new Node(i,j));
					visit[i][j]=true;

					while(!qu.isEmpty()) {
						//계산 
						value=cal(k++);
						//맥스보다 크면 
						if(max<value) {
							max=value;
						}
						
						//사이즈만큼 돌 수 있도록
						int size=qu.size();
						
						for(int z=0;z<size;z++) {
							Node node=qu.poll();
							int x=node.x;
							int y=node.y;
							
							//4방향으로 늘려준다
							for(int a=0;a<4;a++) {
								int rdx=x+dx[a];
								int rdy=y+dy[a];
								
								if(rdx<0 || rdy<0||rdx>=N||rdy>=N || visit[rdx][rdy]) {
									continue;
								}
								
								visit[rdx][rdy]=true;
								qu.offer(new Node(rdx,rdy));
								
							}
						}
					}
					
					
					
				}
			}
			
			
			
			// output
			System.out.println("#"+TC+" "+max);
		}

	}

	
	//값 계산
	static int cal(int k) {
		int home=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visit[i][j] && map[i][j]==1) {
					home++;
				}
			}
		}
		
		// 집 개수*M-(운영비용) 
		int cost=home*M-((k*k)+((k-1)*(k-1)));
		//0까지는 손해를 보지않는다.
		if(cost>=0) {
			return home;
		}else {
			return 0;
		}
		
		
	}
}
