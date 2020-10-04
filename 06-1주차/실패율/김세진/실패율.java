package de;

import java.io.IOException;
import java.util.Arrays;

public class 실패율 {

	public static void main(String[] args) throws IOException{
		
//		System.out.println(Arrays.toString(solution(5, new int[] {2, 1, 2, 6, 2, 4, 3, 3})));
		System.out.println(Arrays.toString(solution(8, new int[] {1, 2, 3, 4, 5, 6, 7})));
	}

    static public int[] solution(int N, int[] stages) {
        int[] answer =new int[N];
        double[] arr=new double[N+1];
        double[] result=new double[N+1];
        
        double sumN=stages.length;
        
        //input
        for(int i=0;i<stages.length;i++) {
        	if(stages[i]==N+1) {
        		continue;
        	}
        	arr[stages[i]]++;	
        }
        
        //solve
        for(int i=1;i<=N;i++) {
        	if(arr[i]==0) {
        		result[i]=0;
        	}else {
        		result[i]=arr[i]/sumN;
        	}
        	
        	sumN-=(int)arr[i];
        }
        
        //output
        for(int i=0;i<N;i++) {
        	int index=0;
        	double max=-1;
        	
        	for(int j=1;j<=N;j++) {
        		if(max<result[j]) {
        			max=result[j];
        			index=j;
        		}
        	}
        	result[index]=-1;
        	answer[i]=index;
        }
        
        return answer;
    }
}
