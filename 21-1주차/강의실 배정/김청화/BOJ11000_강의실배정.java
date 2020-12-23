package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000_강의실배정 {
	static int N, max;
	static ArrayList<lecture> lt = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			lt.add(new lecture(start, end));
		}
		
		Collections.sort(lt);
	
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(lt.get(0).end);
		for (int i = 1; i < lt.size(); i++) {
			if(pq.peek() <= lt.get(i).start)
				pq.poll();
			
			pq.add(lt.get(i).end);
			
			max = max < pq.size() ? pq.size() : max;
		}
		
		System.out.println(max);
	}

	public static class lecture implements Comparable<lecture> {
		int start;
		int end;
		
		lecture(int start, int end){
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(lecture o) {
			return this.start - o.start;
		}
	}
}
