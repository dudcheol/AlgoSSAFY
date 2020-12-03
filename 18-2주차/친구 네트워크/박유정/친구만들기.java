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
			hm = new HashMap<String, String>();//부모담는 hm
			ans = new HashMap<String, Integer>();//친구 수 담는 hm
			
			for (int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				if (!ans.containsKey(a)) {//hm에 없을경우에는 
					ans.put(a, 1);//자기자신밖에없으니까 친구1명
					hm.put(a, a);//부모도 자기자신
				}
				if (!ans.containsKey(b)) {
					ans.put(b, 1);
					hm.put(b, b);
				}
				int answer = union(a, b);//부모합치기
				System.out.println(answer);
			}
		}
	}

	private static int union(String a, String b) {
		String pa = find(a);
		String pb = find(b);

		if (!pa.equals(pb)) {//부모가 다를경우
			int sum = ans.get(pa) + ans.get(pb);//두 부모의 친구 합쳐줌
			ans.put(pa, sum);//친구수 업데이트
			hm.put(pb, pa);//부모합쳐줌
			return sum;
		}
		return ans.get(pa);
	}

	private static String find(String a) {
		Queue<String> q = new LinkedList<String>();

		while (true) {
			String pa = hm.get(a);

			if (pa.equals(a)) {
				for (String s : q) {//큐에있는 부모들 하나로 합쳐주기
					hm.put(s, pa);
				}
				return pa;
			}
			q.add(a);//중간 경로 큐에담아서 부모하나로 합쳐주기
			a = pa;
		}
	}
}
