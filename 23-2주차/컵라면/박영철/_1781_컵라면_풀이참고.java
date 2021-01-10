package algoSSAFY.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _1781_컵라면_풀이참고 {
    private static int N;

    private static class Problem{
        int deadline;
        int ramen;

        public Problem(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public String toString() {
            return "deadline=" + deadline +
                    ", ramen=" + ramen;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());

        Problem[] problems = new Problem[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            problems[i] = new Problem(stoi(st.nextToken()), stoi(st.nextToken()));
        }

        // deadline이 빠르고, 라면의 수를 많이 주는 순서로 정렬
        Arrays.sort(problems, (o1,o2)->{
            int ret = Integer.compare(o1.deadline, o2.deadline);
            if (ret==0) ret = -Integer.compare(o1.ramen, o2.ramen);
            return ret;
        });

        // 최소힙. pq에는 라면의 갯수가 담김
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int time = 0;
        for (int i = 0; i < N; i++) { // deadline, 라면 수로 정렬된 배열을 순회한다
            if (problems[i].deadline > time){ // 현재 시간에 풀 수 있는 문제이면 푼다
               pq.offer(problems[i].ramen); // 풀어서 얻은 라면 수 pq에 저장
               time++; // 문제를 풀었으므로 시간 증가
            }
            // 현재 시간이 deadline을 넘겨서 풀 수 없는 문제인데,
            // 나중에 라면을 더 받을 수 있는 문제(problems[i])가 있다면
            // 그전에 풀었던 문제(pq.peek)를 풀지 않고 현재 문제를 푼다.
            else if (problems[i].ramen > pq.peek()){
                pq.poll(); // 그전에 풀었던 문제를 풀지 않은 셈 친다. 최소힙이므로 최소값이 빠져나온다.
                pq.offer(problems[i].ramen); // 현재 문제를 푼 것으로 바꾼다
            }
        }

        int sum=0;
        while (!pq.isEmpty()){
            sum+=pq.poll();
        }

        System.out.println(sum);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
