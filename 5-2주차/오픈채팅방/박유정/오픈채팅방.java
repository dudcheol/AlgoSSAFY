import java.util.*;

public class 오픈채팅방 {
public String[] solution(String[] record) {       
        StringBuilder sb=new StringBuilder();
        HashMap<String,String> hm=new HashMap<String, String>();//id와 닉네임저장
        Queue<Pair> q=new LinkedList<>();//명령과 id저장
        
        StringTokenizer st;        
        for (int i = 0; i < record.length; i++) {
        	st=new StringTokenizer(record[i]);
        	String com=st.nextToken();
        	String id=st.nextToken();
            
        	if(com.equals("Leave")) {
                q.add(new Pair(com,id));
        		continue;
        	}
        	
        	String name=st.nextToken();
        	
        	if(com.equals("Enter")) {
                q.add(new Pair(com,id));
        		hm.put(id,name);
        	}
            else if(com.equals("Change")){
                hm.put(id,name);
            }
		}
    
       String[] answer =new String[q.size()];
       for(int i=0;!q.isEmpty();i++){
        	Pair p=q.poll();
        	String com=p.com;
        	String id=p.id;
        	    
        	if(com.equals("Enter")) {
        		answer[i]=sb.append(hm.get(id)).append("님이 들어왔습니다.").toString();
        	}
        	else if(com.equals("Leave")) {
        		answer[i]=sb.append(hm.get(id)).append("님이 나갔습니다.").toString();
        	}
        	sb.delete(0,sb.length());//sb리셋
        }
        return answer;
    }
}
class Pair{
	String com;
	String id;
	
	public Pair(String com, String id) {
		super();
		this.com = com;
		this.id = id;
	}
}