import java.util.ArrayList;
import java.util.List;

public class 수식최대화 {
	class Solution {
		public long solution(String expression) {
			long answer = 0;
			List<Character> li = new ArrayList<>();

			int start = 0;
			char[] log = { '*', '+', '-' };

			for (int i = 0; i < expression.length(); i++) {
				if (!Character.isDigit(expression.charAt(i))) {
					li.add((char) (Integer.parseInt((expression.substring(start, i)))));
					li.add(expression.charAt(i));
					start = i + 1;
				}
			}

			li.add((char) (Integer.parseInt((expression.substring(start, expression.length())))));
			int size = li.size();
			List<Character> licopy = new ArrayList<>(li);

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (j == i)
						continue;
					for (int k = 0; k < 3; k++) {
						if (k == i || k == j)
							continue;

						for (int m = 0; m < log.length; m++) {
							if (log[i] == licopy.get(i)) {
								long x = licopy.get(i - 1) - '0';
								long y = licopy.get(i + 1) - '0';
								long res=cal(x, y, licopy.get(i));
								licopy.remove(i+1);
								licopy.remove(i);
								licopy.remove(i-1);
								licopy.add((char) (res+'0'));
							}

						}
					}
				}
			}
			return answer;
		}

		public long cal(long x, long y, char log) {
			long ans = 0;
			switch (log) {
			case '*':
				ans = x * y;
				break;
			case '-':
				ans = x - y;
				break;
			case '+':
				ans = x + y;
				break;
			}
			return ans;
		}
	}

}
