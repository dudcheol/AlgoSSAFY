package algoSSAFY.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _17471_게리맨더링 {
    private static int N, pcnt[], visited[], selected[], edge[][], min;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        pcnt = new int[N+1];
        for (int i = 1; i <= N; i++) {
            pcnt[i] = Integer.parseInt(st.nextToken());
        }
        edge = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int len = Integer.parseInt(st.nextToken());
            for (int j = 0; j < len; j++) {
                int to = Integer.parseInt(st.nextToken());
                edge[i][to] = 1;
                edge[to][i] = 1;
            }
        }
        selected = new int[N+1];
        visited = new int[N+1];
        min = Integer.MAX_VALUE;

        subset(1);
        System.out.println(min==Integer.MAX_VALUE?-1:min);
    }

    private static void subset(int k) {
        if (k == N+1) {
            Arrays.fill(visited, 0); // 초기화
            check();
            return;
        }
        selected[k]=1;
        subset(k+1);
        selected[k]=2;
        subset(k+1);
    }

    // selected true = RED, false = BLUE
    private static void check() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]!=0) continue;
            if (selected[i]==1){ // red
                dfs(i, 1);
            } else { // blue
                dfs(i, 2);
            }
            cnt++;
        }

        if (cnt>2) return;

        int rcnt=0,bcnt=0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]==0) return;
            else if (visited[i]==1) rcnt+=pcnt[i];
            else bcnt+=pcnt[i];
        }
        min = Math.min(min, Math.abs(rcnt-bcnt));
    }

    private static void dfs(int k, int color) {
        visited[k] = color;
        for (int i = 1; i <= N; i++) {
            if (visited[i]==0 && edge[k][i]==1 && selected[i]==color){
                dfs(i, color);
            }
        }
    }
}
