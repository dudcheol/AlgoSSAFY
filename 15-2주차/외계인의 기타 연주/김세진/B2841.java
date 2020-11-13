import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class B2841 {
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int P = Integer.parseInt(stz.nextToken());

		// stack 6개 쓰는 문제 -> 좀 더 쉽게 푸는 방법없나?
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		Stack<Integer> stack3 = new Stack<Integer>();
		Stack<Integer> stack4 = new Stack<Integer>();
		Stack<Integer> stack5 = new Stack<Integer>();
		Stack<Integer> stack6 = new Stack<Integer>();

		count = 0;

		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(br.readLine());
			int lineNum = Integer.parseInt(stz.nextToken());
			int pNum = Integer.parseInt(stz.nextToken());

			switch (lineNum) {
			case 1:
				stackeble(stack1, pNum);
				break;
			case 2:
				stackeble(stack2, pNum);
				break;
			case 3:
				stackeble(stack3, pNum);
				break;
			case 4:
				stackeble(stack4, pNum);
				break;
			case 5:
				stackeble(stack5, pNum);
				break;
			case 6:
				stackeble(stack6, pNum);
				break;

			}

		}
		System.out.println(count);
	}

	public static void stackeble(Stack<Integer> stack, int pNum) {
		if (stack.isEmpty()) {
			stack.add(pNum);
			count++;
		} else {
			int max = stack.peek();
			// 들어가있는 최대값이 pNum보다 작으면
			if (max < pNum) {

				stack.add(pNum);

				count++;
			}
			// 크면
			else if (max > pNum) {
				// top이 지금 값보다 작아질때까지
				if (stack.size() > 1) {
					while (stack.peek() > pNum) {
						stack.pop();
						count++;
						
						if(stack.isEmpty()) {
							break;
						}
					}

					if (stack.isEmpty()||stack.peek() != pNum) {
						stack.add(pNum);
						count++;
					}

				}else {
					stack.pop();
					count+=2;
					stack.add(pNum);
				}
			}

		}
	}
}
