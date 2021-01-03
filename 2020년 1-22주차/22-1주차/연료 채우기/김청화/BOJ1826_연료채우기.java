package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1826_연료채우기 {
	static int N, L, P, cur_distance, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<OilBank> d_order = new PriorityQueue<>(new Comparator<OilBank>() {
			@Override
			public int compare(OilBank o1, OilBank o2) {
				return o1.distance - o2.distance;
			}
		});
		PriorityQueue<OilBank> q_order = new PriorityQueue<>(new Comparator<OilBank>() {
			@Override
			public int compare(OilBank o1, OilBank o2) {
				return o2.quantity -o1.quantity;
			}
		});
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			d_order.add(new OilBank(d, q));
		}
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 마을까지 거리
		P = Integer.parseInt(st.nextToken()); // 원래 연료
		
		while(true) {
			
			int size = d_order.size();
			for (int i = 0; i < size; i++) {

				// 현재 연료로 갈 수 있는 주유소는 q_order에 넣기
				if(P >= d_order.peek().distance - cur_distance) {
					q_order.add(d_order.poll());
				} else
					break;
			}
			
			//  그 중 연료를 가장 많이 넣을 수 있는 주유소 들르기
			P = P - q_order.peek().distance + cur_distance + q_order.peek().quantity; 
			cur_distance = q_order.poll().distance;
			cnt++;
			
			if(cur_distance + P >= L) {
				System.out.println(cnt);
				break;
			}
			
			if(q_order.isEmpty() && d_order.isEmpty()) {
				System.out.println(-1);
				break;
			}
		}
	}
	
	public static class OilBank {
		int distance;
		int quantity;
		
		OilBank(int distance, int quantity){
			this.distance = distance;
			this.quantity = quantity;
		}
	}
}