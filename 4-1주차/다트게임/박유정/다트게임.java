import java.util.*;

public class 다트게임 {
	public int solution(String dartResult) {
		int num = 0;
		int sum = 0;
		Stack<Integer> li = new Stack<>();

		for (int i = 0; i < dartResult.length(); i++) {
			if (Character.isDigit(dartResult.charAt(i))) {
				num = dartResult.charAt(i) - '0';
				if ( Character.isDigit(dartResult.charAt(i + 1))) {//정수두자리수
					num = 10;
					i++;
				}
				li.push(num);
			} else if (dartResult.charAt(i) == 'D') {
				li.push((int) Math.pow(li.pop(), 2));
			} else if (dartResult.charAt(i) == 'T') {
				li.push((int) Math.pow(li.pop(), 3));
			} else if (dartResult.charAt(i) == '*') {
				int a = li.pop() * 2;
				if (!li.isEmpty()) {//첫번재 숫자 아닐경우
					int b = li.pop() * 2;
					li.push(b);// b를 먼저넣어주기 위함
				}
				li.push(a);
			} else if (dartResult.charAt(i) == '#') {
				li.push(li.pop() * -1);
			}
		}
		while (!li.isEmpty()) {
			sum += li.pop();
			System.out.println(sum);
		}
		return sum;
	}

}
