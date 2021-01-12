package algoSSAFY.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _7662_이중_우선순위_큐 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<int[]> asc = new PriorityQueue<>((o1,o2)->{
            int ret = Integer.compare(o1[0],o2[0]);
            if (ret==0) ret = Integer.compare(o1[1],o2[1]);
            return ret;
        });
        PriorityQueue<int[]> desc = new PriorityQueue<>((o1,o2)->{
            int ret = -Integer.compare(o1[0],o2[0]);
            if (ret==0) ret = -Integer.compare(o1[1],o2[1]);
            return ret;
        });

        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < TC; test_case++) {
            int T = Integer.parseInt(br.readLine());
            int order = 0;
            boolean[] visited = new boolean[1000000];
            for (int input = 0; input < T; input++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                char cmd = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());
                if (cmd == 'I'){
                    asc.offer(new int[]{num, order});
                    desc.offer(new int[]{num, order});
                } else {
                    if (asc.isEmpty() || desc.isEmpty())
                        continue;

                    if (num==-1){
                        while (!asc.isEmpty() && visited[asc.peek()[1]]) asc.poll();
                        if (!asc.isEmpty()) visited[asc.poll()[1]] = true;
                    }else{
                        while (!desc.isEmpty() && visited[desc.peek()[1]]) desc.poll();
                        if (!desc.isEmpty()) visited[desc.poll()[1]] = true;
                    }
                }
                order++;
            }

            // 방문처리된애들 모두 없애기
            while (!asc.isEmpty() && visited[asc.peek()[1]]) asc.poll();
            while (!desc.isEmpty() && visited[desc.peek()[1]]) desc.poll();

            if (asc.isEmpty() || desc.isEmpty()){
                sb.append("EMPTY").append('\n');
            } else {
                sb.append(desc.poll()[0]).append(' ').append(asc.poll()[0]).append('\n');
            }
            asc.clear();
            desc.clear();
        }
        System.out.print(sb);
    }
}
