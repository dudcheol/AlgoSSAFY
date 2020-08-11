package programmers;

import java.util.Stack;

public class StockPrice_Stack {

	public static void main(String[] args) {
		
		int[] prices = { 1, 2, 3, 4, 5, 3, 4, 2, 1, 5, 1, 3, 4 };	
        Stack<Integer> beginIdxs = new Stack<>();
        int i = 0;
        int[] answer = new int[prices.length];

        beginIdxs.push(i);
        for (i = 1; i < prices.length; i++) {
        	
            while (!beginIdxs.empty() && prices[i] < prices[beginIdxs.peek()]) { // 끝까지 돌거나 주식가격이 떨어질 떄까지
            	
                int beginIdx = beginIdxs.pop(); // index
                answer[beginIdx] = i - beginIdx; // 떨어질 때까지 
                
            }
            beginIdxs.push(i); // index로서 i값을 stack에 저장
        }
        while (!beginIdxs.empty()) {
            int beginIdx = beginIdxs.pop(); // 떨어졌을 때의 index는 남아있지 않기 때문
            answer[beginIdx] = i - beginIdx - 1; 
        }
        for (int j = 0; j < answer.length; j++) {
			System.out.println(answer[j]);
		}
        
	}

}
