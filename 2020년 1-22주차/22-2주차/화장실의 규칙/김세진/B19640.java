import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B19640 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		//설계
		//일단 M개의 큐를 만들어서 차례대로 배분
		//M번 반복해서 제일 앞에 있는 친구들 
		
		int N=Integer.parseInt(stz.nextToken());
		int M=Integer.parseInt(stz.nextToken());
		int K=Integer.parseInt(stz.nextToken());
		Queue<sawon>[] qu=new LinkedList[M];
		
		for(int i=0;i<M;i++) {
			qu[i]=new LinkedList<sawon>();
		}
		
		
		int index=0;
		
		for(int i=0;i<N;i++) {
			if(index>=M) {
				index=0;
			}
			stz=new StringTokenizer(br.readLine());
			
			int a=Integer.parseInt(stz.nextToken());
			int b=Integer.parseInt(stz.nextToken());
			qu[index].add(new sawon(a, b, index++,i));
			
		}

		int count=0;

		PriorityQueue<sawon> pq=new PriorityQueue<sawon>();
		
		for(int i=0;i<M;i++) {
			if(qu[i].isEmpty()) {
				break;
			}
			sawon sawon=qu[i].poll();
			pq.add(sawon);
			
		}
		
		while(!pq.isEmpty()) {
			count++;
			sawon sawon=pq.poll();
			
			if(sawon.K==K) {
				break;
			}
			
			if(qu[sawon.index].isEmpty()) {
				continue;
			}
			
			pq.add(qu[sawon.index].poll());
			
		}
		System.out.println(count-1);
		
	}
	
	
	public static class sawon implements Comparable<sawon>{
		int D;
		int H;
		int index;
		int K;
		
		public sawon(int d, int h, int index,int k) {
			
			D = d;
			H = h;
			K=  k;
			this.index = index;
		}
		
		
		@Override
		public int compareTo(sawon o) {
			
			int WD=o.D-this.D;
			
			
			if(WD==0) {
				
				int H=o.H-this.H;
				
				if(H==0) {
					return this.index-o.index;
				}
				
				return H;
			}
			
			
			return WD;
		}		
	}

}
