package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1202 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(stz.nextToken());
		int K=Integer.parseInt(stz.nextToken());
		
		int[][] Jewel=new int[N][2];
		int[] backPack=new int[K];

		for(int i=0;i<N;i++) {
			stz=new StringTokenizer(br.readLine());
			Jewel[i][0]=Integer.parseInt(stz.nextToken());
			Jewel[i][1]=Integer.parseInt(stz.nextToken());
		}
		for(int i=0;i<K;i++) {
			backPack[i]=Integer.parseInt(br.readLine());
		}
		Arrays.parallelSort(backPack);
		Arrays.sort(Jewel,new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]==o2[0]) {
					return o2[1]-o1[1];
				}
				return o1[0]-o2[0];
			}
		});
		
		int index=0;
		long sum=0;
		
		PriorityQueue<Integer> qu=new PriorityQueue<Integer>();
		
		for(int i=0;i<K;i++) {
			while(index<N && Jewel[index][0]<=backPack[i]) {
				qu.add(-Jewel[index][1]);
				index++;
			}
			if(!qu.isEmpty()) {
				sum+=Math.abs(qu.poll());
			}
		}
		System.out.println(sum);
		
	}

}
