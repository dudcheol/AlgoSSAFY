import java.util.*;

public class 캐시 {
	  public int solution(int cacheSize, String[] cities) {
	        int answer = 0;
	        Queue<String> q=new LinkedList<String>();
	        
	        for (int i = 0; i < cities.length; i++) {
	            String city=cities[i].toUpperCase();
				if(!q.contains(city)) {
					if(q.size()==cacheSize&&!q.isEmpty()) {
						q.poll();
					}
					if(cacheSize!=0)
	                    q.add(city);
					answer+=5;
				}
				else {
					q.remove(city);
					q.add(city);
					answer+=1;
				}
			}
	        return answer;
	    }

}
