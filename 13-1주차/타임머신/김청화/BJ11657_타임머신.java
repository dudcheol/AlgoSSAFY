package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 11657 타임머신 https://www.acmicpc.net/problem/11657
// 벨만-포드
public class BJ11657_타임머신 {
	static int N, M; // N개 도시, M개 버스
	static long[] distance; 
	static ArrayList<int[]> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		distance = new long[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list.add(new int[] {u - 1, v - 1, w});
		}
		
		distance[0] = 0; // 자기 자신으로 가는 최단 경로 = 0
		for (int i = 0; i < N -1; i++) { // 이 부분 이해 안 감 (그냥 한 번만 더 돌리면 안 되나...?)
			for (int j = 0; j < M; j++) {
				if(distance[list.get(j)[0]] != Integer.MAX_VALUE && distance[list.get(j)[1]] > distance[list.get(j)[0]] + list.get(j)[2]) {
					distance[list.get(j)[1]] = distance[list.get(j)[0]] + list.get(j)[2];
				}
			}
		}
		
		boolean check = false;
		
		for (int i = 0; i < M; i++) {
			if(distance[list.get(i)[0]] != Integer.MAX_VALUE && distance[list.get(i)[1]] > distance[list.get(i)[0]] + list.get(i)[2]) {
				check = true;
				break;
			}
		}
		
		if(check)
			System.out.println(-1);
		else {
			for (int i = 1; i < N; i++) {
				if(distance[i] != Integer.MAX_VALUE)
					System.out.println(distance[i]);
				else
					System.out.println(-1);
			}
		}
	}
}
