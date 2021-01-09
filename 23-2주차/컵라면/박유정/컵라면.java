package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 컵라면 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {//데드라인빠른순, 컵라면 많은순
				if (o1[0] == o2[0]) {
					return o2[1] - o1[1];
				}
				return o1[0] - o2[0];
			}
		});
	

		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(arr[0][1]);//첫번쨰것 넣어줌
		int time = 1;//현재시각
		
		for (int i = 1; i < N; i++) {
			
			if (arr[i][0] > time) {//데드라인마감 안된것(현재시각 보다 큰것)
				int ra = arr[i][1];
				time++;
				q.add(ra);
				
				for (int j = i + 1; j < N; j++) {
					
					if (arr[j][0] == arr[i][0]) {//같은 데드라인일경우
						if(time<arr[i][0]) {//데드라인이 아직 남았을경우
							q.add(arr[j][1]);//그냥 넣어줌
							time++;
						}
						else if (q.peek() < arr[j][1]) {//데드라인이 마감되었고, 같은데드라인중에 컵라면이 더 이득인것
							q.poll();//옛날꺼뺴고
							q.add(arr[j][1]);//현재꺼로 대신
						} else {	
							i=j-1;
							break;
						}
					} else {
						i=j-1;
						break;
					}
				}
				
				
				
			}
		}
		int sum = 0;
		for (int n : q) {
			sum += n;
		}
		System.out.println(sum);
	}
}
