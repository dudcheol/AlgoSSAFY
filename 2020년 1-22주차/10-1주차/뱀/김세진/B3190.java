package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B3190 {
	static int N, K, L;
	static int[][] map;
	static boolean[][] visited;

	// 위 왼 아래 오른 순
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		Queue<String[]> qu = new LinkedList<String[]>();

		for (int i = 0; i < K; i++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0])-1;
			int y = Integer.parseInt(input[1])-1;
			map[x][y] = 1;
		}
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			qu.offer(br.readLine().split(" "));
		}
		int time = 0;
		// 뱀 위치
		visited[0][0] = true;
		// 0 위 1 왼 2 아래 3 오른
		int headX = 0;
		int headY = 0;
		
		//꼬리 위치
		int tailX=0;
		int tailY=0;
		
		
		
		Queue<int[]> tailQu=new LinkedList<int[]>();
		//초기 꼬리의 위치
		tailQu.offer(new int[] {0,0});
		int direction = 3;
		
		//순서 
		
		//1. 머리를 먼저 감
		//2. 사과 유무에 따른 결과 변화
		
		while (true) {
			// 1초 증가
			time++;

			// 움직임
			
			//1. 머리를 먼저감
			int rdx = headX + dx[direction];
			int rdy = headY + dy[direction];
			
			//머리가 범위 외 + 내몸에 닿으면
			if (rdx < 0 || rdy < 0 || rdx >= N || rdy >= N || visited[rdx][rdy]) {
				System.out.println(time);
				return;
			}

			//머리 부분 도착
			visited[rdx][rdy] = true;
			
			// 사과가 아니면 꼬리가 짧아짐
			
			
			
			if (map[rdx][rdy] != 1) {
				//꼬리의 좌표를 가진 queue에서 좌표 1세트를 꺼냄
				if(!tailQu.isEmpty()) {
					int[] tailXY=tailQu.poll();
					tailX=tailXY[0];
					tailY=tailXY[1];
				}
				//꼬리 위치 false 변경
				visited[tailX][tailY] = false;
			}else {
				map[rdx][rdy]=0;  //분노의 라인 ... 이거때문에 20분을..
			}
			
			//꼬리의 정보를 가진 qu에 현재 방향의 좌표값을 넣어줌
//			tailQu.offer(new int[] {tailX+dx[direction],tailY+dy[direction]});
			tailQu.offer(new int[] {rdx,rdy});
			
			headX = rdx;
			headY = rdy;

			// 뱀 상황 종료

			// 방향 전환 정보

			// 매번 뽑아줘야 하는데 좋은 방법 없나?

			if (!qu.isEmpty()) {
				String[] input = qu.peek();
				// 만약 같다면,
				if (time == Integer.parseInt(input[0])) {
					// 뽑고
					input = qu.poll();

					if (input[1].equals("D")) {
						direction -= 1;
					} else {
						direction += 1;
					}

					if (direction == -1) {
						direction = 3;
					}
					if (direction == 4) {
						direction = 0;
					}
				}
			}

			
		}
	}

}
