package com.kakao.y2019;

import java.util.*;

public class 실패율 {
	public int[] solution(int N, int[] stages) {
		int[] answer;

		Arrays.sort(stages);
		int sum = 0;
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();// 스테이지,도달한 사람수

		for (int i = 1; i <= N + 1; i++) {
			hm.put(i, 0);// 도달한 사람수는 0으로 초기화
		}

		for (int i = stages.length - 1; i >= 0; i--) {// 마지막스테이지부터 계산
			int stage = stages[i];
			sum++;

			for (int j = i - 1; j >= 0; j--) {
				if (stage == stages[j]) {
					sum++;
					i = j;
				} else {
					i = j + 1;
					break;
				}
			}
			System.out.println(stage + " " + sum);
			hm.put(stage, sum);
		}

		answer = new int[N];
		double prev = hm.get(N + 1);// 다 깬 사람수
		double fail = 0;
		double[][] arr = new double[N][2];

		for (int i = N; i >= 1; i--) {
			double result = hm.get(i);

			if (result == 0) {// 도달한사람이 없음,도달한 사람 모두 통과
				fail = 0;
				result = prev;
			} else if (result != 0) {
				fail = (result - prev) / result;// 실패율 계산
			} 
			arr[i - 1][0] = i;//스테이지 저장
			arr[i - 1][1] = fail;//스테이지 실패율 저장
			prev = result;//깬사람 저장(이전 스테이지에서 깨지못한 사람 수 계산)
		}

		Arrays.sort(arr, new Comparator<double[]>() {// 실패율 내림차순으로 sort

			@Override
			public int compare(double[] o1, double[] o2) {
				if (o2[1] == o1[1])// 실패율같은 경우 스테이지순
					return (o1[0] - o2[0]) >= 0 ? 1 : -1;
				return (o2[1] - o1[1]) >= 0 ? 1 : -1;
			}

		});
		for (int i = 0; i < N; i++) {
			answer[i] = (int) arr[i][0];
		}
		return answer;
	}
}
