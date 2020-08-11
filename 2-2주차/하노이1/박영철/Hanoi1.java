package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hanoi1 {
    static StringBuilder sb = new StringBuilder();

    static void hanoi(int n, int from, int by, int to){

        if (n == 1){
            sb.append(n).append(" ").append(":").append(" ").append(from).append(" ").append("-").append(">").append(" ").append(to).append("\n");
            return;
        }

        hanoi(n-1, from, to, by);
        sb.append(n).append(" ").append(":").append(" ").append(from).append(" ").append("-").append(">").append(" ").append(to).append("\n");
        hanoi(n-1, by, from, to);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        hanoi(n, 1, 2, 3);
        System.out.print(sb);
    }
}
