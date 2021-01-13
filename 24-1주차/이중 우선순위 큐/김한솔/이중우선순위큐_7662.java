/**
 * Backjoon - 7662. 이중 우선순위 큐
 * 이중우선순위큐_7662.java
 * @date 2021-01-13
 * @author hansolKim
 **/

package p7000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이중우선순위큐_7662 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()); // testcase 수
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> minQueue = new PriorityQueue<>(); // 오름차순 정렬
		PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
		HashMap<Integer, Integer> minMap = new HashMap<>();
		HashMap<Integer, Integer> maxMap = new HashMap<>();
		
		while(T-->0) {
			int k = Integer.parseInt(br.readLine()); // 연산의 개수
			int cnt = 0;
			
			while(k-->0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				String cmd = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if(cmd.equals("I")) {
					minQueue.add(num);
					maxQueue.add(num);
					cnt++;
				} else {
					if(cnt == 0) {
						maxQueue.clear();
						minQueue.clear();
						minMap.clear();
			            maxMap.clear();
						continue; 
					} // 큐에 아무것도 없는 경우
					int value = 1;
					
					if(num == 1) { // 최댓값 삭제
						int key = maxQueue.poll();
						while(maxMap.containsKey(key)) {
							if (maxMap.get(key)-1 == 0) {
								maxMap.remove(key);
							} else {
								maxMap.put(key, maxMap.get(key)-1);
							}
							key = maxQueue.poll();
						}
						
						if(minMap.containsKey(key)) {
							value = minMap.get(key)+1;
						}
						
						minMap.put(key, value);
					} else { // 최솟값 삭제
						int key = minQueue.poll();
						while(minMap.containsKey(key)) {
							if (minMap.get(key)-1 == 0) {
								minMap.remove(key);
							} else {
								minMap.put(key, minMap.get(key)-1);
							}
							key = minQueue.poll();
						}
						
						if(maxMap.containsKey(key)) {
							value = maxMap.get(key)+1;
						}
						
						maxMap.put(key, value);
					}
					
					cnt--;
				}
			}
			
			if(cnt == 0) {
				sb.append("EMPTY");
			} else {
				
				int maxValue = maxQueue.poll();
				int minValue = minQueue.poll();
				
				while(maxMap.containsKey(maxValue)) {
					if (maxMap.get(maxValue)-1 == 0) {
						maxMap.remove(maxValue);
					} else {
						maxMap.put(maxValue, maxMap.get(maxValue)-1);
					}
					maxValue = maxQueue.poll();
				}
				
				while(minMap.containsKey(minValue)) {
					if (minMap.get(minValue)-1 == 0) {
						minMap.remove(minValue);
					} else {
						minMap.put(minValue, minMap.get(minValue)-1);
					}
					minValue = minQueue.poll();
				}
				
				sb.append(maxValue).append(" ").append(minValue);	
			}
			
			sb.append("\n");
			
			minQueue.clear();
			maxQueue.clear();
			minMap.clear();
			maxMap.clear();
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}