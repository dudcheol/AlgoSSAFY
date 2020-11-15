package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 외계인의기타연주 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());

		HashMap<Integer, PriorityQueue<Integer>> hm = new HashMap<Integer, PriorityQueue<Integer>>();

		for (int i = 1; i <= 6; i++) {
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
			hm.put(i, pq);
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int line = Integer.parseInt(st.nextToken());
			int plat = Integer.parseInt(st.nextToken());

			if (hm.get(line).size() == 0) {
				hm.get(line).add(plat);
				count++;
			} else {
				while (!hm.get(line).isEmpty() && hm.get(line).peek() > plat) {
					hm.get(line).poll();
					count++;
				}
				if (hm.get(line).isEmpty() || hm.get(line).peek() != plat) {
					hm.get(line).add(plat);
					count++;
				}
			}
		}
		System.out.println(count);
	}
}