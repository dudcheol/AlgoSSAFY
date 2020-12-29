package algoSSAFY.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1826_연료_채우기_우선순위큐 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] infos = new int[N][];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            infos[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int L = Integer.parseInt(st.nextToken()); // 마을까지 거리
        int P = Integer.parseInt(st.nextToken()); // 처음 트럭에 있던 연료 양

        // 성경이와 거리가 가까운 순서로 정렬
        Arrays.sort(infos, (o1, o2) -> {
           return Integer.compare(o1[0], o2[0]);
        });

        // 연료를 넣을 우선순위 큐. 연료를 많이 채울 수 있는 곳부터 가기위해 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 성경이가 가지고있는 연료로 갈 수 있는 범위, 방문한 주유소 수
        int mpos = P, cnt = 0, idx = 0;
        while (mpos < L) {
            // 성경이가 현재 위치에서 가지고 있는 연료로 갈 수 있는 곳에서 얻을 수 있는 연료들을 모두 pq에 담는다
            while(idx<N && infos[idx][0] <= mpos) {
                pq.offer(infos[idx][1]);
                idx++;
            }
            // 성경이의 현재 연료로는 갈 수 없는 주유소가 나오면
            // pq에 있는 주유소들 중 가장 많은 연료를 충전할 수 있는 곳을 간다 (pq의 top)
            if (pq.isEmpty()) break; // pq가 비어있으면 갈 수 있는 범위 중 연료를 충전할 수 있는 주유소가 없다는 것
            mpos += pq.poll(); // 주유소를 들러 연료를 충전했으므로 갈 수 있는 범위가 늘어난다
            cnt++;
        }
        if (mpos >= L){
            System.out.println(cnt);
            return;
        }
        System.out.println(-1);
    }
}
