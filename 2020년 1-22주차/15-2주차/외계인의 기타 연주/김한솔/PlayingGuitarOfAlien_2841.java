/**
 * Backjoon - 2669. 직사각형 네개의 합집합의 면적 구하기
 * FourSumRectangle_2669.java
 * @date 2020-09-22
 * @author hansolKim
 **/

package p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PlayingGuitarOfAlien_2841 {

	static int move;
	static PriorityQueue<Integer>[] pqs;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		pqs = new PriorityQueue[7];
		
		for(int i=1; i<7; i++) {
			pqs[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
		
		move = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int line = Integer.parseInt(st.nextToken()); // 줄 번호
			int fret = Integer.parseInt(st.nextToken()); // 프렛
			
			// 해당 줄이 비어 있다면
			if(pqs[line].isEmpty()) {
				add(line, fret);
				continue;
			}
			
			// 해당 줄이 비어있지 않다면 --> 가장 높은 음 확인 후 현재 음보다 같거나 작을 때까지
			while(!pqs[line].isEmpty() && pqs[line].peek()>fret) {
				pqs[line].poll();
				move++;
			}
			
			// 비어있다면 추가하고 continue
			if(pqs[line].isEmpty()) { add(line, fret); }
			
			// 현재 프렛음과 같으면 continue, 같지 않다면 추가
			if(pqs[line].peek() == fret) { continue; }
			add(line, fret);
		}
		
		System.out.println(move);
		br.close();
	}
	
	private static void add(int line, int fret) {
		pqs[line].add(fret);
		move++;
	}
}