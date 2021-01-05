package vacation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class  압축{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split("");
		Stack<String> st = new Stack<String>();
		int answer=0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(")")) {
				int num = 0;
				
				while (!st.peek().equals("(")) {
					String s=st.pop();
					if(s.charAt(0)=='.') {
						num+=Integer.parseInt(s.substring(1,s.length()));
					}else {
						num++;
					}
				}
				
				st.pop();// "(" 제거
				
				int k = Integer.parseInt(st.pop());//k
				num*=k;//k만큼 반복
				st.push("."+String.valueOf(num));
			} else {
				st.push(arr[i]);
			}
		}
		while (!st.isEmpty()) {
			String s=st.pop();
			if(s.charAt(0)=='.') {
				answer+=Integer.parseInt(s.substring(1,s.length()));
			}else {
				answer++;
			}
		}
		System.out.println(answer);
	}
}