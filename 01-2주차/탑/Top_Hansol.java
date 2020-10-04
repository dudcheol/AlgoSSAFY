package programmers;

import java.util.Stack;

public class Top {
	
	 public int[] solution(int[] heights) {
		int[] answer = {};
		
		answer = new int[heights.length];
		Stack<Integer> heightMemory = new Stack<>();
		Stack<Integer> indexMemory = new Stack<>();
		
		for (int idx=0; idx < heights.length ; idx++) {
			
			int height = heights[idx];
			
			// 1. 스택에 아무것도 없는 경우 -> 스택에 푸시 및 수신 받지 못하므로 0값 삽입
			while(true) {
				
				// 모두 꺼내어 진 경우
				if(heightMemory.isEmpty()) {
					answer[idx] = 0;
					heightMemory.push(height);
					indexMemory.push(idx+1);
					break;
				}
				
				// 수신 받은 경우
				if(height < heightMemory.peek()) {
					answer[idx] = indexMemory.peek();
					heightMemory.push(height);
					indexMemory.push(idx+1);
					break;
				}
				
				// 수신 받지 못한경우
				heightMemory.pop();
				indexMemory.pop();
			}		
		}
		
		return answer;
	}
}
