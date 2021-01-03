package algoSSAFY.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _17299_오등큰수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        // int형 배열 선언 범위는 100만까지는 괜찮다.
        // 512mb -> 512,000kb -> 512,000,000byte
        // int - 4byte
        // int[]은 128,000,000개 생성 가능(?) 맞나??? 질문해보기
        int[] cnt = new int[1000001];
//        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
//            if (map.containsKey(arr[i])) map.put(arr[i], map.get(arr[i])+1);
//            else map.put(arr[i], 1);
            cnt[arr[i]]++;
        }

        Stack<Integer> st = new Stack<Integer>();
        int[] answer = new int[N];
        for (int i = N-1; i >= 0; i--) {
            int cur = arr[i];
            if (st.isEmpty()){
                answer[i] = -1;
                st.push(cur);
            } else {
                int val = cnt[cur];
                while(!st.isEmpty() && (cnt[st.peek()] <= val)) st.pop();
                if (st.isEmpty()) answer[i] = -1;
                else answer[i] = st.peek();
                st.push(cur);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(' ');
        }
        System.out.print(sb);
    }
}
