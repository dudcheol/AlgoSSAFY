import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B6087 {

	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(stz.nextToken());
		int M=Integer.parseInt(stz.nextToken());
		
		char[][] map=new char[M][N];
		int[][] visited=new int[M][N];
		
		boolean flag=true;
		
		int goalX=0;
		int goalY=0;
		
		Queue<Node> qu=new LinkedList<Node>();
		for(int i=0;i<M;i++) {
			String k=br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j]=k.charAt(j);
				visited[i][j]=Integer.MAX_VALUE;
				
				
				if(map[i][j]=='C' && flag) {
					flag=false;
					qu.add(new Node(i,j,-1,0));
					visited[i][j]=0;
				}
				
				
				if(map[i][j]=='C' && !flag) {
					goalX=i;
					goalY=j;
				}
			}
		}
		
		int min=Integer.MAX_VALUE;
		
		//bfs
		while(!qu.isEmpty()) {
			Node node=qu.poll();
			int x=node.x;
			int y=node.y;
			int direction=node.direction;
			int mirror=node.count;
			
			if(x==goalX && y==goalY) {
				
				min=Math.min(min,mirror);
			}
			
			for(int i=0;i<4;i++) {
				int rdx=x+dx[i];
				int rdy=y+dy[i];
				
				direction=i;
				
				if(rdx<0 || rdx>=M || rdy<0 || rdy>=N || map[rdx][rdy]=='*') {
					continue;
				}
				int temp=mirror;
				
				if(node.direction!=direction && node.direction!=-1) {
					temp++;
				}
				
				if(visited[rdx][rdy]<mirror) {
					continue;
				}
				
				
				visited[rdx][rdy]=mirror;
				qu.add(new Node(rdx,rdy,direction,temp));
				
				
			}
		}
//		System.out.println(visited[goalX][goalY]);
		System.out.println(min);
		

	}
	public static class Node{
		int x;
		int y;
		int direction;
		int count;
		
		public Node(int x, int y, int direction,int count) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.count=count;
		}
		
		
		
	}

}
