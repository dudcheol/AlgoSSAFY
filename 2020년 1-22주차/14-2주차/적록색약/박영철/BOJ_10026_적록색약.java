package week14;

import java.io.*;
import java.util.*;

public class BOJ_10026_적록색약 {

	private static int N;
	private static char[][] map;
	private static boolean[][] visited;
	private static int[] dy = {-1,1,0,0};
	private static int[] dx = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		map = new char[N][];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 어차피 전부 다 돌아봐야 하므로 dfs도 괜찮다고 생각함
		int no = 0; // 적록색약 아닌사람
		int yes = 0; // 적록색약인 사람
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				dfs(i, j, Character.toString(map[i][j]));
				no++;
			}
		}
		
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				
				String color = null;
				switch(map[i][j]) {
				case 'R':
				case 'G':
					color = "RG";
					break;
				case 'B':
					color = "B";
					break;
				}
				dfs(i, j, color);
				yes++;
			}
		}
		
		System.out.println(no + " " + yes);
	}

	private static void dfs(int y, int x, String color) {
		visited[y][x] = true;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny<0||nx<0||ny>=N||nx>=N||visited[ny][nx]||!color.contains(Character.toString(map[ny][nx]))) continue;
			dfs(ny,nx,color);
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

}
