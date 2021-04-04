package algoSSAFY.week32;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 디저트_카페 {
    private static int N,map[][],max,cantmove[]={3,2,1,0};
    private static int[] dy = {-1,-1,1,1}; // 대각이동: 좌상,우상,좌하,우하
    private static int[] dx = {-1,1,-1,1}; // 대각이동: 좌상,우상,좌하,우하
    private static boolean[] eat, dirs;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            N=Integer.parseInt(br.readLine());
            map=new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < N; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            max=0;
            // 모든 지점을 시작점으로 하여 dfs => 개선의 여지가 있음
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    eat=new boolean[101]; // 디저트종류 1~100
                    dirs=new boolean[4];
                    dfs(i,j,i,j,0,1);
                }
            }
            sb.append('#').append(test_case).append(' ').append(max==0?-1:max).append('\n');
        }
        System.out.print(sb);
    }

    private static void dfs(int sy, int sx, int y, int x, int preDir, int cnt) {
        eat[map[y][x]]=true;
        for (int d = 0; d < 4; d++) {
            if(cantmove[preDir]==d) continue; // 이동할 수 없는 방향

            int ny=y+dy[d];
            int nx=x+dx[d];

            if (ny<0||nx<0||ny>=N||nx>=N) continue; // 범위를 벗어남

            if (ny==sy && nx==sx){ // 출발했던 곳으로 돌아옴(사각형 완성),
                max=Math.max(max,cnt);
                return;
            }

            if (eat[map[ny][nx]]) continue; // 이미 먹었던 디저트임
            if (dirs[d]) continue; // 이전에 이미 사용했던 방향임

            if (preDir!=d){ // 방향을 꺾음 => 이 방향은 이제 갈 수 없음
                dirs[preDir]=true;
            }
            dfs(sy,sx,ny,nx,d,cnt+1);
            dirs[preDir]=false;
        }
        eat[map[y][x]]=false;
    }
}
