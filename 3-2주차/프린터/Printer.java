package programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

// https://programmers.co.kr/learn/courses/30/lessons/42587

public class Printer {
	//1, 1, 9, 1, 1, 1
	//0
	static int[] priorities = {2, 3, 1, 4, 5, 9, 6, 7};
	static int location = 3;
	//static int[] priorities = {2, 1, 3, 2};
	//static int location = 2;
	
	public static void main(String[] args) {
		int result;
		
		result = solution(priorities, location);
		System.out.println(result);
	}
	
	public static int solution(int[] priorities, int location) {
		int answer = 1;
		
		Queue<doc> q = new LinkedList<doc>(); // priority 담을 큐
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		// 큐에 집어 넣음
		for (int i = 0; i < priorities.length; i++) {
			if(i == location)
				q.add(new doc(priorities[i], 1)); // 보고자 하는 문서 location에 1
			else
				q.add(new doc(priorities[i], 0));
			arr.add(priorities[i]);
		}
		arr.sort(Comparator.reverseOrder()); // 우선순위가 높 -> 낮으로 정렬
	
		doc pre = q.poll(); // 일단 맨 앞거 하나 뺌
		
		// 하나씩 빼면서 뒤에 우선 순위가 높은 문서가 있는지 확인해야 함
		while(!q.isEmpty()) {		
			
			// 만약 arr 맨 앞의 숫자(우선순위)보다 낮으면 뒤로 보냄
			if(pre.priorities < arr.get(0)) { 
				q.add(pre);
			}
			
			// 만약 우선 순위가 가장 높으면 remove
			if(pre.priorities >= arr.get(0)) { 				
				if(pre.location == 1)
					break;
				arr.remove(0);	// 가장 높은 우선순위의 문서가 뽑혔으니 삭제	
				answer++;		// 문서가 뽑힐 때마다 ++

			}
			pre = q.poll(); // 다음 문서 탐색
		}
		
		return answer;
	}
	
	static class doc{
		int priorities;
		int location;
		
		public doc(int priorities, int location) {
			this.priorities = priorities;
			this.location = location;	
		}
		
		
	}
	
}

