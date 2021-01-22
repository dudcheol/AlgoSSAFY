package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 멀티탭스케줄링 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int max = 1000;

		int[] arr = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<int[]> li = new ArrayList<int[]>();
		for (int i = 0; i < K; i++) {
			boolean flag = false;
			for (int j = i + 1; j < K; j++) {
				if (arr[i] == arr[j]) {
					li.add(new int[] { arr[i], j - i });// 얼마뒤에 사용될건지 저장
					flag = true;
					break;
				}
			}
			if (!flag) {
				li.add(new int[] { arr[i], max });// 앞으로 사용안되면 max값
			}
		}
		
		for (int[] d:li) {
			System.out.println(Arrays.toString(d));
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int count = 0;

		for (int[] pro : li) {
			for (int k : hm.keySet()) {// 시간줄여주기
				hm.put(k, hm.get(k) - 1);
			}
			if (hm.size() < N) {// 멀티탭비어있음
				hm.put(pro[0], pro[1]);
			} else {// 멀티탭 꽉참
				if (hm.containsKey(pro[0])) {// 이미 멀티탭에 꽂혀있는경우
					hm.put(pro[0], pro[1]);
					continue;
				}
				pq.clear();
				for (int k : hm.keySet()) {
					pq.add(new int[] { k, hm.get(k) });
				}
				int[] re = pq.poll();
				hm.remove(re[0]);
				hm.put(pro[0], pro[1]);
				count++;
			}
		}
		System.out.println(count);

	}
}
