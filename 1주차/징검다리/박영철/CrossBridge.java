package week1;

public class CrossBridge {
    static int[] stone;
    static int size;
    static int K;

    static int solution(int[] stones, int k) {
        stone = stones;
        size = stones.length;
        K = k;

        // 최댓값과 최솟값 구하기
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            max = Math.max(max, stone[i]);
            min = Math.min(min, stone[i]);
        }

        return crossBridge(min, max);
    }

    static int crossBridge(int min, int max) {
        if (min >= max) return min;

        // 이분탐색을 위한 중간값 찾기
        int mid = (max + min) / 2;

        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (stone[i] - mid <= 0) {
                // 더이상 건널 수 없는 다리
                cnt++;
                if (cnt == K) {
                    // 건널 수 없는 다리면 건널 인원 줄여보기
                    return crossBridge(min, mid - 1);
                }
            } else cnt = 0;
        }
        // 건널 수 있는 다리이면 건널 인원 늘려보기
        return crossBridge(mid + 1, max);
    }

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
//        int[] stones = {2, 4, 5, 4, 3, 2, 1, 9, 5, 1};
        int k = 3;

        System.out.println(solution(stones, k));
    }
}
