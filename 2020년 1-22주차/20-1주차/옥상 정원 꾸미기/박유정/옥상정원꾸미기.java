package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 옥상정원꾸미기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Stack<int[]> st = new Stack<int[]>();

		int[]  ans= new int[n];

		for (int i = 0; i < n; i++) {
			int building = Integer.parseInt(br.readLine());

			if (!st.isEmpty() && st.peek()[0] <= building) {// 큰게 나왔을경우
				int[] arr=st.pop();
				int prev=arr[1];//인덱스 저장
				
				while(!st.empty()&&st.peek()[0]<=building) {//현재 보다 작은것이 있다면 다 빼줌
					int[] cur=st.pop();
					ans[cur[1]]+=ans[prev]+1;
					prev=cur[1];
				}
				if(!st.isEmpty()) {//비어있지 않을경우 남은것 제일 위에 전것 더해줌 10 3 7
					ans[st.peek()[1]]+=ans[prev]+1;
				}
			}
			st.push(new int[] { building, i });
			while(i==n-1&&st.size()!=1) {//마지막 인덱스라면 다빼주면서 계산
				int[] cur=st.pop();
				ans[st.peek()[1]]+=ans[cur[1]]+1;
			}

		}
		long answer=0;
		for (int i : ans) {
			answer+=i;
			//System.out.println(i);
		}
		System.out.println(answer);
	}

}
