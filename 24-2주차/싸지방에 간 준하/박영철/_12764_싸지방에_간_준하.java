package algoSSAFY.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _12764_싸지방에_간_준하 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            // 시작시간, 끝나는시간 순서로 정렬
            arr[i] = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        }

        // 시작 시간 순으로 정렬
        Arrays.sort(arr, (o1, o2)->{
            return Integer.compare(o1[0],o2[0]); // 시작,종료시간이 다른사람과 겹치는 경우는 없음
        });

        // 끝나는 시간이 빠르면서, 컴퓨터 번호가 작은 순서
        PriorityQueue<int[]> using = new PriorityQueue<>((o1,o2)->{
            int ret = Integer.compare(o1[0],o2[0]);
            if (ret==0) ret = Integer.compare(o1[1],o2[1]);
            return ret;
        });

        PriorityQueue<Integer> emptyCom = new PriorityQueue<>();

        int comNo = 1;
        int[] computer = new int[100001];
        for (int i = 0; i < N; i++) {
            int start = arr[i][0];
            int end = arr[i][1];

            if (using.isEmpty()) { // 모든 컴퓨터가 사용되지 않고 있다
                using.offer(new int[]{end, 1}); // 끝나는시간, 사용하는 컴퓨터 번호
                computer[1]++;
                continue;
            }

            // 현재 차례 사람이 시작하려는 시간에 비어있는 자리 구하기
            while(!using.isEmpty() && using.peek()[0] < start){
                emptyCom.offer(using.poll()[1]);
            }

            if (emptyCom.isEmpty()){ // 비어있는 자리가 없으면
                using.offer(new int[]{end,++comNo}); // 기다리지 않고 새로운 컴퓨터 사용
                computer[comNo]++;
            } else { // 비어있는 자리가 있다면 그 중 가장 작은 번호자리 사용
                int no = emptyCom.poll();
                using.offer(new int[]{end,no});
                computer[no]++;
            }

            /**
             * 실수
             * 새로운 사람은 이전 사람이 이용하던 좌석이 아니라
             * "비어있는 좌석 중에서 번호가 가장 작은 좌석"에 앉아야 한다. 가령,
             * 10 100
             * 20 50
             * 110 120
             * 이라면, 3번째 사람은 2번자리에 앉는게 아니라, 1번자리에 앉아야 한다.
             */
//            int[] peek = pq.peek();
//            int useEnd = peek[0];
//            int useCom = peek[1];
//            if (start < useEnd){
//                pq.offer(new int[]{end,++comNo}); // 기다리지 않고 새로운 컴퓨터 사용
//                computer[comNo]++;
//            } else {
//                pq.poll(); // 현재 차례 사용자는 사용이 끝난 컴퓨터 사용하면 됨 => 사용했던 사람 pq에서 제거
//                pq.offer(new int[]{end,useCom}); // 현재 차례 사용자 추가
//                computer[useCom]++;
//            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(comNo).append('\n');
        for (int i = 1; i <= comNo; i++) {
            if (computer[i]==0)break;
            sb.append(computer[i]).append(' ');
        }
        System.out.print(sb);
    }
}
