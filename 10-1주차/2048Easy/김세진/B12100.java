package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B12100 {
	static int N, max;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stz.nextToken());
			}
		}
		perM(0);
		System.out.println(max);
	}

	static void perM(int depth) {
		//원래 map 저장
		int[][] mirror = map.clone();

		// base case
		if (depth == 5) {

			// 5가 되면 최대값을 찾아서 max에 저장 후 return 한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max < map[i][j]) {
						max = map[i][j];
					}
				}
			}
			return;
		}
		// 상하좌우
		for (int i = 0; i < 4; i++) {
			solution(i);
			perM(depth+1);
			//원래 map으로 다시 복귀
			map = mirror.clone();
		}
	}

	static void solution(int direction) {
		Queue<Integer> qu = new LinkedList<Integer>();
		int[][] mirror = new int[N][N];

		// 상
		if (direction == 0) {
			
			//모든 칸 확인
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 세로축 기준 맨위부터 확인 / 0이 아닌공간이면 qu에 값을 넣어줌
					if (map[j][i] != 0) {
						qu.offer(map[j][i]);
					}
				}
				int index = 0;
//				System.out.println(qu.size());
				while (!qu.isEmpty()) {
					//제일 위칸에 있는 값 
					int value = qu.peek();
					
					// 값이 0이면 qu에서 값을 빼서 그 자리에 넣어줌
					if (mirror[index][i] == 0) {
						mirror[index][i] = qu.poll();
					}
					// 값이 같다면 합쳐줘야함
					else if (mirror[index][i] == value) {
						mirror[index++][i] += qu.poll();
					}
					// 두개 다 아닌경우 따로따로
					else {
						mirror[++index][i] = qu.poll();
					}

				}
//				System.out.println(qu.size());
			}
		}
		// 하
		if (direction == 1) {

			for (int i = 0; i < N; i++) {
				for (int j = N-1; j > -1; j--) {
					// 세로축 기준 0이 아닌공간이면 qu에 값을 넣어줌
					// 아래에서 위로 순서 확인
					if (map[j][i] != 0) {
						qu.offer(map[j][i]);
					}
				}
				//제일 아래부터
				int index = N-1;
				while (!qu.isEmpty()) {
					int value = qu.peek();

					// 값이 없다면
					if (mirror[index][i] == 0) {
						mirror[index][i] = qu.poll();
					}
					// 값이 같다면 합쳐줘야함
					else if (mirror[index][i] == value) {
						mirror[index--][i] += qu.poll();
					}
					// 두개 다 아닌경우 따로따로
					else {
						mirror[--index][i] = qu.poll();
					}

				}
			}
		}
		// 좌
		if (direction == 2) {

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 가로축 기준 왼쪽부터 확인 0이 아닌공간이면 qu에 값을 넣어줌
					if (map[i][j] != 0) {
						qu.offer(map[i][j]);
					}
				}
				int index = 0;
				while (!qu.isEmpty()) {
					int value = qu.peek();

					// 값이 없다면
					if (mirror[i][index] == 0) {
						mirror[i][index] = qu.poll();
					}
					// 값이 같다면 합쳐줘야함
					else if (mirror[i][index] == value) {
						mirror[i][index++] += qu.poll();
					}
					// 두개 다 아닌경우 따로따로
					else {
						mirror[i][++index] = qu.poll();
					}

				}
			}
		}
		// 우
		if (direction == 3) {

			for (int i = 0; i < N; i++) {
				for (int j = N-1; j > -1; j--) {
					// 세로축 기준 0이 아닌공간이면 qu에 값을 넣어줌
					if (map[i][j] != 0) {
						qu.offer(map[i][j]);
					}
				}
				int index = N-1;
				while (!qu.isEmpty()) {
					int value = qu.peek();

					// 값이 없다면
					if (mirror[i][index] == 0) {
						mirror[i][index] = qu.poll();
					}
					// 값이 같다면 합쳐줘야함
					else if (mirror[i][index] == value) {
						mirror[i][index--] += qu.poll();
					}
					// 두개 다 아닌경우 따로따로
					else {
						mirror[i][--index] = qu.poll();
					}

				}
			}
		}
		map=mirror.clone();
	}
	
	static void printM(int[][] map) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
