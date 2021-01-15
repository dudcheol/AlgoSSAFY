package algoSSAFY.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17472_다리만들기2 {
    private static final int BRIDGE = 9;
    private static int N,M;
    private static int[] dy = {-1,1,0,0};
    private static int[] dx = {0,0,-1,1};
    private static boolean[][] visited;
    private static int islandCnt, min;
    private static int max;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken())==1?-1:0;
            }
        }

        // 각 섬 표시하기
        int num=1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]==-1) {
                    findIsland(i,j,num++,map);
                }
            }
        }

        islandCnt = num-1;
        visited = new boolean[num][num];
        min =Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]==1) dfs(1,i,j,0,map);
            }
        }

        System.out.println(min==Integer.MAX_VALUE?-1:min);
    }

    private static void dfs(int island, int y, int x, int cnt, int[][] map) {
        print(map);

        if (isAllConnected()){
            min = Math.min(min, cnt);
//            print(map);
            return;
        }

        for (int d = 0; d < 4; d++) {
            if (buildable(y, x, d, map)) {
                int[][] tmp = deepcopy(map);
                int bCnt=0;
                int dest;
                int ny=y,nx=x;
                while (true){
                    ny += dy[d];
                    nx += dx[d];
                    if (tmp[ny][nx]!=BRIDGE && tmp[ny][nx]!=0) {
                        dest = tmp[ny][nx];
                        break;
                    }
                    tmp[ny][nx]=BRIDGE;
                    bCnt++;
                }

                visited[island][dest] = visited[dest][island] = true;
                // 다음 섬찾기
//                if (island==islandCnt){
//                    min = Math.min(min, cnt+bCnt);
//                    return;
//                }
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (tmp[i][j]==island+1){
                            dfs(island+1, i, j, cnt+bCnt, tmp);
                        }
                    }
                }
                visited[island][dest] = visited[dest][island] = false;
            }
        }
    }

    private static void print(int[][] map) {
//        for (int i = 1; i < islandCnt; i++) {
//            if (visited[i])System.out.print(i+" ");
//        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]==9?"*":map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isAllConnected() {
        boolean[][] tmp = deepcopy(visited);

        for (int k = 1; k <= islandCnt; k++) {
            for (int i = 1; i <= islandCnt; i++) {
                for (int j = 1; j <= islandCnt; j++) {
                    if (tmp[i][k] && tmp[k][j]){
                        tmp[i][j]=true;
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= islandCnt; i++) {
            for (int j = 1; j <= islandCnt; j++) {
                if (tmp[i][j])cnt++;
            }
        }

        System.out.println(cnt);
        return cnt==islandCnt*islandCnt;
    }

//    private static int countBridge(int[][] map) {
//        int cnt = 0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if (map[i][j]==BRIDGE)cnt++;
//            }
//        }
//        return cnt;
//    }

    private static void findIsland(int y, int x, int num, int[][] map) {
        map[y][x]=num;
        for (int d = 0; d < 4; d++) {
            int ny = y+dy[d];
            int nx = x+dx[d];
            if (!isIn(ny,nx)||map[ny][nx]!=-1) continue;
            findIsland(ny,nx,num,map);
        }
    }

    // 현재 좌표에서 해당 방향으로 다리를 지을 수 있는지 확인
    private static boolean buildable(int y, int x, int d, int[][] map) {
        int island = map[y][x];
        for (int k = 0; k < 2; k++) { // 길이 2 이상이어야 하므로 2만큼 지어봄
            y += dy[d];
            x += dx[d];
            if (!isIn(y, x) || (map[y][x] != BRIDGE && map[y][x]!=0)) return false;
        }
        while (true){
            y += dy[d];
            x += dx[d];
            if (!isIn(y, x) || map[y][x]==island) return false;
            if (map[y][x] != BRIDGE && map[y][x]!=0) {
                return true;
            }
        }
    }

    private static boolean isIn(int y, int x) {
        return (0<=y&&y<N)&&(0<=x&&x<M);
    }

    private static int[][] deepcopy(int[][] map) {
        int[][] ret = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i],0,ret[i],0,M);
        }
        return ret;
    }

    private static boolean[][] deepcopy(boolean[][] map) {
        boolean[][] ret = new boolean[map.length][map.length];
        for (int i = 1; i < map.length; i++) {
            System.arraycopy(map[i],0,ret[i],0,map.length);
        }
        return ret;
    }
}
