package week16;

import java.io.*;
import java.util.*;

public class _5052_전화번호목록 {

	private static int T;
	private static int N;
	private static String[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			N = Integer.parseInt(br.readLine());
			nums = new String[N];
			for (int i = 0; i < N; i++) {
				nums[i] = br.readLine();
			}

			Arrays.sort(nums);

			String target = nums[0];
			boolean yes = true;
			for (int i = 1; i < N; i++) {
				String cur = nums[i];
				
				if(target.length() == cur.length()) {
					if(target.equals(cur)) {
						yes = false;
						break;
					}
				}
				
				else if(target.length() < cur.length()) {
//					if(cur.contains(target)) { 주의!! 접두어일 경우만 고려
					if(cur.substring(0, target.length()).equals(target)) {
						yes = false;
						break;
					}
				}
				
				target = cur; // 다음 인덱스로 넘어갈 때 target을 현재 번호로 바꿔주기
			}
			
			sb.append(yes?"YES":"NO").append('\n');
		}
		
		System.out.print(sb);
	}
}
