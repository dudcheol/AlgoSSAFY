import java.util.*;

class Point {
	int i, j, sum, dir;

	public Point(int i, int j, int sum, int dir) {
		super();
		this.i = i;
		this.j = j;
		this.sum = sum;
		this.dir = dir;
	}

}

class 경주로건설_박유정 {
	static int size;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int min = Integer.MAX_VALUE;

	public static int solution(int[][] map) {
		size = map.length;
		Queue<Point> q = new LinkedList<>();
		
		map[0][0]=1;//시작점은 탐색 안하도록 1넣어줌
		
		q.add(new Point(0, 0, 0, 0));
		
		int answer = Integer.MAX_VALUE;
		
		while (!q.isEmpty()) {//bfs 풀이 dfs 는 실패

			Point p = q.poll();
			int i = p.i;
			int j = p.j;
			int sum = p.sum;
			int dir = p.dir;
			
			if (i == size - 1 && j == size - 1) {//도착지점 도달하면 작은 값을 answer 에 넣어줌
				answer = Math.min(answer, sum);
//				System.out.println(answer);
				continue;
			}
			
			for (int k = 0; k < 4; k++) {
				int x = i + dx[k];// 상하좌우 이동
				int y = j + dy[k];

				if (x < 0 || y < 0 || x >= size || y >= size || map[x][y] == 1)// 배열 범위 벗어나거나 벽이있으면 넘어감
					continue;
				
				int new_cost = 0;
				if (k == dir || (i == 0 && j == 0))// 전과 방향이 일치하거나 출발점이면 +100
					new_cost=sum+100;

				else
					new_cost=sum+600;//코너지점과 직선거리를 합한 값인 600을 넣아즘

				if (map[x][y] == 0||map[x][y]>=new_cost) {//방문하지 않음 or 방문햇는데 비용이 더 적게나온경우
					map[x][y] = new_cost;
					q.add(new Point(x, y, new_cost, k));
				}
			}
		}
		return answer;
	}

//	public static void main(String[] args) {
//		int[][] arr = { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
//				{ 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 1, 0 },
//				{ 0, 1, 0, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0 } };
//		min = solution(arr);
//		System.out.println("--==================--" + min);
//	}
}