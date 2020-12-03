package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 친구만들기 {
	static HashMap<String, String> hm;
	static HashMap<String, Integer> ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());

		for (int i = 0; i < test; i++) {
			int n = Integer.parseInt(br.readLine());
			hm = new HashMap<String, String>();
			ans = new HashMap<String, Integer>();
			
			for (int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				if (!ans.containsKey(a)) {
					ans.put(a, 1);
					hm.put(a, a);
				}
				if (!ans.containsKey(b)) {
					ans.put(b, 1);
					hm.put(b, b);
				}
				int answer = union(a, b);
				System.out.println(answer);
			}
		}
	}

	private static int union(String a, String b) {
		String pa = find(a);
		String pb = find(b);

		if (!pa.equals(pb)) {
			int sum = ans.get(pa) + ans.get(pb);
			ans.put(pa, sum);
			hm.put(pb, pa);
			return sum;
		}
		return ans.get(pa);
	}

	private static String find(String a) {
		Queue<String> q = new LinkedList<String>();

		while (true) {
			String pa = hm.get(a);

			if (pa.equals(a)) {
				for (String s : q) {
					hm.put(s, pa);
				}
				return pa;
			}
			q.add(a);
			a = pa;
		}
	}
}
