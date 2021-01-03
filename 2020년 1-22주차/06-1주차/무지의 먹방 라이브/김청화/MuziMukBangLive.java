package programmers;

import java.util.LinkedList;
import java.util.Queue;

// 효율성 실패
// 프로그래머스 무지의 먹방라이브 https://programmers.co.kr/learn/courses/30/lessons/42891
public class MuziMukBangLive {
	static int[] food_times = { 3, 1, 2 }; // 각 음식을 모두 먹는데 필요한 시간(음식의 버호 순서대로)
	static int k = 5; // 방송이 중단된 시간
	
	public static void main(String[] args) {
		long result = solution(food_times, k);
		System.out.println(result);
	}
	
	
	public static long solution(int[] food_times, long k) {
        long answer = 0;
        
        int total_times = 0; // 음식을 다 먹는데 필요한 시간
        Queue<Food> q = new LinkedList<Food>();
        
        for (int i = 0; i < food_times.length; i++) {
			q.add(new Food(i + 1, food_times[i]));
			total_times += food_times[i];
		}
        
        int cnt = 0;
        while(!q.isEmpty()) {
        	Food f = q.poll();
        	
        	if(k >= total_times || cnt == total_times) {
        		answer = -1;
        		break;
        	}
        	
        	if(cnt == k) {
        		answer = f.idx;
        		break;
        	}
	
        	// 만약 남은 양이 0이 아니라면 1만큼 빼주고 다시 넣어줌 
        	if(f.remain != 0) {
        		f.remain -= 1;
        		if(f.remain != 0)
        			q.offer(f);
        		cnt++; // 음식 먹은 횟수 증가
        	}
        }
        
        return answer;
    }
	
	static class Food {
		int idx;
		int remain;
		
		Food(int idx, int remain){
			this.idx = idx;
			this.remain = remain;
		}
	}
}
