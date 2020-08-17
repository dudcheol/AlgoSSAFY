package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Cache {

	public static void main(String[] args) {
		Cache cache = new Cache();
		String[] cities = {
			"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"
		};
		cache.solution(3, cities );

	}
	
	private static final int CACHE_MISS = 5;
	private static final int CACHE_HIT = 1;

	public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> cache = new LinkedList<>();
        
        for(int i=0; i<cities.length; i++) {
        	
        	String city = cities[i].toUpperCase();
        	
        	// cache에 존재하는지 확인
        	if(cache.contains(city)) {
        		answer += CACHE_HIT;
        		cache.remove(city);
        		cache.add(city);
        	} else {
        		answer += CACHE_MISS;
        		cache.add(city);
        	}
        	
        	if(cache.size() > cacheSize) {
        		cache.poll();
        	}
        }
        return answer;
    }
}
