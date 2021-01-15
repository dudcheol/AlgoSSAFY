package algoSSAFY.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _17472_다리만들기2_다시풀기 {
    private static int N,M;
    private static int[] dy = {-1,1,0,0};
    private static int[] dx = {0,0,-1,1};
    private static int[] vertex;
    private static int num;

    private static class Edge{
        int from;
        int to;
        int len;

        public Edge(int from, int to, int len) {
            this.from = from;
            this.to = to;
            this.len = len;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", len=" + len +
                    '}';
        }
    }

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
        num=1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]==-1) {
                    findIsland(i,j,num++,map);
                }
            }
        }

        num-=1; // 정점의 수
        vertex = new int[num+1];
        for (int i = 1; i <= num; i++) {
            vertex[i]=i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)->{
            return Integer.compare(o1.len, o2.len);
        });

        // 각 섬별로 간선 만들기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]==0) continue;
                int cnt,ny,nx;
                loop:for (int d = 0; d < 4; d++) {
                    cnt=0;
                    ny=i;
                    nx=j;
                    int island = map[ny][nx];
                    for (int k = 0; k < 2; k++) { // 길이 2 이상이어야 하므로 2만큼 지어봄
                        ny += dy[d];
                        nx += dx[d];
                        if (!isIn(ny, nx) || map[ny][nx]!=0) continue loop;
                        cnt++;
                    }
                    while (true){
                        ny += dy[d];
                        nx += dx[d];
                        if (!isIn(ny, nx) || map[ny][nx]==island) continue loop;
                        if (map[ny][nx]!=0) {
                            break;
                        }
                        cnt++;
                    }
                    pq.offer(new Edge(map[i][j], map[ny][nx], cnt));
                }
            }
        }

        int limit=num; // 선택할 간선의 갯수는 정점의수-1
        int min=0;
        while (!pq.isEmpty() && limit>0){
            Edge edge = pq.poll();

            if (!union(edge.from, edge.to)) continue;
            min+=edge.len;
            limit--;
        }

        // 모든 섬이 연결되었는지 확인
        int root = find(1);
        for (int i = 2; i <= num; i++) {
            if (root!=find(i)){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(min);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;
        vertex[bRoot]=aRoot;
        return true;
    }

    private static int find(int x) {
        if (vertex[x]==x) return x;
        return vertex[x] = find(vertex[x]);
    }

    private static void findIsland(int y, int x, int num, int[][] map) {
        map[y][x]=num;
        for (int d = 0; d < 4; d++) {
            int ny = y+dy[d];
            int nx = x+dx[d];
            if (!isIn(ny,nx)||map[ny][nx]!=-1) continue;
            findIsland(ny,nx,num,map);
        }
    }

    private static boolean isIn(int y, int x) {
        return (0<=y&&y<N)&&(0<=x&&x<M);
    }
}
