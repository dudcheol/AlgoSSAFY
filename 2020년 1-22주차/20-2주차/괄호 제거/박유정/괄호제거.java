package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class 괄호제거 {
	static ArrayList<int[]> al;
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<String> pq = new PriorityQueue<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		al = new ArrayList<int[]>();
		Stack<Integer> st = new Stack<Integer>();

		for (int i = 0; i < input.length; i++) {//괄호 쌍들을 모두 구함
			if (input[i] == '(') {
				st.push(i);
				input[i] = ' ';
			} else if (input[i] == ')') {
				al.add(new int[] { st.pop(), i });
				input[i] = ' ';
			}
		}
		
		boolean[] check = new boolean[al.size()];
		
		sub(check, input, 0, al.size(), 0, 0);
		
		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void sub(boolean[] check, char[] input, int count, int end, int index, int tcount) {
		if (count == end) {
			if (tcount == end)// 모두 true일때는 pass
				return;
			char[] incopy = input.clone();

			for (int i = 0; i < check.length; i++) {
				if (check[i]) {
					int[] pair = al.get(i);
					incopy[pair[0]] = '(';
					incopy[pair[1]] = ')';
				}
			}
			for (int i = 0; i < incopy.length; i++) {
				if (incopy[i] != ' ') {
					sb.append(incopy[i]);
				}
			}
			if (!pq.contains(sb.toString())) {//중복괄호면 겹칠수 잇음((1))
				pq.add(sb.toString());
			}
			sb.delete(0, sb.length());
			return;
		}
		check[index] = true;
		sub(check, input, count + 1, end, index + 1, tcount + 1);
		check[index] = false;
		sub(check, input, count + 1, end, index + 1, tcount);

	}

}
