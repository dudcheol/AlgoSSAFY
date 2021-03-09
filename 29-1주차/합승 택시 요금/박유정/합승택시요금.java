package programmers;

import java.util.Arrays;

public class 합승택시요금 {
	  public int solution(int n, int s, int a, int b, int[][] fares) {
			int[][] map = new int[n + 1][n + 1];
			final int inf = 1000001;//100001로했더니 틀림

			for (int i = 0; i < map.length; i++) {//초기화
				Arrays.fill(map[i], inf);
	            map[i][i]=0;
			}
			for (int i = 0; i < fares.length; i++) {
				map[fares[i][0]][fares[i][1]] = fares[i][2];
				map[fares[i][1]][fares[i][0]] = fares[i][2];
			}

			for (int i = 0; i < map.length; i++) {// 경유
				for (int j = 0; j < map.length; j++) {// 출발
					for (int k = 0; k < map.length; k++) {// 도착
						if (map[j][k] > map[j][i] + map[i][k]) {
							map[j][k] = map[j][i] + map[i][k];
						}
					}
				}
			}
			int min = map[s][a]+map[s][b];
			
			for (int i = 0; i < map.length; i++) {//같이 타고가는 지점
				if(min>map[s][i]+map[i][a]+map[i][b]) {
					min=map[s][i]+map[i][a]+map[i][b];
				}
			}
			System.out.println(min);
			return min;
		}

	public static void main(String[] args) {
		합승택시요금 a=new 합승택시요금();
		a.solution(6,4,6,2,new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
		
	}

}

