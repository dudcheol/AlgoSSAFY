package algoSSAFY.week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _10216_Count_Circle_Groups {
    private static int N,node[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            N = Integer.parseInt(br.readLine());
            node = new int[N+1];
            for (int i = 1; i <= N; i++) node[i]=i;

            int[][] pos = new int[N+1][];
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());
                pos[i]=new int[]{x,y,R};
            }

            for (int i = 1; i <= N; i++) {
                int cx=pos[i][0];
                int cy=pos[i][1];
                int cr=pos[i][2];
                for (int j = i+1; j <= N; j++) {
                    int tx=pos[j][0];
                    int ty=pos[j][1];
                    int tr=pos[j][2];
                    if(cr+tr >= Math.sqrt(Math.pow(cx-tx,2)+Math.pow(cy-ty,2))){
                        union(i,j);
                    }
                }
            }

            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                set.add(find(i));
            }

            sb.append(set.size()).append('\n');
        }
        System.out.print(sb);
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        node[bRoot] = aRoot;
    }

    private static int find(int a) {
        if (a==node[a]) return a;
        return node[a] = find(node[a]);
    }
}
