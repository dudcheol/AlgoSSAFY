import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17298_success {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
	
		Stack<Integer> input=new Stack<Integer>();
		Stack<Integer> middle=new Stack<Integer>();
		Stack<Integer> output=new Stack<Integer>();
		
		
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			input.push(Integer.parseInt(stz.nextToken()));
		}
		StringBuilder sb=new StringBuilder();
		
		while(!input.isEmpty()) {
			if(middle.isEmpty()) {
				middle.push(input.pop());
				output.push(-1);
			}
			
			if(input.isEmpty()) {
				break;
			}
			
			if((input.peek()<middle.peek())) {
				output.push(middle.peek());
				middle.push(input.pop());
			}else {
				middle.pop();
			}
		}
		
		while(!output.isEmpty()) {
			sb.append(output.pop()).append(" ");
		}
		
		System.out.println(sb.toString());
	}

}
