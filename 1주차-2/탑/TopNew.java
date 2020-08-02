package com.ssafy.bj.top;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 1493 탑
public class TopNew {
	static StringBuilder string = new StringBuilder();
	// BufferedReader는 Exception이 따로 처리를 해줘야 함 -> throws를 해줘야 함
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// StringTokenizer 객체 선언
		StringTokenizer st = null;
		
		// String Line이므로 Integer.parseInt를 이용하여 형변환해야 함.
		int N = Integer.parseInt(br.readLine());
		
		// String Line일 때 StringTokenizer 이용
		st = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> index = new Stack<>();
	
		
		int count = 1;
		int tmp;
		for (int i = 0; i < N; i++) {
			if(i == 0) {				
				stack.push(Integer.parseInt(st.nextToken()));
				index.push(count);
				string.append("0 ");
			}
			else {
				tmp = Integer.parseInt(st.nextToken());
				if(stack.peek() > tmp) {
					string.append(index.peek() + " ");
					stack.push(tmp);
					index.push(count);			
				} 
				else {
					
					if(stack.peek() == tmp) {
						stack.pop();
						index.pop();
						stack.push(tmp);
						index.push(count);	
					}	
					for (int j = 0; j <= stack.size(); j++) {
						if(stack.peek() > tmp) {
							string.append(index.peek() + " ");
							break;
						}
						else {		
							stack.pop();
							index.pop();
							if(stack.isEmpty())
								string.append("0 ");
						}
					}
					stack.push(tmp);
					index.push(count);
				}

			}	
			count++;
		}
		System.out.println(string);

	}
	
}
