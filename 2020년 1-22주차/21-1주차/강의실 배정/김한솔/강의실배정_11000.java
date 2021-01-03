/**
 * Backjoon - 11000. 강의실 배정
 * 강의실배정_11000.java
 * @date 2020-12-21
 * @author hansolKim
 **/

package p11000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실배정_11000 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		ArrayList<int[]> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			list.add(new int[] { s, t });
		}
		
		list.sort(new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}
				return Integer.compare(o1[0], o2[0]);
			}
		});

		pq.add(list.get(0)[1]);
		for(int i=1; i<N; i++) {
			if(pq.peek() <= list.get(i)[0]) { // 현재 수업시간의 시작시간이 지금까지의 강의실중 연결가능한 경우 
				pq.poll();
			}
			pq.add(list.get(i)[1]);
		}

		System.out.println(pq.size());
		br.close();
	}
}