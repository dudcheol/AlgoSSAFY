package algoSSAFY.week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _1715_카드정렬하기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->{
            return Integer.compare(o1,o2);
        });

        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (!pq.isEmpty() && pq.size()!=1){
            int sum = pq.poll() + pq.poll();
            pq.offer(sum);
            answer += sum;
        }

        System.out.println(answer);
    }
}
