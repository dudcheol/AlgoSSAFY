package simulation;
import java.util.LinkedList;

public class Cache {

	public static void main(String[] args) {
		System.out.println(solution(3,	new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
		System.out.println(solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
		System.out.println(solution(2, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));

	}
    static public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize==0) {
        	return 5*cities.length;
        }
        
        
        LinkedList<String> cache=new LinkedList<String>();
        
        for(int i=0;i<cities.length;i++) {
        	String city=cities[i].toLowerCase();
        	
        	//hit
        	if(cache.remove(city)) {
        		cache.addFirst(city);
        		answer+=1;
        	}
        	
        	//miss
        	else {
        		int size=cache.size();
        		
        		if(size==cacheSize) {
        			cache.pollLast();
        		}
        		
        		cache.addFirst(city);
        		answer+=5;
        	}
        	
        	
        }
        
        
        return answer;
    }
}
