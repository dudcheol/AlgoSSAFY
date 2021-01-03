package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B9935_2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		//input
		String input=br.readLine();
		String boom=br.readLine();
		
		//solve
		//아이디어 :
		//끝 글자가 같으면, 글자 수 만큼 bucket에 담는다. bucket에 있는 글자를  sb에 넣고 sb.toString과 boom을 비교해서 같으면 그대로 진행 아니면 stack에 다시 넣어준다.
		Stack<Character> stack= new Stack<Character>();
		
		int boom_len=boom.length();
		

		StringBuilder sb=new StringBuilder();
		if(input.length()<boom_len) {
			System.out.println(input);
			return;
		}
		//이론상 stack에는 출력해야하는 한줄만 남는다. 
		for(int i=0;i<input.length();i++) {
			stack.push(input.charAt(i));
			
			if(input.charAt(i)==boom.charAt(boom_len-1) && stack.size()>=boom_len) {
				Stack<Character> bucket=new Stack<Character>();
				
				
				for(int j=0;j<boom_len;j++) {
					bucket.push(stack.pop());
				}
				
				while(!bucket.isEmpty()) {
					sb.append(bucket.pop());
				}
				
				if(!(sb.toString().equals(boom))) {
					for(int k=0;k<boom_len;k++) {
						stack.push(sb.charAt(k));
					}
				}
				//초기화
				sb.setLength(0);
			}
			
		}
		
		Stack<Character> output=new Stack<Character>();
		
		//output
		while(!stack.isEmpty()) {
			output.push(stack.pop());
		}
		
		while(!output.isEmpty()) {
			sb.append(output.pop());
		}
		
		if(sb.length()==0) {
			System.out.println("FRULA");
		}else {
			System.out.println(sb.toString());
		}

	}

}
