package week29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_모의_미생물격리 {
	private static int N,M,K,dy[]= {-1,1,0,0},dx[]= {0,0,-1,1}, convert[]= {1,0,3,2};
	private static Cell[][] map;
	private static class Cell{
		int cnt;
		int d;
		public Cell(int cnt, int d) {
			this.cnt = cnt;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			map=new Cell[N][N];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int y=Integer.parseInt(st.nextToken());
				int x=Integer.parseInt(st.nextToken());
				int cnt=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken())-1;
				map[y][x]=new Cell(cnt,d);
			}
			
			for (int i = 0; i < M; i++) {
				simulate();
			}
			
			int sum=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]!=null) {
						sum+=map[i][j].cnt;
					}
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(sum).append('\n');
		}
		System.out.print(sb);
	}

	private static void simulate() {
		ArrayList<Cell>[][] tmp = new ArrayList[N][N];
		
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]!=null) {
					Cell cell = map[i][j];
					int cnt = cell.cnt;
					int d = cell.d;
					int ny=i+dy[d];
					int nx=j+dx[d];
					if(ny<1||nx<1||ny>=N-1||nx>=N-1) {  // 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다. 
						cnt/=2;
						d=convert[d];
						if(cnt!=0) {
							if(tmp[ny][nx]==null) {
								tmp[ny][nx]=new ArrayList<Cell>();
							}
							tmp[ny][nx].add(new Cell(cnt,d));
						}
					}else {
						if(tmp[ny][nx]==null) {
							tmp[ny][nx]=new ArrayList<Cell>();
						}
						tmp[ny][nx].add(new Cell(cnt,d));
					}
				}
			}
		}
		
		// 여러개인 군집 처리
		Cell[][] ret = new Cell[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(tmp[i][j]!=null) {
					int max=0;
					int sum=0;
					int nd=0;
					for (int k = 0; k < tmp[i][j].size(); k++) {
						if(max < tmp[i][j].get(k).cnt) {
							max=tmp[i][j].get(k).cnt;
							nd=tmp[i][j].get(k).d;
						}
						sum+=tmp[i][j].get(k).cnt;
					}
					ret[i][j]=new Cell(sum,nd);
				}
			}
		}
		
		map=ret;
	}
}
