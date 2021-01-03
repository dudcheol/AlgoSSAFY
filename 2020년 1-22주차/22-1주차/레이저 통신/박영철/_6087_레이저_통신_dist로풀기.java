package algoSSAFY.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _6087_레이저_통신_dist로풀기 {
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
        int[][] dist = new int[H][W];
        for (int k = 0; k < H; k++) {
            Arrays.fill(dist[k], Integer.MAX_VALUE);
        }

        for (int d = 0; d < 4; d++) {
            dist[i][j]=0;
            int ny = i + dy[d];
            int nx = j + dx[d];
            if (ny<0||nx<0||ny>=H||nx>=W||map[ny][nx]=='*') continue;
            dist[ny][nx] = 0;
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
                if (ny<0||nx<0||ny>=H||nx>=W||map[ny][nx]=='*') continue;

                int nMirror = dir==d?mirror:mirror+1;
                if (dist[ny][nx] >= nMirror){
                    dist[ny][nx] = nMirror;
                    pq.offer(new int[]{ny,nx,nMirror,d});
                }
            }
        }
    }
}
