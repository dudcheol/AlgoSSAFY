/**
 * jungol - 1161. 하노이1
 * Hanoi1_1161.java
 * @date 2020-08-09
 * @author hansolKim
 **/

package jungol;

import java.util.Scanner;

public class Hanoi1_1161 {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		hanoi(N, 1, 3, 2);
		
		
	}
	
	public static void hanoi(int n, int from, int to, int help) {
		if(n == 1) {
			System.out.println(n + " : " +from + " -> " + to);
			return;
		}
		
		// 원반 n-1개를 보조 기둥으로 이동
		hanoi(n-1, from, help, to);
		
		// 가장 큰 원반을 목적지로 이동
		System.out.println(n + " : " +from + " -> " + to);
		hanoi(n-1, help, to, from);
	}
	

}
