package algoSSAFY.week27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2636_치즈 {
    private static int R,C,map[][], dy[]={-1,1,0,0}, dx[]={0,0,-1,1};
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map=new int[R][C];
        int cnt=0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < C; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
                if (map[i][j]==1)cnt++;
            }
        }

        int pre=cnt, time=0;
        while(true) {
            int tmpCnt=count(); //치즈 갯수 세기
            if (tmpCnt == 0) {
                System.out.println(time);
                System.out.println(pre);
                return;
            }

            // 공기와 닿는 부분 녹음
            int[][] tmp = deepcopy(map);
            visited=new boolean[R][C];
            dfs(0,0,tmp);
//            bfs(tmp);

            pre=tmpCnt;
            time++;
        }
    }

    private static void dfs(int y, int x, int[][] tmp) {
        visited[y][x]=true;
        for (int d = 0; d < 4; d++) {
            int ny=y+dy[d];
            int nx=x+dx[d];
            if (ny<0||nx<0||ny>=R||nx>=C||visited[ny][nx])continue;
            if (tmp[ny][nx]==1) map[ny][nx]=0;
            else dfs(ny,nx,tmp);
        }
    }

//    private static void bfs(int[][] tmp) {
//        Queue<int[]> q = new LinkedList<>();
//
//        q.offer(new int[]{0,0});
//
//        while (!q.isEmpty()){
//            int[] p = q.poll();
//            int y=p[0];
//            int x=p[1];
//            for (int d = 0; d < 4; d++) {
//                int ny=y+dy[d];
//                int nx=x+dx[d];
//                if (ny<0||nx<0||ny>=R||nx>=C||visited[ny][nx]) continue;
//                visited[ny][nx]=true;
//                if (tmp[ny][nx]==1) map[ny][nx]=0;
//                else q.offer(new int[]{ny,nx});
//            }
//        }
//    }

    private static int[][] deepcopy(int[][] map) {
        int[][] ret = new int[R][C];
        for (int i = 0; i < R; i++) {
            System.arraycopy(map[i],0,ret[i],0,C);
        }
        return ret;
    }

    private static int count() {
        int cnt=0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==1)cnt++;
            }
        }
        return cnt;
    }
}
