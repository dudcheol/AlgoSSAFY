/**
 * Backjoon - 1697. 숨바꼭질
 * HideAndSeek_1697.java
 * @date 2020-08-09
 * @author hansolKim
 **/

package backjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HideAndSeek_1697 {
	
	private static int N, K;
	private static int isVisited[];
	private static int SIZE = 100000;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 수빈의 위치 입력
		K = sc.nextInt(); // 동생의 위치 입력
		
		isVisited = new int[SIZE+1]; // 좌표길이가 총 100000만이고 좌표 그대로 인덱스 사용
		
		bfs(N);
		
	}

	private static void bfs(int pos) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(pos);
		isVisited[pos] = 1; // 방문표기(1회차 방문)
		
		while(!q.isEmpty()) {
			int x = q.peek();
			if(x == K) { // 수빈이와 동생의 위치가 같아진 경우
				System.out.println(isVisited[x] - 1);
				break;
			} 			
			
			if(x>pos && isVisited[x-1] == 0) {
				q.offer(x-1);
				isVisited[x-1] = isVisited[x]+1;
				continue;
			} else {
				if(x*2<=SIZE && isVisited[x*2] == 0) {
					q.offer(x*2);
					isVisited[x*2] = isVisited[x]+1;
				}
				
				if(x+1<=SIZE && isVisited[x+1] == 0) {
					q.offer(x+1);
					isVisited[x+1] = isVisited[x]+1;
				}
				
				if(x>0 && isVisited[x-1] == 0) {
					q.offer(x-1);
					isVisited[x-1] = isVisited[x]+1;
					
				}
			}
			q.poll();
		}
	}

}
