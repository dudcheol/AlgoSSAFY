package algoSSAFY.week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _2014_소수의_곱 {
    private static int K,N,nums[],selected[];
    private static LinkedList<Integer> pq;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        nums = new int[K];
        pq = new LinkedList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 중복조합
        int end = 1;
        while(pq.size() <= K*N){
            selected = new int[end];
            duplComb(0, 0, end++);
        }

        Collections.sort(pq);

//        int i = 0;
//        while(!pq.isEmpty()){
//            System.out.println(i++ +"번째 : "+pq.poll());
//        }

        System.out.println(pq.get(N-1));
    }

    private static void duplComb(int k, int idx, int end) {
        if (k==end) {
            int mul = 1;
            for (int i = 0; i < end; i++)
                mul *= selected[i];
            pq.offer(mul);
            return;
        }
        for (int i = idx; i < K; i++) {
            selected[k] = nums[i];
            duplComb(k+1, i, end);
        }
    }
}
