package graph;

public class 순위 {

	public static void main(String[] args) {
		
		
		System.out.println(solution(5, new int[][] {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
//		System.out.println(solution(3, new int[][] {{1, 2}, {1, 3},{2,3}}));
	}
    static public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] opgg=new int[n+1][n+1]; 
        
        
        //100명으로 한계를 지은 이유 = 완탐을 할 수 있게 하려고
        
        //재경기는 없다 -> 모순되기때문
        for(int i=0;i<results.length;i++) {
        	
        	//승자 1
        	opgg[results[i][0]][results[i][1]]=1;
        	
        	//패자 -1
        	opgg[results[i][1]][results[i][0]]=-1;
        	
        	
        }
        //null은 0이다.
        
//        printA(n,opgg);
        
        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=n;j++) {
        		//패배 전적이 있다면
        		if(opgg[i][j]==-1) {
        			for(int k=1;k<=n;k++) {
        				//내가 이긴 친구를 찾아 
        				if(opgg[i][k]==1) {
        					
        					//전적을 남겨줌
        					opgg[k][j]=-1;
        					opgg[j][k]=1;
        				}
        			}
        		}
        	}
        }
//        printA(n,opgg);
        
        
        for(int i=1;i<=n;i++) {
        	int count=0;
        	for(int j=1;j<=n;j++) {
        		if(count>1) {
        			break;
        		}
        		if(opgg[i][j]==0) {
        			count++;
        		}
        		
        	}
        	if(count==1) {
        		answer++;
        	}
        }
        
        return answer;
    }
    
    static public void printA(int n,int[][] arr) {
    	for(int i=1;i<=n;i++) {
    		for(int j=1;j<=n;j++) {
    			System.out.print(arr[i][j]+" ");
    		}
    		System.out.println();
    	}
    }
}
