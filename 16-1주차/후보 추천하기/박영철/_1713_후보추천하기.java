package week16;

import java.util.*;
import java.io.*;

public class _1713_후보추천하기 {

	private static int N;
	private static int M;

	private static class Picture implements Comparable<Picture> {
		int num;
		int recommand;
		int time;

		public Picture(int num, int recommand, int time) {
			this.num = num;
			this.recommand = recommand;
			this.time = time;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Integer) {
				return (Integer)obj == this.num;
			}
			return false;
		}

		@Override
		public int compareTo(Picture o) {
			int ret = Integer.compare(this.recommand, o.recommand);
			if(ret == 0) ret = Integer.compare(this.time, o.time);
			return ret;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
//		PriorityQueue<Picture> pq = new PriorityQueue<Picture>((o1,o2)->{
//			int ret = Integer.compare(o1.recommand, o2.recommand);
//			if(ret == 0) ret = Integer.compare(o1.time, o2.time);
//			return ret;
//		});
		ArrayList<Picture> list = new ArrayList<Picture>();
		
		int time = 0;
		loop: for (int i = 0; i < M; i++) {
			Collections.sort(list);
			
			int num = Integer.parseInt(st.nextToken());
			
			for (Picture p : list) {
				if(p.num == num) {
					p.recommand++;
					continue loop;
				}
			}
			
			if(list.size() < N) {
				list.add(new Picture(num, 1, time++));
			} else {
				list.remove(0);
				list.add(new Picture(num, 1, time++));
			}
		}
		
		Collections.sort(list, (o1,o2)->{
			return Integer.compare(o1.num, o2.num);
		});
		
		StringBuilder sb = new StringBuilder();
		for (Picture p : list) {
			sb.append(p.num).append(' ');
		}
		
		System.out.print(sb);
	}

}
