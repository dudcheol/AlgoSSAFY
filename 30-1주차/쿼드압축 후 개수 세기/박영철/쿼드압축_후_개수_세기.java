import java.util.*;

// 10:15~10:32
class Solution {
    int zero;
    int one;
    
    public int[] solution(int[][] arr) {
        int size = arr.length;
        
        zip(arr, 0, 0, size);
        
        int[] answer = {zero, one};
        return answer;
    }
    
    public void zip(int[][] arr, int y, int x, int size){
        // 현재 범위가 모두 같은 숫자인지 확인
        int zcnt=0;
        int ocnt=0;
        for(int i=y;i<y+size;i++){
            for(int j=x;j<x+size;j++){
                if(arr[i][j]==0)zcnt++;
                else ocnt++;
            }
        }
        
        if(zcnt==size*size) {
            zero++;
            return;
        } else if(ocnt==size*size){
            one++;
            return;
        }
        
        // 4등분하기
        int div = size/2;
        zip(arr, y, x, div);
        zip(arr, y+div, x, div);
        zip(arr, y, x+div, div);
        zip(arr, y+div, x+div, div);
    }
}
