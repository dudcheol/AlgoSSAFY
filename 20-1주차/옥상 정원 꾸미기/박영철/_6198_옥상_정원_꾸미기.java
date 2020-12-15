package algoSSAFY.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _6198_옥상_정원_꾸미기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> st = new Stack<Integer>();
        // long으로 선언해야 하는 이유? 80000,7999,7998,...,3,2,1 순서로 8만개 빌딩이 있다면 0+1+2+...+79997+79998+79999가 된다.
        // 1부터 n까지의 합은 n(n+2)/2 이므로 integer범위를 벗어난다.
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());

            while (!st.isEmpty() && cur >= st.peek()) {
                st.pop();
            }

            st.push(cur);

            cnt += st.size()-1;
        }

        System.out.println(cnt);
    }
}
