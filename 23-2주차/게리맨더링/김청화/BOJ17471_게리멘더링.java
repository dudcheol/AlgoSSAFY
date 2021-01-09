package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471_게리멘더링 {
	static int N, people[], answer = Integer.MAX_VALUE;
	static boolean[] visited;
	static ArrayList<Integer> result = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> adjlist = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		people = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		adjlist.add(new ArrayList<Integer>());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			adjlist.add(new ArrayList<Integer>());
		}
		
		// 관계 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				adjlist.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 1; i <= N / 2; i++) {
			comb(1, i);
		}
		
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
	}

	private static void comb(int cur, int cnt) {
		if(cnt == 0) {
			countDiff(result);
			return;
		}
		
		for (int i = cur; i <= N; i++) {
			result.add(i);
			comb(i + 1, cnt - 1);
			result.remove(result.size() - 1);
		}
	}

	private static void countDiff(ArrayList<Integer> A) {
		ArrayList<Integer> B = new ArrayList<>(); // B 구역
		for (int i = 1; i <= N; i++) {
			if(A.contains(i))
				continue;
			B.add(i);
		}
		
		if(!isConnected(A) || !isConnected(B)) return;
		
		int numA = 0;
		for (int i = 0; i < A.size(); i++) {
			numA += people[A.get(i)];
		}
		
		int numB = 0;
		for (int i = 0; i < B.size(); i++) {
			numB += people[B.get(i)];
		}
		
		answer = Math.min(Math.abs(numA - numB), answer);
	}

	private static boolean isConnected(ArrayList<Integer> A) {
		boolean[] visited = new boolean[N + 1];
		visited[A.get(0)] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(A.get(0));
		
		int count = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i : adjlist.get(cur)) {
				// 이미 방문한 적이 없고, A에 속해야 함
				if(!visited[i] && A.contains(i)) {
					visited[i] = true;
					count++;
					q.add(i);
				}
			}
			
			if(count == A.size()) {
				return true;
			}
		}
		return false;
	}
}