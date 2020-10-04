package week4;

import java.util.Iterator;
import java.util.LinkedList;

public class Cache {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        int idx = 0;

        LinkedList<String> cache = new LinkedList<>();

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        while (idx < cities.length) {
            String target = cities[idx++].toLowerCase();

            // 캐시에 target이 있는지 확인
            boolean find = false;
            for (Iterator<String> iterator = cache.iterator(); iterator.hasNext(); ) {
                String c = iterator.next();
                if (c.equals(target)) {
                    iterator.remove();
                    cache.offer(c);
                    find = true;
                    break;
                }
            }

            // 캐시에 target이 없었다면
            if (!find) {
                // 캐시에 아직 빈자리가 있다면
                if (cache.size() < cacheSize) {
                    cache.offer(target);
                } else {
                    // 캐시가 가득 차 있다면 가장 나중에 저장한 도시를 지우고 target을 추가한다
                    cache.poll();
                    cache.offer(target);
                }
                answer += 5;
            } else answer += 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        Cache cache = new Cache();

        // return 50
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        String[] cities2 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        String[] cities3 = {"Jeju", "Jeju", "Jeju", "Jeju", "Jeju", "Jeju", "seoul"};

        System.out.println(cache.solution(
                0, cities3
        ));
    }
}
