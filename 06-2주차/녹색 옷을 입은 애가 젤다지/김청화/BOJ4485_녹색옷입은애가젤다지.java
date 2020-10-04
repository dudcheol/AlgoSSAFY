package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ4485 녹색 옷 입은 애가 젤다지? https://www.acmicpc.net/problem/4485

public class BOJ4485_녹색옷입은애가젤다지 {
	
	static int N, result, probnum; 
	static int[][] map, totalWeight, dirs = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder string = new StringBuilder();
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			probnum++;

			map = new int[N][N];
			totalWeight = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				Arrays.fill(totalWeight[i], Integer.MAX_VALUE);
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
			totalWeight[0][0] = map[0][0]; // 출발지 weight는 자기 자신으로 초기화
			pq.offer(new Vertex(0, 0, map[0][0]));
			Vertex current;
			
			while(!pq.isEmpty()) {
				current = pq.poll();
				
				for (int i = 0; i < 4; i++) {
					int nx = current.x + dirs[i][0];
					int ny = current.y + dirs[i][1];
					
					// 현재 weight + map[nx][ny]값 (경유해서 도달한 값)이 이전에 계산한 weight[nx][ny]값보다 작으면 업데이트
					if(isIn(nx, ny) && current.weight + map[nx][ny] < totalWeight[nx][ny]) {
						totalWeight[nx][ny] = current.weight + map[nx][ny];
						pq.offer(new Vertex(nx, ny, current.weight + map[nx][ny]));
					}
				}
			}
			result = totalWeight[N - 1][N - 1];
			string.append("Problem " + probnum + ": " + result + "\n");
		}
		
		System.out.println(string);
	}
	
	static class Vertex implements Comparable<Vertex> {
		int x, y, weight;
		
		public Vertex(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight; // weight가 작은 비용 우선적으로 처리
		}
	}
	
	static boolean isIn(int nx, int ny) {
		if(nx >= 0 && ny >= 0 && nx < N && ny < N)
			return true;
		return false;
	}
}
