package week6;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class 무지의먹방라이브 {

	class Food {
		int num;
		long time;

		public Food(int num, long time) {
			this.num = num;
			this.time = time;
		}
	}

	public int solution(int[] food_times, long k) {
		int answer = 0;

		// food_times 길이 1~200,000
		// food_times 원소 1~100,000,000
		// k 길이 1~2x10^13

		int foodSize = food_times.length;
		long mok = k / foodSize;
		long rest = k % foodSize;

		// 모든 음식을 큐에 담는다
		Queue<Food> q = new LinkedList<Food>();

		for (int i = 0; i < foodSize; i++) {
			q.offer(new Food(i + 1, (long) food_times[i]));
		}

		// 모든 음식에 몫을 뺀다 뺐는데 음수일 경우 다음 음식으로 전가
		long pre = 0; // 현재 값이 음수일 경우 다음 음식에게 전가하기 위한 변수
		int size = q.size();
		while (mok != 0 || !q.isEmpty()) {
			if (size-- <= 0) {
				// 한바퀴 돌았으므로 음수를 가지고있는지 판단
				long sumFood = 0;
				boolean hasMinus = false;
				for (Iterator iterator = q.iterator(); iterator.hasNext();) {
					long foodTime = ((Food) iterator.next()).time;
					if (foodTime < 0) {
						hasMinus = true;
					}
					sumFood += foodTime;
				}
				sumFood += pre;
				if (hasMinus) {// 음수가 있다면
					// 모든 수를 더했는데 음수라면 가망없으므로 반복문을 종료
					if (sumFood < 0)
						return -1;
				} else {
					if (pre >= 0)
						break;// 음수가 없고 pre가 음수가 아니라면 반복문 종료
				}
				mok = 0; // 한바퀴 돌았으므로 몫만큼 빼지 않는다
				size = q.size() - 1; // 큐 사이즈 초기화
			}

			Food food = q.poll();
			food.time = food.time - mok + pre;

			if (food.time <= 0) {
				pre = food.time;
				if (q.isEmpty())
					answer = food.num;
			} else {
				pre = 0;
				q.offer(food);
			}
		}

		// 이제 나머지만큼 하나씩 꺼내보면 된다
		if (rest == 0) {
			return q.isEmpty() ? -1 : q.peek().num;
		}

		while (rest-- != 0) {
			if (q.isEmpty())
				return answer;
			Food food = q.poll();
			if (--food.time <= 0) {

			} else {
				q.offer(food);
			}
		}

		return q.peek().num;
	}

	public static void main(String[] args) {
		무지의먹방라이브 m = new 무지의먹방라이브();

//		int[] food_times = { 3, 1, 2 };
		int[] food_times = { 5, 4, 3, 2, 1 };
//		long k = 5;
		long k = 10;

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