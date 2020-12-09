/**
 * Backjoon - 2014. 소수의 곱
 * MultipleOfPrimeNumber_2014.java
 * @date 2020-12-08
 * @author hansolKim
 **/

package p2000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MultipleOfPrimeNumber_2014 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken()); // 소수의 개수
		int N = Integer.parseInt(st.nextToken()); // N번째 수
		
		st = new StringTokenizer(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int[] primeNumber = new int[K];
		
		// 소수 저장
		for(int i=0; i<K; i++) {
			int num = Integer.parseInt(st.nextToken());
			primeNumber[i] = num;
			pq.add(num);
		}
		
		// N-1번의 수행
		for(int i=0; i<N-1; i++) {

			int curNum = pq.poll();
			
			for(int j=0; j<K; j++) {
				long num = (long)curNum*primeNumber[j]; 
				if(num >= Integer.MAX_VALUE) {break;}				
				pq.add((int) num);
				if(curNum % primeNumber[j] == 0) { break; }
			}
			
		}
		
		// N번째 poll한 값이 정답
		long answer = pq.poll();
		
		bw.write(answer+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
