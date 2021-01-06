package data_structure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B1662 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String S=br.readLine();
		StringBuilder sb=new StringBuilder();
		Stack<Character> stack=new Stack<Character>();
		Stack<Integer> len=new Stack<Integer>();
		int count=0;
		
		for(int i=0;i<S.length();i++) {
			
			if(S.charAt(i)==')') {
				int l=0;
				while(stack.peek()!='(') {
					stack.pop();
					l+=len.pop();
				}
				
				stack.pop();
				
				
				int k=stack.pop()-'0';
				
				len.pop();
				stack.push('0');
				len.push(k*l);
				
				
				
			}else if(S.charAt(i)=='('){
				stack.add(S.charAt(i));
			}else {
				stack.add(S.charAt(i));
				len.add(1);
			}
			
		}
		int solve=0;
		
		while(!len.isEmpty()) {
			solve+=len.pop();
		}
		System.out.println(solve);

	}

}
