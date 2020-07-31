package kakao;

class ConstructionRacetrack {
	
	private static int[][] cost;
	private static int[][] boardArr;
	private static int dx[] = {0, 1, 0, -1};
	private static int dy[] = {1, 0, -1, 0};
	
    public int solution(int[][] board) {
        int answer = 0;
        cost = new int [board.length][board.length];
        boardArr = board;
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
            	cost[i][j] = Integer.MAX_VALUE;
            }
        }
        
        cost[0][0] = 0;
        
        dfs(0, 0, 0);
        dfs(0, 0, 1);
        
        answer = cost[board.length-1][board.length-1];
       
        return answer;
    }
    
    
    public void dfs(int x, int y, int direction) {     	    
    	
    	if(x == cost.length-1 && y == cost.length-1) {
    		return;
    	}
    	
    	for(int i=0; i<4; i++) { 
    		
    		int rx = x+dx[i];
    		int ry = y+dy[i];
    		
    		if(rx < boardArr.length && ry < boardArr.length && rx >= 0 && ry >= 0) { 
    			if(boardArr[rx][ry] == 0) {
    				
    				int a = cost[rx][ry];
    				int b = (i%2 == direction) ? cost[x][y]+100 : cost[x][y]+600;
    				
    				if(a >= b) {
						cost[rx][ry] = b;
						dfs(rx, ry, i%2);
					}    				
    			}
    		}
    	}

    }
}