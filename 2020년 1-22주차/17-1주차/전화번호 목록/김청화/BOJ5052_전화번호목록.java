package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ5052_전화번호목록 {
	static int t, n;
	static String[] list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		loop: for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			list = new String[n];
			
			for (int i = 0; i < n; i++) {
				list[i] = br.readLine();
			}
			
			Arrays.sort(list);			
			
			for (int i = 1; i < n; i++) {
				if(list[i - 1].length() < list[i].length()) {
					if(list[i - 1].equals(list[i].substring(0, list[i - 1].length()))) {
						System.out.println("NO"); // ㅋㅋㅋㅋ 출력 ㅡㅡ No랑 Yes 말고..
						continue loop;
					}
				}

			}
			System.out.println("YES");
		}
	}
}
