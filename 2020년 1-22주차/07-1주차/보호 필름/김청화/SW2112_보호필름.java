package swea;
// swea2112 보호 필름 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW2112_보호필름 {
	static int T, D, W, K; // D: 두께, W: 가로크기, K: 합격기준
	static int[] sel;
	static int[][] film, tmp;
	static boolean flag;
	
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		T = Integer.parseInt(br.readLine());
		
//		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 주입하지 않아도 될 경우
			if(check(film))
				System.out.println(0);
			
			else {
				tmp = film.clone();
				
				for (int i = 1; i <= D; i++) {
					int[][] tmp = film.clone();
					sel = new int[i];
					comb( 0, 0, i);
				}
				
				System.out.println(ans);
			}
	}
	
	// num: 주입 횟수
	static void comb(int num, int depth, int end) {
		int min = Integer.MAX_VALUE;
		
//		// 주입 횟수가 이전에 검사한 주입 횟수보다 많으면 더 이상 검사할 필요 없음
		if(num == end) {
			System.out.println(Arrays.deepToString(tmp));
//			System.out.println(Arrays.toString(sel));
			if(check(tmp)) {
				if(min > num)
					min = num;
				flag = true;
			}
			return;
		}
		
		for (int i = depth; i < D; ++i) {
//			sel[num] = i; // 뽑힌 셀 num 저장
			int[] temp = film[i];
			
			// 0과 1을 차례대로 주입해 봄
			for (int j = 0; j < 2; j++) {
				Arrays.fill(tmp[i], j);
				comb(num + 1, i + 1, end);
				if(flag) return;
			}
			tmp[i] = temp;
		}
	}	
	
	// 연속된 특성이 k개 이상인지 검사
	static boolean check(int[][] film) {
		boolean[] checked = new boolean[W];
		for (int j = 0; j < W; j++) {
			int num = film[0][j];
			int cnt = 1;
			for (int i = 1; i < D; i++) {		
				if(num == film[i][j])
					cnt++;
				else
					cnt = 1;
				
				if(cnt >= K) {
					checked[j] = true;
					break;
				}		
				num = film[i][j];
			}
		}
		
		for (int i = 0; i < checked.length; i++) {
			if(!checked[i])
				return false;
		}
		return true;
	}

}
