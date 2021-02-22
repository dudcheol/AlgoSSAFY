package algoSSAFY.week27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13908_비밀번호 {
    private static int n,m;
    private static int[] nums, selected;
    private static int answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums= new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        selected = new int[n];
        perm(0);
        System.out.println(answer);
    }

    private static void perm(int k) {
        // 가지치기
        if(n-k < m){
            int contains=0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (nums[i] == selected[j]) {
                        contains++;
                        break;
                    }
                }
            }
            if(contains<m) { //포함된 수가 m보다 적고
                if (n - k < m-contains) return; // 남은 수의 갯수가 더 필요한 수의 갯수보다 적으면 더 볼 필요 없음
            }
        }

        if (k==n){ // 기저
            answer++;
            return;
        }


        for (int i = 0; i <= 9; i++) {
            selected[k]=i;
            perm(k+1);
        }
    }
}
