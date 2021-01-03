package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9935 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		//input
		String input=br.readLine();
		String boom=br.readLine();
		
		//solve
		//스택 문제? 큐 문제? 제한시간 2초 = 자료구조써서 한번만.
		int input_len=input.length();
		
		String answer=input.replace(boom, "");
		
		int answer_len=answer.length();
		
		while(answer_len!=input_len) {
			input=answer;
			input_len=answer_len;
			answer=input.replace(boom, "");
			answer_len=answer.length();
		}
		
		
		//output
		
		if(answer_len==0) {
			System.out.println("FRULA");
		}else {
			System.out.println(answer);
		}

	}

}
