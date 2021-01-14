/**
 * Backjoon - 2812. 크게 만들기
 * 크게만들기_2812.java
 * @date 2020-12-18
 * @author hansolKim
 **/

package p12000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 싸지방에간준하_12764 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<int[]> list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			list.add(new int[] {p, q});
		}
		
		Collections.sort(list, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]); 
			}
		});
		
		PriorityQueue<Integer> computerNumber = new PriorityQueue<>();
		
		for(int i=1; i<=N; i++) {
			computerNumber.add(i);
		}
		
		int[] count = new int[N+1];
		
		// 사용중인 컴퓨터 목록 : (0:끝나는 시각, 1:사용한 컴퓨터번호)
		PriorityQueue<int[]> useList = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		
		int answer = 0;
		for(int[] person : list) {
			int start = person[0];
			int end = person[1];
			
			// 현재 사용자가 이용하려는 시각에 컴퓨터 이용이 끝난 사람은 pq에서 빼주기
			while(!useList.isEmpty() && useList.peek()[0] <= start) {
				int useNumber = useList.poll()[1];
				computerNumber.add(useNumber); // 컴퓨터 번호 반환
			}
			
			int useComputerNumber = computerNumber.poll(); // 현재 사용자가 이용할 컴퓨터 번호
			count[useComputerNumber]++; // 이용한 사람의 수 증가
			useList.add(new int[] {end, useComputerNumber}); // 사용중인 리스트에 추가
			answer = answer<useComputerNumber ? useComputerNumber : answer;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(answer).append("\n");
		for(int i=1; i<=answer; i++) {
			sb.append(count[i]).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}