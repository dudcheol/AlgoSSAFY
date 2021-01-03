package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 14719 빗물 https://www.acmicpc.net/problem/14719
public class BJ14719_빗물2 {
	static int H, W, cnt;
	static int[] heights;
	static ArrayList<Integer> max_idx = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		heights = new int[W];
		st = new StringTokenizer(br.readLine());
		int max = 0; // 막대 중 가장 긴 길이
		for (int i = 0; i < heights.length; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
			if(max < heights[i]) {
				max = heights[i];
			}
		}
		
		max_idx.add(0);
		for (int i = 0; i < heights.length; i++) {
			if(heights[i] == max){
				max_idx.add(i);
			}
		}
		
		
		for (int k = 1; k < max_idx.size(); k++) {
			
			// 현재보다 더 높은 막대가 있을 때까지 찾고 그 사이에 있는 막대들 위로 빗물 쌓기
			// 처음부터 가장 긴 막대까지
			int cur = max_idx.get(k - 1);
			for (int i = 1; i <= max_idx.get(k); i++) { 
				if(heights[cur] <= heights[i]) { 
					for (int j = cur + 1; j < i; j++) {
						cnt += heights[cur] - heights[j];
					}
					cur = i;
				}
			}
		
			// 다음 max_idx 부터 가장 긴 막대까지
			if(k == max_idx.size() - 1)
				cur = heights.length - 1;
			else
				cur = max_idx.get(k + 1) - 1;
			
			for (int i = cur - 2; i >= max_idx.get(k); i--) {
				if(heights[cur] <= heights[i]) {
					for (int j = cur - 1; j > i; j--) {
						cnt += heights[cur] - heights[j];
					}
					cur = i;
				}
			}
		}
		System.out.println(cnt);
	}
}

// 11 5
// 11 0 0 0 11 max 인덱스를 가지는 막대가 두 개 있을 때 생각해야 함!
