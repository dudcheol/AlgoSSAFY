package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실배정 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Lecture> q = new PriorityQueue<Lecture>();
		int arr[][] = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1])
					return o1[0]-o2[0];
				return o1[1] - o2[1];
			}
		});
		for (int i = 0; i < N; i++) {
			int s=arr[i][0];
			int e=arr[i][1];
			
			if (!q.isEmpty() && q.peek().end <= s) {
				q.poll();
			} 
			q.add(new Lecture(s, e));
		}
		System.out.println(q.size());
	}

}

class Lecture implements Comparable<Lecture> {
	int start;
	int end;

	public Lecture(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Lecture o) {
		// TODO Auto-generated method stub
		return this.end - o.end;
	}

}
