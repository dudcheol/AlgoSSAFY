package algoSSAFY.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _19640_화장실의_규칙 {
    private static int N,M,K;

    private static class Person{
        int d;
        int h;
        int lineNo;
        boolean deka;

        public Person(int d, int h, int lineNo, boolean deka) {
            this.d = d;
            this.h = h;
            this.lineNo = lineNo;
            this.deka = deka;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Person> pq = new PriorityQueue<Person>((o1,o2)->{
            int res = -Integer.compare(o1.d, o2.d);
            if (res==0) res = -Integer.compare(o1.h, o2.h);
            if (res==0) res = Integer.compare(o1.lineNo, o2.lineNo);
            return res;
        });

        Queue<Person>[] q = new Queue[M];
        for (int i = 0; i < M; i++) {
            q[i] = new LinkedList<>();
        }

        int lineNo = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            boolean deka = i==K?true:false;
            q[i%M].offer(new Person(d,h,i%M,deka));
        }


        // 각 줄의 선두들을 pq에 담는다
        for (int i = 0; i < M; i++) {
            if (q[i].isEmpty()) continue;
            pq.offer(q[i].peek());
        }

        /**
         * 실수:
         * pq의 성질을 최대한 이용해야 한다
         * 반복문을 돌 때마다 pq를 초기화해서 새롭게 pq를 채워가면서 선두들의 우선순위를 정하면 시간초과가 난다.
         * pq에서 poll했을 때, poll한 사람이 섰던 줄의 선두만 바뀐다는 점을 이용해서 풀면 된다.
         */
        // 데카를 찾을때까지 무한 반복
        int answer = 0;
        while (true){
            Person first = pq.poll(); // pq의 우선순위가 가장 높은 사람이 먼저 화장실에 들어간다
            if (first.deka) break; // 그 사람이 데카라면 반복문을 종료한다
            q[first.lineNo].poll(); // q에서 줄 서 있던 사람이 화장실에 간 것이므로 poll 한다

            // pq는 각 줄의 선두들만 담겨야 하므로 방금 화장실에 들어간 사람이 있던 줄의 새로운 선두를 pq에 넣는다
            if (!q[first.lineNo].isEmpty())
                pq.offer(q[first.lineNo].peek());

            answer++;
        }

        System.out.println(answer);
    }
}
