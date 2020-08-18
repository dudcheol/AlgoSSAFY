package programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class PracticeTest {
	static int[] answers = { 1, 3, 2, 4, 2 };
	public static void main(String[] args) {
		int[] first = { 1, 2, 3, 4, 5 };
		int[] second = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] third = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
		
		int[] cnt = { 0, 0, 0 };
		ArrayList<Integer> index = new ArrayList<>();
		int[] answer = {};
		
		for (int i = 0; i < answers.length; i++) {
			if(answers[i] == first[i%first.length]) {
				cnt[0]++;
			}
			if(answers[i] == second[i%second.length]) {
				cnt[1]++;
			}
			if(answers[i] == third[i%third.length]) {
				cnt[2]++;
			}
		}
		
		int max = cnt[0];
		for (int i = 1; i < cnt.length; i++) {
			if(max < cnt[i])
				max = cnt[i];
		}
		for (int i = 0; i < cnt.length; i++) {
			if(max == cnt[i])
				index.add(i + 1);
		}
		
		answer = new int[index.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = index.get(i);
		}	
	}

}
