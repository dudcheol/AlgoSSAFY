package programmers;

import java.util.ArrayList;

public class FormulaMaximization {
	
	private static ArrayList<Character> operators;
	private static ArrayList<Long> numbers;
	private static int N = 3;
	private static char[] staticOps = {'*', '+', '-'};
	private static char[] ops;
	private static boolean[] isSelected; // 사용한 숫자인지 여부 배열
	private static long number = 0;
	private static long answer = 0;
	
//	public static void main(String[] args) {
//		FormulaMaximization formulaMaximization = new FormulaMaximization();
//		String expression = "300-600+900";
//		formulaMaximization.solution(expression);
//	}
	
	public long solution(String expression) {
        
        operators = new ArrayList<>();
        numbers = new ArrayList<>();
        ops = new char[N];
        isSelected = new boolean[N]; // false로 초기화
        
        for(int i=0; i<expression.length(); i++) {
        	
        	char exp = expression.charAt(i);
        	
        	if(exp == '*' || exp == '+'
        			|| exp == '-') {
        		operators.add(exp);
        		numbers.add(number);
        		number = 0;
        		continue;
        	}
        	number *= 10;
        	number += (exp-'0');
        }
        
        // 마지막 숫자 삽입
        numbers.add(number);
        
        permutaition(0);
        
        System.out.println(answer);
        return answer;
    }
	
	public void permutaition(int cnt) {
		
		if(cnt == N) {			
			// 여기서 호출
			getAnswer(ops);
			return;
		}
		
		for(int i=0; i<N; i++) {
			
			if(isSelected[i]) continue;
			ops[cnt] = staticOps[i];
			isSelected[i] = true;
			
			permutaition(cnt+1);
			isSelected[i] = false;
		}
	}
	
	// 수식의 순서를 주고 값을 얻는 메소드
	public void getAnswer(char[] ops) {
		
		ArrayList<Character> tempOperator = new ArrayList<>(operators);
		ArrayList<Long> tempNumbers = new ArrayList<>(numbers);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<tempOperator.size(); j++) {	
				if(ops[i] == tempOperator.get(j)) {									
					
					// remove 코드에서 j를 두번 호출이유
					tempNumbers.add(j, 
							getOperation(tempNumbers.remove(j), tempNumbers.remove(j), ops[i]));
					
					tempOperator.remove(j);
					j--;
					
				}
			}
			
		}
		answer = Math.max(answer, Math.abs(tempNumbers.get(0))); // 최대값
	}
	
	public Long getOperation(Long x, Long y, char op) {
		if(op == '*') {
			return x*y;
		}
		if(op == '+') {
			return x+y;
		}
		if(op == '-') {
			return x-y;
		}
		return (long) 0;
	}
}
