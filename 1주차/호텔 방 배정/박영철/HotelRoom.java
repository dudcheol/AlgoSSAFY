package week1;

import java.util.*;

public class HotelRoom {
    static HashMap<Long, Long> map = new HashMap<>();

    static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            long rn = room_number[i];
            if (!map.containsKey(rn)) {
                // 방이 존재하지 않는다면
                map.put(rn, rn + 1);
                answer[i] = rn;
                continue;
            }
            // 방이 존재한다면
            answer[i] = union(rn);
        }

        return answer;
    }

    static long union(long rn) {
        // 기저
        if (!map.containsKey(rn)) {
            // rn이 빈방이라면
            map.put(rn, rn + 1);
            return rn;
        }

        long ret;
        map.put(rn, ret = union(map.get(rn)));
        return ret;
    }

    public static void main(String[] args) {
        long k = 10;
        long[] room_number = {1, 3, 4, 1, 3, 1};

        System.out.println(Arrays.toString(solution(k, room_number)));
    }
}
