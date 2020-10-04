package com.ssafy.bj.top;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 1493 탑
public class Top {
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
		
		int count = 0;
		int tmp;
		int same = 0;
		boolean flag = false;
		for (int i = 0; i < N; i++) {
//			// 스택에 탑의 높이 입력
//			stack.push(Integer.parseInt(st.nextToken()));
			if(i == 0) {				
				stack.push(Integer.parseInt(st.nextToken()));
				string.append(count + " ");
			}
			else {
				tmp = Integer.parseInt(st.nextToken());
				if(stack.peek() > tmp) {
					string.append(count + " ");
					stack.push(tmp);
					same = count;
					
				} else if(stack.peek() == tmp) {
					stack.pop();
					stack.push(tmp);
					string.append(same + " ");
				}
				else {
					for (int j = 0; j < stack.size(); j++) {
						if(stack.peek() > tmp) {
							string.append(count - j + " ");
							flag = true;
							same = count - j;
							break;
						}
						else {		
							stack.pop();
						}
					}
					stack.push(tmp);
					if(!flag)
						string.append(count - 1 + " ");
					same = count - 1;
				}

			}	
			count++;
		}
		System.out.println(string);

	}
	
}
