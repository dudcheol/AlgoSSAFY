package com.kakao.y2019;

import java.util.*;

public class 무지의먹방라이브 {
	public int solution(int[] food_times, long k) {
		int answer = -1;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i : food_times) {
			q.add(i);
		}
		long prev_min = 0;

		while (!q.isEmpty()) {
			long cur = q.peek();
			long cur_min = cur - prev_min;// 최솟값

			if (cur_min * q.size() < k) {// 최솟값 만큼 회전
				prev_min = cur;
				k -= cur_min * q.size();
			} else {// 최솟값이 더 작을경우

				while (k - q.size() >= 0) {// 회전할 수 있는 경우 회전
					prev_min++;
					k -= q.size();
				}

				answer = (int) k;// 남은음식 중에서 k번째
				break;
			}

			while (!q.isEmpty() && cur == q.peek()) {// 최솟값이 여러개면 빼줌
				q.poll();
			}
		}
		int count = -1;// k는 0부터 시작함

		if (answer != -1) {

			for (int i = 0; i < food_times.length; i++) {

				if (food_times[i] - prev_min > 0) {
					count++;

					if (count == answer)
						return i + 1;// 순번은 1부터시작
				}
			}
		}
		return -1;// 큐가 비어서 빠져나온경우
	}

	public static void main(String[] args) {
		무지의먹방라이브 m = new 무지의먹방라이브();
		System.out.println(m.solution(new int[] { 1, 1, 1 }, 5));// -1인경우
	}
}
