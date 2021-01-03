package algoSSAFY.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
참고
- 완전탐색: https://roseline124.github.io/algorithm/2019/04/16/Algorithm-baekjoon-1038.html
- 큐: https://dundung.tistory.com/118
 */

// 못푼이유: 문제자체를 잘못 이해함 100,200도 감수하는 수로 생각함..
public class _1038_감소하는_수_재귀풀이 {
    private static ArrayList<Long> list;
    private static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<Long>();
        for (int i = 1; i <= 9; i++) {
            bruteForce(i);
        }

        Collections.sort(list);

        if (N == 0) System.out.println(0);
        else if (N > list.size()) System.out.println(-1);
        else System.out.println(list.get(N-1));
    }

    private static void bruteForce(long num) {
        if (num>9876543210L){ // if(cnt>10) return; 이 더 깔끔할듯
            return;
        }

        list.add(num);
        for (int i = 0; i < num%10; i++) { // 가장 끝자리수보다 작은 수만 뒤에 추가할 수 있음
            bruteForce(num*10+i);
        }
    }
}
