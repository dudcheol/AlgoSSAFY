package algoSSAFY.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _1781_컵라면 {
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

        ArrayList<Problem> problems = new ArrayList<>();
        int[] deadcnt = new int[200001];
        int maxtime = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            problems.add(new Problem(stoi(st.nextToken()), stoi(st.nextToken())));
            deadcnt[problems.get(i).deadline]++;
            maxtime = Math.max(maxtime, problems.get(i).deadline);
        }

        Collections.sort(problems, (o1,o2)->{
            int ret = Integer.compare(o1.deadline, o2.deadline);
            if (ret==0) ret = -Integer.compare(o1.ramen, o2.ramen);
            return ret;
        });

        PriorityQueue<Problem> pq = new PriorityQueue<Problem>((o1,o2)->{
            return -Integer.compare(o1.ramen, o2.ramen);
        });

        // deadcnt가 1보다 크면 pq에 포함시킴
        HashMap<Integer, Problem> map = new HashMap<>();
        for (int i = 0; i < problems.size(); i++) {
            if(deadcnt[problems.get(i).deadline]>1){
                deadcnt[problems.get(i).deadline]--;
                pq.offer(problems.get(i));
            } else {
                map.put(problems.get(i).deadline, problems.get(i));
            }
        }

        int time = 1;
        int sum = 0;
        while (time <= maxtime){
            if (map.get(time)!=null) pq.offer(map.get(time));
            while (!pq.isEmpty() && pq.peek().deadline < time){
                pq.poll();
            }
            time++;
            if (pq.isEmpty()) continue;
            sum += pq.poll().ramen;
        }

        System.out.println(sum);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
