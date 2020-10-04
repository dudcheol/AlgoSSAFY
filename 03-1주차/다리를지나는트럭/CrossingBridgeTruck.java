package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class CrossingBridgeTruck {

	// 고려해야 하는 것 
	// 1. 시간
	// 2. 무게		
	static int bridge_length = 2;
	static int weight = 10;
	static int[] truck_weights = {7, 4, 5, 6};
//	static int bridge_length = 100;
//	static int weight = 100;
//	static int[] truck_weights = {10};

	
	public static void main(String[] args) {

		
		int index = 1;
		int sum = 0; // 다리 위 트럭 무게
		int time = 1;
		
		Queue<Truck> truck = new LinkedList<Truck>();
		Queue<Truck> weight_sum = new LinkedList<Truck>(); // 현재 다리 위에 있는 트럭 무게
		

		
		for (int i = 0; i < truck_weights.length; i++) { // 큐에 트럭을 저장
			truck.add(new Truck(0, truck_weights[i]));
		}
		
		
		weight_sum.offer(truck.poll());
		sum = weight_sum.peek().weight;
		
		while(!weight_sum.isEmpty()) {	
			for (Truck t : weight_sum) { // 현재 다리 위에 있는 트럭의 시간을 다 ++
				t.time++;
			}

			if(weight_sum.peek().time == bridge_length) { // 다리 위 트럭이 다리 길이와 같아지면 poll
				sum -= weight_sum.poll().weight;
			}			
			
			if(!truck.isEmpty() && sum + truck.peek().weight <= weight) { // 새로운 트럭과 여태까지 트럭 합이 weight보다 작으면(앞트럭 시간이 2일때도 고려해야되나)
				sum += truck.peek().weight;
				weight_sum.add(truck.poll());
			}

			time++;
		}
		
		System.out.println(time);
		
	}
	
	static class Truck{
		int time;
		int weight;
		
		public Truck(int time, int weight) {
			super();
			this.time = time;
			this.weight = weight;
		}
	}

}
