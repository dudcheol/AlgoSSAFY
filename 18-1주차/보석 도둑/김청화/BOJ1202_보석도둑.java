package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1202_보석도둑 {
	static int N, K;
	static long answer; // long!!
	static ArrayList<Integer> bags = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Jewelry[] jewelries = new Jewelry[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			jewelries[i] = new Jewelry(weight, price);
		}
		
		Arrays.sort(jewelries, new Comparator<Jewelry>(){ // 중복 안 되게 하기 위해서
			@Override
			public int compare(Jewelry o1, Jewelry o2) {
				if(o1.weight != o2.weight)
					return o1.weight - o2.weight;
				else {
					return o2.price - o1.price;
				}
			}
		});
		
		for (int i = 0; i < K; i++) {
			int bag = Integer.parseInt(br.readLine());
			bags.add(bag);
		}
		Collections.sort(bags);
		
		// 가격 내림차순
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		int k = 0;
		for (int i = 0; i < K; i++) {
			while(k < N) {
				// 보석이 무게 순으로 되어 있으니까 한 번 넣으면 그 뒤로는 pq에 넣어도 될 지 검사 안 해도 됨
				if (jewelries[k].weight <= bags.get(i)) { 
					pq.offer(jewelries[k].price);
					k++;
				} else 
					break;
			}

            // 가격 젤 높은 거 가방에 넣음 
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
		
		System.out.println(answer);
	}
	
	static class Jewelry{
		int weight;
		int price;
		
		Jewelry(int weight, int price){
			this.weight = weight;
			this.price = price;
		}
	}

}
