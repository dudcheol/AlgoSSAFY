package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이중우선순위큐 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < test; i++) {
			int input = Integer.parseInt(br.readLine());

			PriorityQueue<Integer> min = new PriorityQueue<>();
			PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			int size = 0;

			for (int j = 0; j < input; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				String order = st.nextToken();
				int num = Integer.parseInt(st.nextToken());

				if (order.equals("I")) {
					if (hm.containsKey(num)) {
						hm.put(num, hm.get(num) + 1);
					} else {
						hm.put(num, 1);
					}
					min.add(num);
					max.add(num);
					size++;
				} else if (order.equals("D")) {
					if (size > 0) {
						if (num > 0) {// 최댓값 삭제
							while (!max.isEmpty()) {
								int n = max.poll();
								int remain = hm.get(n);
								if (remain > 0) {
									hm.put(n, remain - 1);
									break;
								}
							}
						} else {// 최솟값 삭제
							while (!min.isEmpty()) {
								int n = min.poll();
								int remain = hm.get(n);
								if (remain > 0) {
									hm.put(n, remain - 1);
									break;
								}
							}
						}
						size--;
					}
				}
			}
			if (size == 0)
				sb.append("EMPTY").append("\n");
			else {
				int answer1 = 0, answer2 = 0;
				while (!max.isEmpty()) {
					int n = max.poll();
					if (hm.get(n) > 0) {
						answer1 = n;
						answer2 = n;
						break;
					}
				}
				while (!min.isEmpty()) {
					int n = min.poll();
					if (hm.get(n) > 0) {
						answer2 = n;
						break;
					}
				}
				sb.append(answer1).append(" ").append(answer2).append("\n");
			}
		}
		System.out.println(sb);
	}

}
