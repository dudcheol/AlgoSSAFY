import java.util.*;

public class n진수게임 {
	public String solution(int n, int t, int m, int p) {
        StringBuilder result=new StringBuilder();
        StringBuilder answer=new StringBuilder();
        
        for (int i = 0; i <= t*m; i++) {
        	result.append(change(i,n));
		}
        
        int count=0;
        
        for (int i =0; i < result.length(); i++) {
        	if(i%m==(p-1)) {//자기 순서가됨
        		count++;
        		answer.append(result.charAt(i));
        		if(count==t)
        			break;
        	}
		}
        return answer.toString();
    }
 public String change(int i,int jin) {
	 StringBuilder m=new StringBuilder();
	 
	if(jin==10)
		return String.valueOf(i);
	while(true) {
		int mok=i/jin;
		int na=i%jin;
		
		switch(na) {
		case 10:
			m.insert(0, "A");
			break;
		case 11:
			m.insert(0, "B");
			break;
		case 12:
			m.insert(0, "C");
			break;
		case 13:
			m.insert(0, "D");
			break;
		case 14:
			m.insert(0, "E");
			break;
		case 15:
			m.insert(0, "F");
			break;
		default:
			m.insert(0,na);
		}
		
		i=mok;
		if(mok==0)
			break;
	}
	return m.toString();
	
 }

}
