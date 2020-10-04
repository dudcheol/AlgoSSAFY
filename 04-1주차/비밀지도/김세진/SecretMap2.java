package de;

import java.util.Arrays;

public class SecretMap2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(
				Arrays.toString(solution(5, new int[] { 9, 20, 28, 18, 11 }, new int[] { 30, 1, 21, 17, 28 })));
		System.out.println(Arrays
				.toString(solution(6, new int[] { 46, 33, 33, 22, 31, 50 }, new int[] { 27, 56, 19, 14, 14, 10 })));
//		System.out.println(Arrays.toString(
//				solution(20, new int[] { 9, 20, 28, 18, 11, 9, 20, 28, 18, 11, 9, 20, 28, 18, 11, 9, 20, 28, 18, 11 },
//						new int[] { 30, 1, 21, 17, 28, 30, 1, 21, 17, 28, 9, 20, 28, 18, 11, 9, 20, 28, 18, 11 })));

	}

	static public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];

		for (int i = 0; i < n; i++) {
			// 2진수 변환
			String k = Integer.toBinaryString(arr1[i]);
			String k2 = Integer.toBinaryString(arr2[i]);

			if (k.length() != n) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < (n - k.length()); j++) {
					sb.append("0");
				}
				sb.append(k);
				k = sb.toString();
			}
			if (k2.length() != n) {
				StringBuilder sb2 = new StringBuilder();
				for (int j = 0; j < (n - k2.length()); j++) {
					sb2.append("0");
				}
				sb2.append(k2);
				k2 = sb2.toString();
			}
			StringBuilder sb3 = new StringBuilder();
			for (int z = 0; z < n; z++) {
				if (k.charAt(z) == '1' || k2.charAt(z) == '1') {
					sb3.append('#');
				} else {
					sb3.append(' ');
				}
			}
			answer[i] = sb3.toString();

		}
		return answer;
	}
}
