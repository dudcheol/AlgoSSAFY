package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FunctionDevelopment {
	
	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		
		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> list = new ArrayList<>(); // 동적으로 데이터 추가 가능
		
		for (int i = 0; i < progresses.length; i++) {
			int day = (100 - progresses[i]) / speeds[i];
			int remainder = (100 - progresses[i]) % speeds[i];
			if(remainder != 0)
				day = day + 1;
			q.add(day);
		}
		
		int cnt = 1;
		int first = q.poll(); // 일단 맨 앞 뽑음	
		while(!q.isEmpty()) {
			
			// 첫 번째 가정: 맨 앞의 작업이 빨리 끝나지 않을 경우 먼저
			// 1. 맨 앞의 작업이 제일 빨리 끝나지 않을 경우
			if(first >= q.peek()) { 
				q.poll();
				cnt++;
			}	
			// 2. 맨 앞의 작업이 제일 빨리 끝날 경우
			else if(first < q.peek()) {
				list.add(cnt);
				cnt = 1;
				first = q.poll();
			}
		}
		list.add(cnt);
		
		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(answer[i]);
		}
		
		//
		/// ((100 - progresses[i]) / speeds[i]) + 1  => 배포 일 수
		/// 
		///
		
	}

}
