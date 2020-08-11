package programmers;

public class StockPrice {	
	static int tmp;

	
	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 2, 3 };	
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < answer.length; i++) {
            for (int j = i + 1; j < answer.length; j++) {
                if (prices[i] > prices[j]) { // 주식 가격이 떨어질 때 
                    answer[i] = j - i; // 인덱스 차를 넣어줌
                    break;
                }
                else 
                	answer[i] = answer.length - i - 1; // 주식 가격이 떨어질 때가 없으면 걍 배열 크기에서 index 뺌
            }
        }
		
        for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
}
