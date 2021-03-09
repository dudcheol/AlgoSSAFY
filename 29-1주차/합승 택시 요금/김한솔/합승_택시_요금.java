/**
 * programmers - 2021 KAKAO BLIND RECRUITMENT. 합승_택시_요금
 * 합승_택시_요금.java
 * @date 2021-03-09
 * @author hansolKim
 **/

package programmers;

public class 합승_택시_요금 {

	public static void main(String[] args) {
		합승_택시_요금 합승_택시_요금 = new 합승_택시_요금();
		// int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
		합승_택시_요금.solution(7, 3, 4, 1, fares);

	}
	
	static final int INF = 100000000;
	
	public int solution(int n, int s, int a, int b, int[][] fares) {        
        
        int[][] graph = new int[n+1][n+1];
        
        for(int i=1; i<=n; i++) {
        	for(int j=1; j<=n; j++) {
        		if(i == j) { 
        			graph[i][j] = 0;
        			continue;
        		}
            	graph[i][j] = INF;
            }
        }
        
        for(int i=0; i<fares.length; i++) {
        	graph[fares[i][0]][fares[i][1]] = fares[i][2];
        	graph[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        for(int k=1; k<=n; ++k) {
        	for(int i=1; i<=n; ++i) {
        		for(int j=1; j<=n; ++j) {
                	if(graph[i][k] + graph[k][j] < graph[i][j]) {
                		graph[i][j] = graph[i][k] + graph[k][j];
                	}
                }
            }        	
        }
        
        int answer = INF;
        for(int i=1; i<=n; ++i) {
        	// System.out.println(i + ":" + graph[s][i] + " " + graph[i][a] + " " + graph[i][b]);
        	answer = Math.min(answer, graph[s][i] + graph[i][a] + graph[i][b]);
        }
        
        // System.out.println(answer);
        return answer;
    }

}
