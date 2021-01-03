package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1713_후보추천하기 {
	static int N, C;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder string = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		C = Integer.parseInt(br.readLine());
		
		PriorityQueue<Candidate> pq = new PriorityQueue<>();
		PriorityQueue<Candidate> tmp = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean flag;
		for (int i = 0; i < C; i++) {	
			int num = Integer.parseInt(st.nextToken());
			
			flag = false;
//			if(pq.contains())
			
			if(pq.size() < N) {
				pq.add(new Candidate(num, 1, i));
				continue;
			}
			
			while(!pq.isEmpty()) {
				Candidate cur = pq.poll();
				if(cur.num == num) {
					cur.count++;
//					cur.update = i;
					flag = true;
				}
				tmp.add(cur);
			}
			while(!tmp.isEmpty()) {
				pq.add(tmp.poll());
			}
			
//			for (Candidate c : pq) {
//				if(c.num == num){
//					c.count++;
//					flag = true;
//					break;
//				}
//			}
			
			if(!flag) {				
				if(pq.size() == N)
					pq.poll();
				pq.add(new Candidate(num, 1, i));
			}
		}
		
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			result[i] = pq.poll().num;
		}
		Arrays.sort(result);
		for (int i = 0; i < N; i++) {
			string.append(result[i]).append(" ");
		}
		string.append("\n");
		
		System.out.println(string);
	}
	
	static class Candidate implements Comparable<Candidate>{
		int num; // 학생번호
		int count; // 추천횟수
		int update;
		
		Candidate(int num, int count, int update){
			this.num = num;
			this.count = count;
			this.update = update;
		}
		@Override
		public int compareTo(Candidate o) {
			if(o.count != this.count)
				return count - o.count; // 추천횟수 오름차순
			else
				return update - o.update; // 업데이트 오름차순 (업데이트 숫자 작으면 더 오래 전에 업데이트)
		}
	}

}
