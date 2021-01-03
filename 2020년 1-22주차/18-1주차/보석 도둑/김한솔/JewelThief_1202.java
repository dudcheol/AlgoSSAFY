/**
 * Backjoon - 1202. 보석 도둑
 * JewelThief_1202.java
 * @date 2020-12-02
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JewelThief_1202 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 보석 가게의 개수
		int K = Integer.parseInt(st.nextToken()); // 가방의 개수
		
		// 보석리스트를 가격 내림차순으로 정렬
		ArrayList<int[]> jewelList = new ArrayList<>();		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken()); // 보석의 무게
			int V = Integer.parseInt(st.nextToken()); // 보석의 가격
			
			jewelList.add(new int[] {M, V});
		}
		
		Collections.sort(jewelList, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		
		PriorityQueue<Integer> bags = new PriorityQueue<>();

		// 가방 크기 입력
		for(int i=0; i<K; i++) {
			bags.add(Integer.parseInt(br.readLine()));
		}
		
		// 가방이 존재하지 않을 때 까지 보석 넣기
		long answer = 0;
		int idx = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		while(!bags.isEmpty()) {
			int bag = bags.poll(); // 가방의 무게
			
			// 현재 가방보다 가벼운 보석 모두 pq에 넣기
			while(idx<jewelList.size() && jewelList.get(idx)[0] <= bag) {
				pq.add(jewelList.get(idx)[1]); // 보석 가격 pq에 삽입
				idx++;
			}
			
			// 훔칠 보석이 있는 경우
			if(!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		
		System.out.println(answer);
		br.close();
	}
}