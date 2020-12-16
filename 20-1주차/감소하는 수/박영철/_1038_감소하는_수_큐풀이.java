package algoSSAFY.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
참고
- 완전탐색: https://roseline124.github.io/algorithm/2019/04/16/Algorithm-baekjoon-1038.html
- 큐: https://dundung.tistory.com/118
 */

public class _1038_감소하는_수_큐풀이 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N <= 10){ // 10 이전은 N과 동일함
            System.out.println(N);
            return;
        }

        Queue<Long> q = new LinkedList<Long>();
        for (int i = 1; i <= 9; i++) { // 1~9까지 큐에 삽입
            q.offer((long)i);
        }

        int cnt = 9;
        long offerNum = 0;
        while(!q.isEmpty()){
            long num = q.poll();
            for (int i = 0; i < num%10; i++) {
                q.offer(offerNum = 10*num + i);
                if(++cnt == N){
                    System.out.println(offerNum);
                    return;
                }
                if (offerNum >= 9876543210L){
                    System.out.println(-1);
                    return;
                }
            }
        }
    }
}
