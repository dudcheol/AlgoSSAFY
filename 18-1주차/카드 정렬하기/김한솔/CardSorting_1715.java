/**
 * Backjoon - 1715. 카드 정렬하기
 * CardSorting_1715.java
 * @date 2020-12-02
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class CardSorting_1715 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			pq.add(num);
		}
		
		int answer = 0;
		while(pq.size() > 1) {
			int x = pq.poll();
			int y = pq.poll();
			answer += (x+y);
			pq.add(x+y);
		}
		
		System.out.println(answer);
		br.close();
	}

}
