package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// swea6808 규영이와 인영이의 카드게임 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0&categoryId=AWgv9va6HnkDFAW0&categoryType=CODE&&&
public class SW6808_규영이와인영이의카드게임 {
	static int T;
	static int[] GY = new int[9];
	static int[] IY = new int[9];
	static int[] IYs = new int[9];
	static int wincnt, losecnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder string = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean[] checked = new boolean[19];
			for (int i = 0; i < 9; i++) {
				GY[i] = Integer.parseInt(st.nextToken());
				checked[GY[i]] = true;
			}
			int idx = 0;
			for (int i = 1; i < checked.length; i++) {
				if(!checked[i])
					IY[idx++] = i;
			}
			
			boolean[] visited = new boolean[9];
			wincnt = 0; 
			losecnt = 0;
			perm(0, visited);
			string.append("#" + tc + " " + wincnt + " " + losecnt + "\n");
		}
		System.out.println(string);
	}
	
	static void perm(int k, boolean[] v) {
		if(k == 9) {
//			System.out.println(Arrays.toString(IYs));
			int sumGY = 0, sumIY = 0;
			for (int i = 0; i < GY.length; i++) {
				if(GY[i] > IYs[i])
					sumGY += GY[i] + IYs[i];
				else if(IYs[i] > GY[i])
					sumIY += GY[i] + IYs[i];
			}
			if(sumGY > sumIY)
				wincnt++;
			else if(sumIY > sumGY)
				losecnt++;
			return;
		}
		
		for (int i = 0; i < v.length; i++) {
			if(!v[i]) {
				v[i] = true;
				IYs[k] = IY[i];
				perm(k + 1, v);
				v[i] = false;
			}
		}
	}
}
