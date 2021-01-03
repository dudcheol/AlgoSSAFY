package algoSSAFY.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _6087_레이저_통신_4차원visited로풀기 {
    private static int W,H;
    private static char[][] map;
    private static int[] dy = {-1,1,0,0}; //상하좌우
    private static int[] dx = {0,0,-1,1}; //상하좌우
    private static int min;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][];
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j]=='C') {
                    map[i][j] = '.';
                    min = Integer.MAX_VALUE;
                    bfs(i,j);
                    System.out.println(min);
                    return;
                }
            }
        }
    }

    private static void bfs(int i, int j) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)->{
           return Integer.compare(o1[2],o2[2]);
        });
        boolean[][][][] visited = new boolean[4][4][H][W];

        for (int d = 0; d < 4; d++) {
            visited[d][d][i][j]=true;
            int ny = i + dy[d];
            int nx = j + dx[d];
            if (ny<0||nx<0||ny>=H||nx>=W||map[ny][nx]=='*') continue;
            visited[d][d][ny][nx] = true;
            pq.offer(new int[]{ny,nx,0,d});
        }

        while(!pq.isEmpty()){
            int[] poll = pq.poll();
            int y = poll[0];
            int x = poll[1];
            int mirror = poll[2];
            int dir = poll[3];

            if (map[y][x]=='C'){
                min = Math.min(mirror, min);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny<0||nx<0||ny>=H||nx>=W||map[ny][nx]=='*'||visited[dir][d][ny][nx]) continue;
                visited[dir][d][ny][nx] = true;
                pq.offer(new int[]{ny,nx,dir==d?mirror:mirror+1,d});
            }
        }
    }
}

// 질문
// 어떤 사람은 3차원배열로 풀었다고 하는데 어떻게 풀어야 하는지?
// 4차원으로는 풀 순 있음.. 근데 좋은 코드라고 생각되진 않음

/*
단순히 어떤 칸에 먼저 도달하는지만 필요한 게 아니라, 어느 방향으로 가던 건지도 중요합니다.
아래와 같은 예시에서 아래 방향이 먼저 큐에 들어가기 때문에 1행 0열은 왼쪽 방향으로 가는 것이 먼저 큐에 들어가고,
아래 방향으로 가는 것은 무시됩니다. 하지만 왼쪽 방향으로 가던 것은 실제로는 아래로 다시 한 번 꺾어줘야 하므로 손해가 납니다.
답:1
내풀이:2
2 3
.C
..
C*
 */
