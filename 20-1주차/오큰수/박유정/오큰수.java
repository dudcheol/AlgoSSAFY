package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오큰수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		int[] list=new int[n];
		int[] answer=new int[n];
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		Stack<int[]> stack=new Stack<int[]>();
		
		for (int i = 0; i < n; i++) {
			while(!stack.isEmpty()&&stack.peek()[1]<list[i]) {
				int[] arr=stack.pop();
				answer[arr[0]]=list[i];
			}
			stack.push(new int[] {i,list[i]});
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i:answer) {
			if(i!=0) {
				sb.append(i).append(" ");
			}
			else {
				sb.append(-1).append(" ");
			}
		}
		System.out.println(sb);
	}

}
