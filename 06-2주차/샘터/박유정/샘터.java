import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 샘터 {
	static int N, K, MIDDLE = 100000000;// 인덱스 음수방지
	static int[] chicken;
	static boolean[] built;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 입력값받아오기위함
		StringTokenizer st = new StringTokenizer(br.readLine());// 입력값 받아오기위함
		N = Integer.parseInt(st.nextToken());// 치킨집수
		K = Integer.parseInt(st.nextToken());// 새로지을집수

		chicken = new int[N];// 치킨위치저장
		built = new boolean[200000000];// 집 위치 저장
		Queue<int[]> q=new LinkedList<int[]>();

		st = new StringTokenizer(br.readLine());// 한줄 입력 받아옴
		for (int i = 0; i < N; i++) {
			chicken[i] = Integer.parseInt(st.nextToken()) + MIDDLE;// 치킨위치 입력
			built[chicken[i]] = true;// 치킨집위치 표시
			q.add(new int[] {chicken[i],0});//치킨집 위치,집 지은 갯수  
		}

		int count = 0;// 집지은 갯수
		long happy = 0;// 행복지수 계산
		int[] d = { 1, -1 };
		
		
		label:while(!q.isEmpty()) {
			int[] arr=q.poll();
			int loc=arr[0];
			int dis=arr[1];
			
			for (int i = 0; i < 2; i++) {
				int x=loc+d[i];
				
				if(x<0||x>=built.length||built[x])
					continue;
				
				built[x]=true;
				q.add(new int[] {x,dis+1});
				
				happy+=dis+1;
				count++;
				
				if(count==K)
					break label;
				
			}
		}
		System.out.println(happy);
	}
}
