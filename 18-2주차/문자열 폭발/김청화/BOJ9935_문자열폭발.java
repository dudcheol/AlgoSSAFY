package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9935_문자열폭발 {
	static String string, bomb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		string = br.readLine();
		bomb = br.readLine();
		
		char[] result = new char[string.length()];
		int idx = 0;
		
		for (int i = 0; i < string.length(); i++) {
			result[idx++] = string.charAt(i);
			
			if(result[idx - 1] == bomb.charAt(bomb.length() - 1)) {
				
				if(idx < bomb.length()) continue;
				
				boolean flag = true;
				for (int j = 0; j < bomb.length(); j++) {
					if(result[idx - j - 1] != bomb.charAt(bomb.length() - j - 1)) { // 뒤에서부터 검사
						flag = false;
						break;
					}
				}					
				if(flag)
					idx -= bomb.length();
			}
		}
		
		if(idx == 0)
			sb.append("FRULA");
		else {
			for (int i = 0; i < idx; i++) {
				sb.append(result[i]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}

}
