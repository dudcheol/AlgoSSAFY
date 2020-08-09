package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PermAndComb {
    static int n, r, cmd = -1;
    static boolean[] visited;
    static int[] nums;
    static int cnt;

    public static void main(String[] args) throws Exception {
        while (cmd != 0) {
            System.out.println("n r cmd 입력");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            cmd = Integer.parseInt(st.nextToken());

            visited = new boolean[n + 1];
            nums = new int[r];
            cnt = 0;

            switch (cmd) {
                case 1:// 순열 : 서로 다른 n개 중에서 중복을 제외한 r개를 순서를 고려하여 나열
                    perm(0);
                    break;
                case 2:// 중복순열 : 서로 다른 n개 중에서 중복을 포함한 r개를 순서를 고려하여 나열
                    duplPerm(0);
                    break;
                case 3:// 조합 : 서로 다른 n개 중에서 r개를 순서에 상관없이 나열
                    comb(0, 1);
                    break;
                case 4:// 중복조합 : 서로 다른 n개 중에서 동일한 것의 중복을 인정하고 r개 순서에 상관없이 나열
                    duplComb(0, 1);
                    break;
                default:
            }

            System.out.println("경우의 수는 " + cnt + "개 입니다.");
        }
    }

    static void perm(int k) {
        if (k == r) {
            cnt++;
            System.out.println(Arrays.toString(nums));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            nums[k] = i;
            perm(k + 1);
            visited[i] = false;
        }
    }

    static void duplPerm(int k) {
        if (k == r) {
            cnt++;
            System.out.println(Arrays.toString(nums));
            return;
        }
        for (int i = 1; i <= n; i++) {
            nums[k] = i;
            duplPerm(k + 1);
        }
    }

    static void comb(int k, int idx) {
        if (k == r) {
            cnt++;
            System.out.println(Arrays.toString(nums));
            return;
        }
        for (int i = idx; i <= n; i++) {
            nums[k] = i;
            comb(k + 1, i + 1);
        }
    }

    static void duplComb(int k, int idx) {
        if (k == r) {
            cnt++;
            System.out.println(Arrays.toString(nums));
            return;
        }
        for (int i = idx; i <= n; i++) {
            nums[k] = i;
            duplComb(k + 1, i);
        }
    }
}
