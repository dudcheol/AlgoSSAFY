package kakao;

class ConstructionRacetrack {
    public int solution(int[][] board) {
        int answer = 0;
        int [][] cost = new int [board.length][board.length];
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
            	cost[i][j] = 100000000;
            }
        }
        
        cost[0][0] = 0;
        
        dfs(0, 0, 0, cost, board);
        dfs(0, 0, 1, cost, board);
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
            	System.out.printf("%-11d", cost[i][j]);
            }
            System.out.println();
        }
        
        
        answer = cost[board.length-1][board.length-1];
       
        
        return answer;
    }
    
    
    public void dfs(int x, int y, int direction, int[][] cost, int[][] board) { // direction 0:가로방향, 1:세로방향
    	
    	int dx[] = {0, 1, 0, -1};
    	int dy[] = {1, 0, -1, 0};
    	
    	// 1. 종점에 도착한경우
    	if(x == cost.length-1 && y == cost.length-1) {
    		return;
    	}
    	
    	for(int i=0; i<4; i++) { // i가 0이면 가로 i가 1이면 세로
    		
    		int rx = x+dx[i];
    		int ry = y+dy[i];
    		
    		// System.out.println(i+" : "+rx +", " + ry + " / " + direction);
    		
    		if(rx < board.length && ry < board.length && rx >= 0 && ry >= 0) { // 경주로를 벗어나지 않는 경우
    			if(board[rx][ry] == 0) { // 벽이 아닌 경우
    				if(i%2 == direction) { // 같은 방향인경우
    					
    					int a = cost[rx][ry];
    					int b = cost[x][y]+100;
    					if(a >= b) {
    						cost[rx][ry] = b;
    						dfs(rx, ry, i%2, cost, board);
    					}
    					
    				} else { // 코너인 경우
    					
    					int a = cost[rx][ry];
    					int b = cost[x][y]+600;
    					if(a >= b) {
    						cost[rx][ry] = b;
    						dfs(rx, ry, i%2, cost, board);
    					}
    				}
    			}
    		}
    	}

    }
}