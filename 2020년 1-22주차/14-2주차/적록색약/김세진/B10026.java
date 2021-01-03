import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B10026 {
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	
	public static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		char[][] map=new char[N][N];
		char[][] llmap=new char[N][N];
		boolean[][] visited=new boolean[N][N];
		for(int i=0;i<N;i++) {
			map[i]=br.readLine().toCharArray();
		}
		
		llmap=map.clone();
		
		Queue<Node> qu=new LinkedList<Node>();
		int Cnt=0;
		
		//적록색약이 아닌 사람cnt;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				
				if(!visited[i][j]) {
					qu.add(new Node(i,j));
					visited[i][j]=true;
					
					while(!qu.isEmpty()) {
						Node node=qu.poll();
						int x=node.x;
						int y=node.y;
						for(int a=0;a<4;a++) {
							int rdx=x+dx[a];
							int rdy=y+dy[a];
							
							if(rdx<0 || rdy<0 || rdx>=N ||rdy>=N || visited[rdx][rdy]|| map[rdx][rdy]!=map[x][y]) {
								continue;
							}
							
							visited[rdx][rdy]=true;
							qu.add(new Node(rdx,rdy));
						}
					}
					Cnt++;
				}
			}
		}
		qu=new LinkedList<Node>();
		//적록색약인 사람이 보는 화면으로 변경
		//green을 red로 다 변경
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				boolean flag=false;
				for(int k=0;k<4;k++) {
					int rdx=i+dx[k];
					int rdy=j+dy[k];
					
					if(rdx<0 || rdy<0 || rdx>=N ||rdy>=N) {
						continue;
					}
					if(llmap[rdx][rdy]!=llmap[i][j]) {
						break;
					}
					
					llmap[i][j]=llmap[rdx][rdy];
				}
				if(llmap[i][j]=='G') {
					llmap[i][j]='R';
				}
			}
		}
		int llCnt=0;
		visited=new boolean[N][N];
		//적록색약 cnt
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				
				if(!visited[i][j]) {
					qu.add(new Node(i,j));
					visited[i][j]=true;
					
					while(!qu.isEmpty()) {
						Node node=qu.poll();
						int x=node.x;
						int y=node.y;
						
						for(int a=0;a<4;a++) {
							int rdx=x+dx[a];
							int rdy=y+dy[a];
							
							if(rdx<0 || rdy<0 || rdx>=N ||rdy>=N || visited[rdx][rdy] || llmap[x][y]!=llmap[rdx][rdy]) {
								continue;
							}
							
							visited[rdx][rdy]=true;
							qu.add(new Node(rdx,rdy));
						}
					}
					llCnt++;
				}
			}
		}
		//사실 맵을 따로 만들필요없이 맵 하나로 가능
		System.out.println(Cnt+" "+llCnt);
	}

}
