package algoSSAFY.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _17298_오큰수 {
    private static int N, arr[], answer[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = new int[N]; // 정답을 담을 배열 따로 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0); // 스택에는 arr배열의 인덱스를 넣는다. [인덱스]를 관리한다는 것이 핵심!!
        for (int i = 1; i < N; i++) { // N개 수를 차례로 순회
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]){ // arr을 순회하면서 top보다 큰 수를 만나면 그 수가 오큰수가 되므로 정답배열에 넣어준다.
                answer[stack.pop()] = arr[i];                      // 이때, 한번만 하지 않고 작거나 같은 수를 만나기 전까진 계속 해주는 것이 포인트
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){ // 스택이 비어있지 않을 경우 남은 인덱스에 모두 -1을 넣는다 (오큰수가 없어서 스택에서 빠져나오지 못했음)
            answer[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(' ');
        }
        System.out.print(sb);
    }
}
