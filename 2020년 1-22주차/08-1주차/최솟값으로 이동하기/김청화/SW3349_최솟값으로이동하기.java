package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//swea 3349 최솟값으로 이동하기 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWDTN0cKr1oDFAWD&categoryId=AWDTN0cKr1oDFAWD&categoryType=CODE&&&
public class SW3349_최솟값으로이동하기 {
	static int T, W, H, N, result;
	static int[][] points, dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}}; //상하좌우대각선위방향
	static int[][] distance;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder string = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			result = 0;
			points = new int[N][N];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				points[n][0] = Integer.parseInt(st.nextToken());
				points[n][1] = Integer.parseInt(st.nextToken());
			}
			
			for (int n = 0; n < N - 1; n++) {
				int x = points[n][0];
				int y = points[n][1];
				int destx = points[n + 1][0];
				int desty = points[n + 1][1];
				
				while(true) {
					if(y == desty) {
						result += Math.abs(destx - x); // x 차이의 절대값만큼 이동
						break;
					} else if (x == destx) {
						result += Math.abs(desty - y); // y 차이의 절대값만큼 이동
						break;
					}else if(y > desty && x > destx) { // 대각선으로 이동
						y -= 1;
						x -= 1;
					} else if (y < desty && x < destx) { // 대각선 이동
						y += 1;
						x += 1;
					} else if(y > desty) {
						y -= 1;
					} else if(x > destx) {
						x -= 1;
					} else if(y < desty) {
						y += 1;
					} else if(x < destx) {
						x += 1;
					}
					result += 1;
					
					if (x == destx && y == desty)
						break;
				}
			}
			string.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(string);
	}
}
