/**
 * 2020 kakao internship
 * 동굴탐험
 * CaveExploration.java
 * @date 2020-08-05
 * @author hansolKim
 * */


package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CaveExploration {

	private static boolean[] isVisited;
	private static HashMap<Integer, ArrayList<Integer>> map;
	private static HashMap<Integer, Integer> orderlist;
	private static int cycle;
	
	public static void main(String[] args) {
		CaveExploration ce = new CaveExploration();
		int[][] path = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
		int[][] order = {{8,5},{6,7},{4,1}};
		System.out.println(ce.solution(9, path, order));
		

	}
	
	public boolean solution(int n, int[][] path, int[][] order) {
        
        isVisited = new boolean[n]; // n개만큼 방문배열 생성
        map = new HashMap<>();
        orderlist = new HashMap<>();
        
        for(int i=0; i<path.length; i++) {
        	for(int j=0; j<2; j++) {
        		ArrayList<Integer> newList;
        		
        		if(map.containsKey(path[i][j])) { // 이미 해당 노드가 존재하는 경우
        			newList = map.get(path[i][j]); // new ArrayList<>(map.get(path[i][j])); 에서 변경(효율성 통과)        			
        		} else { // 존재하지 않는 경우 
        			newList = new ArrayList<>();
        		}
        		
        		newList.add(path[i][1-j]);
        		map.put(path[i][j], newList);
            }
        }
        
        for(int i=0; i<order.length; i++) {
        	orderlist.put(order[i][1], order[i][0]);
        }                
        
        return bfs(0);
    }
	
	public boolean bfs(int start) {
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(start);
		isVisited[start] = true;
		
		while(!queue.isEmpty()) {
			
			if(cycle == queue.size()) { return false; } // 반복 순회중인 경우
			
			int roomNumber = queue.poll();
			
			if(orderlist.containsKey(roomNumber)) {
				if(!isVisited[orderlist.get(roomNumber)]) { // 1. 방을 방문할 수 없는 경우-> 큐에서 꺼낸 후 다시 삽입
					queue.add(roomNumber);
					cycle++;
					continue;
				}
			}
			
			// 2. 방을 방문할 수 있는 경우 : 제약조건이 없거나 제약조건에 해당하는 방을 통과한 경우
			ArrayList<Integer> roomList = map.get(roomNumber);
			isVisited[roomNumber] = true;
			cycle = 0;
			
			// 해당 방에서 방문할 수 있는 방 중에서 방문한 방이 아닌 경우 삽입
			for(int i=0; i<roomList.size(); i++) {				
				if(!isVisited[roomList.get(i)]) {
					queue.addFirst(roomList.get(i));
				} 
			}

		}
		
		return true;
	}

}
