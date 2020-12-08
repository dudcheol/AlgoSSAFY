package data_structure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B2014 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(stz.nextToken());

		PriorityQueue<Long> pq = new PriorityQueue<Long>();

		long[] arr = new long[K];
		stz = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++) {
			arr[i] = Long.parseLong(stz.nextToken());
			pq.add(arr[i]);
		}

		long solve = 0;

		while (N-- > 0) {
			solve = pq.poll();

			for (int i = 0; i < K; i++) {

				pq.add(solve * arr[i]);
				if (solve % arr[i] == 0) {
					break;
				}
			}
		}

		System.out.println(solve);
	}

}
