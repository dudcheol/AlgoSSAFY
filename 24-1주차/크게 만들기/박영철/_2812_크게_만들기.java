package algoSSAFY.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _2812_크게_만들기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String num = br.readLine();

        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < N; i++) {
            int cur = num.charAt(i)-'0';
            while (!s.isEmpty() && K > 0 && cur > s.peek()){
                s.pop();
                K--;
            }
            s.push(cur);
        }

        // K가 아직 남았다면 나머지 더 pop해준다
        while (K-->0){
            s.pop();
        }

        StringBuilder sb = new StringBuilder();
        while(!s.isEmpty()){
            sb.append(s.pop());
        }

        System.out.println(sb.reverse());
    }
}

/*
6 2
391123
 */