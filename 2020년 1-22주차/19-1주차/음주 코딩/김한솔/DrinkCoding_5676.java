/**
 * Backjoon - 5676. 음주코딩
 * DrinkCoding_5676.java
 * @date 2020-12-09
 * @author hansolKim
 **/

package p5000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class DrinkCoding_5676 {
	
	static int N, K; // 수열의 개수, 라운드 수
	static int[] numArray, segTree; // 수열을 저장할 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		String str = "";
		while((str=br.readLine()) != null) {
			
			StringTokenizer st = new StringTokenizer(str);
			// if(st.countTokens() == 0) { break; } 
			
			N = Integer.parseInt(st.nextToken()); // 수열의 개수 입력
			K = Integer.parseInt(st.nextToken()); // 라운드 수 입력
			
			// 수열 입력
			numArray = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				int num = Integer.parseInt(st.nextToken());
				numArray[i] = num;
			}
			
			// 해당 수열 세그먼트 트리 형태로 저장 -> 트리의 높이를 구해야함
			// 트리의 높이
			int base = 2;
			int temp = 2;
			int h = 1;
			while(temp < N) {
				temp *= base;
				h++;
			}
			
			int size = (int) Math.pow(2, h+1);
			System.out.println(N+" " +size);
			segTree = new int[size];
			
			init();
			segTreeInit(1, N, 1);
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				
				char command = st.nextToken().charAt(0);
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				
				if(command == 'C') { // left위치에 right값으로 변경
					right = right==0 ? 0 : right>0 ? 1 : -1;
					
					updateTree(1, N, 1, left, right);
				} else { // left에서 right까지의 곱 계산
					int result = getMultipleValue(1, N, 1, left, right);
					sb.append(result == 0 ? "0" : result == 1 ? "+" : "-");
				}
			}
			
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static int getMultipleValue(int start, int end, int node, int left, int right) {
		if(left > end || right < start) { return 1; } // 0으로 리턴하면 안됨...
		if(left<=start && end<=right) { return segTree[node]; }
		
		int mid = (start+end)/2;
		return getMultipleValue(start, mid, node*2, left, right) 
				* getMultipleValue(mid+1, end, node*2+1, left, right); 
	}

	private static int updateTree(int start, int end, int node, int idx, int value) {
		if(idx < start || idx > end) { return segTree[node]; } // 변경하려는 인덱스 위치가 노드값과 상관이 없는 경우
		
		if(start == end) { return segTree[node] = value; } // 해당 노드 값 변경 
		
		int mid = (start+end)/2;		
		int leftNode = updateTree(start, mid, 2*node, idx, value);
		int rightNode = updateTree(mid+1, end, 2*node+1, idx, value);
		segTree[node] = leftNode * rightNode;
		
		return segTree[node];
	}

	private static void init() {
		for(int i=1; i<segTree.length; i++) {
			segTree[i] = 1;
		}
	}

	private static int segTreeInit(int start, int end, int idx) {
		if(start == end) {
			if(numArray[start] > 0) { return segTree[idx] = 1; }
			if(numArray[start] < 0) { return segTree[idx] = -1; }
			return segTree[idx] = 0;
		}
		
		int mid = (start+end)/2;
		segTree[idx] *= segTreeInit(start, mid, 2*idx);
		segTree[idx] *= segTreeInit(mid+1, end, 2*idx+1);
		
		return segTree[idx];
	}
}
