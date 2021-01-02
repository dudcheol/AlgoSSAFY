package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 화장실의규칙 {
	static int n,m,k;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		Queue<int[]>[] q=new LinkedList[m];//각 줄마다 q에 넣어줌
		for (int i = 0; i < m; i++) {
			q[i]=new LinkedList<int[]>();
		}
		
		PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					if(o1[2]==o2[2]) {
						return o1[0]%m-o2[0]%m;
					}
					return o2[2]-o1[2];
				}
				return o2[1]-o1[1];
			}});//선두주자 정렬
		
		
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			int d1=Integer.parseInt(st.nextToken());
			int h1=Integer.parseInt(st.nextToken());
			q[i%m].add(new int[] {i,d1,h1});
		}//각 줄에 서있는 사람들 
		
		for (int i = 0; i < m&&!q[i].isEmpty(); i++) {
			pq.add(q[i].poll());//선두 주자 담기
		}
		
		int answer=0;
		
		while(!pq.isEmpty()) {
			int[] per=pq.poll();
			
			if(per[0]==k)
				break;
			if(!q[per[0]%m].isEmpty())
				pq.add(q[per[0]%m].poll());
			
			answer++;
		}
		System.out.println(answer);
	}

}
