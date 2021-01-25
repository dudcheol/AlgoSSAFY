package algoSSAFY.week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2573_빙산 {
    private static int N, M, map[][], dy[] = {-1, 1, 0, 0}, dx[] = {0, 0, -1, 1};
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time=0;
        while (true) {
            // 빙산이 있는지 확인
            boolean hasBingsan = false;
            int cnt = 0;
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 빙산이 몇 덩어리인지 확인
                    if (map[i][j] != 0 && !visited[i][j]) {
                        hasBingsan = true;
                        dfs(i, j);
                        cnt++;
                    }
                }
            }

            if (!hasBingsan) { // 빙산 없음
                System.out.println(0);
                return;
            }

            if (cnt>=2){ // 비산이 2덩어리 이상으로 나뉘어짐
                System.out.println(time);
                return;
            }

            // 빙산이 1덩어리면 시뮬레이션 진행
            int[][] tmp = deepcopy(map);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j]!=0){
                        simulation(tmp, i, j);
                    }
                }
            }
            map=tmp;

            time++;
        }
    }

    private static void simulation(int[][] tmp, int y, int x) {
        int cnt=0;
        for (int d = 0; d < 4; d++) {
            int ny=y+dy[d];
            int nx=x+dx[d];
            if (map[ny][nx]==0) cnt++;
        }
        tmp[y][x]= tmp[y][x]-cnt <= 0? 0 : tmp[y][x]-cnt;
    }

    private static int[][] deepcopy(int[][] map) {
        int[][] ret = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i],0,ret[i],0,M);
        }
        return ret;
    }

    private static void dfs(int y, int x) {
        visited[y][x] = true;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (visited[ny][nx] || map[ny][nx] == 0) continue;
            dfs(ny, nx);
        }
    }
}
