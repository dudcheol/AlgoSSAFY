/**
 * programmers - 2019 카카오 블라인드 채용. 무지의 먹방 라이브
 * MuzyEatingLive.java
 * @date 2020-08-25
 * @author hansolKim
 **/

package programmers;

import java.util.Arrays;

public class MuzyEatingLive {

	public static void main(String[] args) {
		MuzyEatingLive eatingLive = new MuzyEatingLive();
		int[] food_times = {1,5,5,5,5,6,7};
		long k = 30;
		eatingLive.solution(food_times, k);
	}

	public int solution(int[] food_times, long k) {
		int answer = 0;
		int foodSize = food_times.length;
		int minIdx = 0;
		int[] sortTimes = food_times.clone();

		Arrays.sort(sortTimes);

		int min = 0;

		main: while (true) {

			if (foodSize == 0) {
				answer = -1;
				break;
			}

			int minValue = sortTimes[minIdx] - min;

			/* (음식들을 먹는 시간들 중 최소값*음식의 수)과 k와 비교 */
			if (k >= ((long)minValue * foodSize)) { // 최소값보다 k가 큰 경우 ---> 모든 배열의 값을 최소값만큼 빼준다.
				for (int i = 0; i < food_times.length; i++) {
					food_times[i] -= minValue;
				}
				
				k -= (minValue * foodSize);
				foodSize--;
				min += minValue;
				minIdx++;

				/* 최소값이 되는 것이 여러개인 경우 ---> 0값이 되는 음식이 여러개인 경우인지 확인 ---> minIdx, foodSize 조절 */
				for (int i = minIdx-1; i < sortTimes.length - 1; i++) {
					if (sortTimes[i] != sortTimes[i + 1]) {
						break;
					}
					minIdx++;
					foodSize--;
				}
			} else {
				long idx = (k>=foodSize) ? (int) (k % foodSize) : k;
				for (int i = 0; i < food_times.length; i++) {
					if (food_times[i] > 0) {
						if (idx == 0) {
							answer = i + 1;
							break main;
						}
						idx--;
					}
				}
			}
		}
		System.out.println(answer);
		return answer;
	}
}
