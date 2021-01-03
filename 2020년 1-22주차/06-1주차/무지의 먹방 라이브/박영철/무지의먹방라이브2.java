package week6;

import java.util.LinkedList;
import java.util.Queue;

public class 무지의먹방라이브2 {

	class Food {
		int num;
		long time;

		public Food(int num, long time) {
			this.num = num;
			this.time = time;
		}
	}

	public int solution(int[] food_times, long k) {
		long size = (long)food_times.length;
		
		Queue<Food> q = new LinkedList<Food>();
		
		// 모든 음식의 합이 k 보다 작으면 return -1;
		long sum = 0L;
		for(int i=0;i<size;i++) {
			q.offer(new Food(i+1, food_times[i]));
			sum += (long)food_times[i];
		}
		if(sum <= k) return -1;

		// k를 size로 나눈 몫과 나머지를 구한다
		long mok = k/size; // 모든 음식을 mok 만큼은 먹어야 함 
		long rest = k%size; // 그리고나서 rest번째 음식까지 먹음
		
		long eat = mok*size;
		while(!q.isEmpty() && eat > 0) {
			Food current = q.poll();
			
			// eat 에서 최대 mok만큼 뺀다
			long res = eat;
			if(current.time <= mok) {
				res -= current.time;
				current.time = 0L;
			} else {
				res -= mok;
				current.time -= mok;
			}
			
			// eat은 음수가 되면 안됨
			if(res < 0L) {
				current.time -= res;
				q.offer(current);
				eat = 0L;
			} else if(res >= 0L) {
				if(current.time != 0L) {
					q.offer(current);
				}
				eat = res;
			}
		}
		
		// 나머지만큼만 돌리면 됨
		while(--rest >= 0L) {
			Food current = q.poll();
			if(--current.time != 0L) {
				q.offer(current);
			}
		}
		
		int answer = q.peek().num;
		return answer;
	}

	public static void main(String[] args) {
		무지의먹방라이브2 m = new 무지의먹방라이브2();

//		int[] food_times = { 3, 1, 2 };
//		int[] food_times = { 5, 4, 3, 2, 1 };
//		int[] food_times = { 1,1,1,1 };
//		int[] food_times = {3,1,1,1,2,4,3};
//		int[] food_times = {4, 3, 5, 6, 2};
//		int[] food_times = {4,1,1,5};
		int[] food_times = {3,1,4,5};
//		long k = 5;
//		long k = 9;
//		long k = 4;
//		long k = 12; // 6
//		long k = 7; // 3
//		long k = 4; // 4
		long k = 12; // 4

		System.out.println(m.solution(food_times, k));
	}

}

/*
 * import java.util.LinkedList; import java.util.Queue;
 * 
 * class Solution { public int solution(int[] food_times, long k) { int answer =
 * 0; int fSize = food_times.length; int mok = (int) (k / fSize); int rest =
 * (int) (k - (fSize * mok)); int use = mok * fSize;
 * 
 * int i = 0;
 * 
 * while (mok!=0) { if (food_times[i] == 0) { i++; continue; } int sub =
 * food_times[i] - mok; if (sub < 0) { food_times[i] = 0; use -= sub; } else {
 * food_times[i] = sub; use -= mok; }
 * 
 * if (i == fSize - 1) { i = 0; } else { i++; } if (use == 0) { break; } }
 * 
 * int zeroCnt=0; while (true) { if(zeroCnt == fSize) { answer = -1; break; }
 * 
 * if (food_times[i] == 0) { zeroCnt++; } else { food_times[i] -= 1; rest--; }
 * 
 * if (i == fSize - 1) { i = 0; } else { i++; } if(rest == 0) { answer = i+1;
 * break; } }
 * 
 * return answer; } }
 */