/**
 * Backjoon - 17406. 배열 돌리기 4
 * 배열돌리기4_17406.java
 * @date 2021-01-04
 * @author hansolKim
 **/

package p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기4_17406 {

	static int N, M, K, answer; // 행의 크기, 열의 크기, 회전 수행 개수, 정답
	static int[] data;
	static int[][] map, rotateInfo; // 배열 값, 회전정보값
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		// 배열 정보입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	    rotateInfo = new int[K][3]; // r-s, c-s, r+s, c+s 
		// 회전수행 값 입력
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			
			rotateInfo[i][0] = r;
			rotateInfo[i][1] = c;
			rotateInfo[i][2] = s;
		}
		
		visited = new boolean[K];
		data = new int[K];
		answer = Integer.MAX_VALUE;
		permutation(0);
		System.out.println(answer);
		br.close();
	}

	private static void permutation(int cnt) {
		if(cnt == K) {			
			int[][] temp = new int[N][M];
			
			// 배열 복사
			for(int i=0; i<N; i++) {
				temp[i] = map[i].clone();
			}			
			
			for(int i=0; i<K; i++) {
				rotate(rotateInfo[data[i]][0], rotateInfo[data[i]][1], rotateInfo[data[i]][2], temp);
			}
			
			int value = getValue(temp);
			
			answer = answer<value ? answer : value;
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(visited[i]) continue;
			data[cnt] = i;
			visited[i] = true;
			permutation(cnt+1);
			visited[i] = false;
		}
	}

	private static int getValue(int[][] map) {
		int value = Integer.MAX_VALUE; 
		for(int i=0; i<N; i++) {
			int sum = 0; 
			for(int j=0; j<M; j++) {
				sum += map[i][j];
			}
			
			value = value < sum ? value : sum;
		}
		
		return value;
	}

	// (x, y) ~ (toX, toY)의 시계방향 회전 구현 메서드
	private static void rotate(int midX, int midY, int size, int[][] map) {
		
		while(size>0) {
			
			// 시작위치 temp 저장
			int x = midX-size;
			int y = midY-size;
			
			int temp = map[x][y];
			
			while(x<midX+size) { // x가 증가하는 구간 (midX+size까지)
				map[x][y] = map[x+1][y];
				x++;
			}
			
			while(y<midY+size) { // y가 증가하는 구간
				map[x][y] = map[x][y+1];
				y++;
			}
			
			while(x>midX-size) { // x가 감소하는 구간
				map[x][y] = map[x-1][y];
				x--;
			}
			
			while(y>midY-size) { // y가 감소하는 구간
				map[x][y] = map[x][y-1];
				y--;
			}
			
			map[x][y+1] = temp;
			size--;
		}
	}
}