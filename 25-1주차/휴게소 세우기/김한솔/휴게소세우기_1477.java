/**
 * Backjoon - 1477. 휴게소 세우기
 * 휴게소세우기_1477.java
 * @date 2021-01-20
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 휴게소세우기_1477 {

	static class Rest implements Comparable<Rest>{
		double len;
		int cnt;

		public Rest(double len, int cnt) {
			this.len = len;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Rest o) {
			return Double.compare(o.len, this.len); // 내림차순 정렬
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 기존 휴게소의 개수
		int M = Integer.parseInt(st.nextToken()); // 추가 휴게소의 개수
		int L = Integer.parseInt(st.nextToken()); // 고속도로의 길이
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+2];
		arr[0] = 0;
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		arr[N+1] = L;
		
		Arrays.sort(arr);
		
		PriorityQueue<Rest> pq = new PriorityQueue<>();
		for(int i=1; i<arr.length; i++) {
			pq.add(new Rest(arr[i]-arr[i-1], 1));
		}
		
		while(M-->0) {
			Rest rest = pq.poll();
			// System.out.println(rest.len + " " + rest.cnt);
			pq.add(new Rest((rest.len*rest.cnt)/(rest.cnt+1), rest.cnt+1));
		}
		
		double min = pq.poll().len;
		int minInteger = (int) min;
		
		System.out.println(min>minInteger ? minInteger+1 : minInteger);
		br.close();
	}
}