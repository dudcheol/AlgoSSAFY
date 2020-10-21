package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 순위2 {
	static int[][] weights;
	static ArrayList<ArrayList<Integer>> loserlist = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> winnerlist = new ArrayList<>();
	
	static int solution(int n, int[][] results) {
        int answer = 0;
        
        // 연결리스트 초기화
        for (int i = 0; i <= n; i++) {
			loserlist.add(new ArrayList<Integer>());
			winnerlist.add(new ArrayList<Integer>());
		}

        for (int i = 0; i < results.length; i++) {
        	int winner = results[i][0];
        	int loser = results[i][1];
        	
        	winnerlist.get(winner).add(loser);
        	loserlist.get(loser).add(winner);
        }
        
        weights = new int[n + 1][n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(results[0][0]); // 시작점 넣기
        
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	
        	// cur이 1이 된 적이 없어서 그래
        	
        	for (int i = 0; i < winnerlist.get(cur).size(); i++) {
				int cur_loser = winnerlist.get(cur).get(i);
				
				weights[cur][cur_loser] = 1;
				weights[cur_loser][cur] = -1;
				q.add(cur_loser);
        		
				// cur_loser는 cur을 이긴 사람한테도 짐
				// loserlist.get(cur) 은 cur을 이긴 애들 목록
				for (int j = 0; j < loserlist.get(cur).size(); j++) {
//					if(cur == 2)
//						System.out.println(loserlist.get(cur).get(j));
					int tot_win = loserlist.get(cur).get(j);
					weights[tot_win][cur_loser] = 1;
					weights[cur_loser][tot_win] = -1;
				}

				// cur_loser에게 진 사람은 cur에게도 짐
				// winnerlist.get(cur_loser)은 cur_loser한테 진 사람 목록
				for (int j = 0; j < winnerlist.get(cur_loser).size(); j++) {
					int tot_lose = winnerlist.get(cur_loser).get(j);
					weights[cur][tot_lose] = 1;
					weights[tot_lose][cur] = -1;
				}
				
				System.out.println(cur);
				print(n);
			}
        }
        
//        
        for (int i = 0; i < results.length; i++) {
        	int winner = results[i][0];
        	int loser = results[i][1];
        	
        	weights[winner][loser] = 1; // results[i][0]: 이긴 사람
        	weights[loser][winner] = -1; // results[i][1]: 진 사람
        	
        	for (int j = 0; j < results.length; j++) {
        		// 현재 이긴 사람이 누군가에게 지면, 현재 진 사람은 탐색 중 이긴 사람한테도 짐
				int cur_winner = results[j][0];
				int cur_loser = results[j][1];
				
        		if(cur_loser == winner) {
					weights[cur_winner][loser] = 1;
					weights[loser][cur_winner] = -1;
					for (int k = 0; k < results.length; k++) {
						
					}
				} 
				
				// 현재 진 사람이 누군가에게 이기면, 현재 이긴 사람은 탐색 중 진 사람도 이김
				// (1, 2) ---> (2, 5)
				if(cur_winner == loser) {
					weights[winner][cur_loser] = 1;
					weights[cur_loser][winner] = -1;
				}
        	}
        	
		}
        
        // 0이 1개면 확실한 등수 알 수 있음
        for (int i = 1; i < n + 1; i++) {
        	int cnt = 0;
			for (int j = 1; j < n + 1; j++) {
				if(weights[j][i] == 0)
					cnt++;
			}
			if(cnt == 1) {
				answer++;
			}
		}

        return answer;
    }

	private static void print(int n) {
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				System.out.print(weights[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n\n=======================================");
	}

	public static void main(String[] args) {
		int n = 5;
		int[][] results = { {4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5} };
		
//		int n = 5;
//		int[][] results = { {1, 2}, {2, 3}, {3, 4}, {4, 5} };
		
		System.out.println(solution(n, results));
	}

}
