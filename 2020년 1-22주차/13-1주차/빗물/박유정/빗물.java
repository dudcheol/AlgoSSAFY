package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 빗물 {
	static int[] arr;
	static int sum = 0;
	static int h, w;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		arr = new int[w];
		for (int i = 0; i < w; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max=Integer.MIN_VALUE;
		int index=0;
	//	System.out.println(Arrays.toString(arr));
		for (int i = 0; i < w; i++) {//최댓값
			if(max<arr[i]) {
				max=arr[i];
				index=i;
			}
		}
		
		maxfind(index,0);//최댓값의 왼쪽 검사
		maxfind(index,w-1);//최댓값의 오른쪽 검사
		System.out.println(sum);
	}

	private static void maxfind(int start, int end) {
		if (start == end || start < 0 || end < 0 || start >= w || end >= w)
			return;
		int max = Integer.MIN_VALUE;
		int index = 0;

		if (start > end) {//왼쪽검사
			for (int i = start-1; i >= end; i--) {//가장앞쪽에 있는 최대 값을 찾아서
				if (max <= arr[i]) {
					max = arr[i];
					index = i;
				}
			}
			//System.out.println(start+" "+index);
			rain(start, index);	//찾은 최댓값 높이만큼 빗물채우기
			maxfind(index, 0);//최댓값을 기준으로 다시 왼쪽 검사
		} else {
			for (int i = start+1; i <= end; i++) {
				if (max <= arr[i]) {
					max = arr[i];
					index = i;
				}
			}
		//	System.out.println(start+" "+index);
			rain(start, index);//빗물을 채우기
			maxfind(index, w - 1);//최댓값을 기준으로 오른쪾을 검사
		}

	}

	private static void rain(int start, int end) {
		int k = Math.min(arr[start], arr[end]);
		if (start > end) {
			for (int i = start-1; i >= end; i--) {
				sum += k - arr[i];
			}

		} else {
			for (int i = start+1 ; i <= end; i++) {
				sum += k - arr[i];
			}
		}
	}

}
