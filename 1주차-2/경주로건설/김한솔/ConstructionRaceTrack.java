package programmers;

class ConstructionRaceTrack {
	
	private static int[][] cost;
	private static int[][] track;
	private static int dx[] = {0, 1, 0, -1}; // 우, 하, 좌, 상
	private static int dy[] = {1, 0, -1, 0};
		
	public static void main(String[] args) {
		
		int [][] board = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},
				{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
		
		solution(board);
	}
	
    public static int solution(int[][] board) {
        int answer = 0;
        cost = new int [board.length][board.length];
        track = board;
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
            	cost[i][j] = Integer.MAX_VALUE;
            }
        }
        
        cost[0][0] = 0;
        
        dfs(0, 0, 0); // 우측 탐색 시작
        dfs(0, 0, 1); // 아랫쪽 탐색 시작
        
/*	    for(int i=0; i<board.length; i++) {
	    	for(int j=0; j<board.length; j++) {
	    		System.out.printf("%-11d", cost[i][j]);
	    	}
	    	System.out.println();
	    }*/
        
        answer = cost[board.length-1][board.length-1];
       
        return answer;
    }
    
    
    public static void dfs(int x, int y, int direction) {     	    
    	
    	if(x == cost.length-1 && y == cost.length-1) {
    		return;
    	}
    	
    	for(int i=0; i<4; i++) { 
    		
    		int rx = x+dx[i];
    		int ry = y+dy[i];
    		
    		if(isInTrack(rx, ry) && !isWall(rx, ry)) { // 경주로 트랙 안에 존재하고 벽이 아닐때
    				
				int a = cost[rx][ry];
				int b = (i%2 == direction) ? cost[x][y]+100 : cost[x][y]+600;
				
				if(a >= b) {
					cost[rx][ry] = b;
					dfs(rx, ry, i%2);
				}    				
    		}
    	}
    }
    
    private static boolean isInTrack(int x, int y) {
    	return (x < track.length && y < track.length && x >= 0 && y >= 0) ? true : false;
    }
    
    private static boolean isWall(int x, int y) {
    	return (track[x][y] == 1) ? true : false;
    }
}