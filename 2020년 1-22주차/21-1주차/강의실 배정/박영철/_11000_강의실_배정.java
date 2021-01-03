package algoSSAFY.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
pq를 2개 쓸 생각을 하지 못함.
처음에 정렬시키고 다음에 pq에 삽입해가며 문제를 풀어나가는 방법도 생각할 것
 */
public class _11000_강의실_배정 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            list.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        // 강의시간표 리스트를 시작순서로 정렬. 같을 경우 종료순서로 정렬
        list.sort((o1,o2)->{
            int res = Integer.compare(o1[0], o2[0]);
            if (res==0) res = Integer.compare(o1[1], o2[1]);
            return res;
        });

        // pq에 삽입되는 시간표는 끝나는 순서로 정렬되도록. pq의 요소 하나하나가 강의실을 의미
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            return Integer.compare(o1[1], o2[1]);
        });

        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);

            if (pq.isEmpty()){
                pq.offer(cur);
                continue;
            }

            int[] peek = pq.peek(); // 우선순위에 따라 큐의 peek이 달라진다.
            if (cur[0] >= peek[1]){ // peek의 강의실 사용 가능하면 현재 수업으로 갱신
                pq.poll();
                pq.offer(cur);
            } else { // peek 강의실 사용 불가능하면 새로운 강의실 사용
                pq.offer(cur);
            }
        }

        System.out.println(pq.size());
    }
}