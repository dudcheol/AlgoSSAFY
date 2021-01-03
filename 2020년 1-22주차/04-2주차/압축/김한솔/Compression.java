/**
 * programmers - 2018 카카오 블라인드 채용. 압축
 * RecentlyMusic.java
 * @date 2020-08-22
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Compression {
	
	public static void main(String[] args) {
		Compression compression = new Compression();
		compression.solution("KAKAO");
	}

	public int[] solution(String msg) {
        int[] answer = {};
        List<Integer> idxList = new ArrayList<>();
        HashMap<String, Integer> dic = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int cnt = 26;
        
        /** 사전 세팅 */
        for(int i=1; i<=cnt; i++) {
        	dic.put(Character.toString((char)(i+64)), i);
        }
        
        int idx = 0;
        
        /** 주요 로직 구현 */
        for(int i=0; i<msg.length(); i++) {
        	
        	sb.append(msg.charAt(i));
        	idx = dic.get(Character.toString(msg.charAt(i))); // idx 가져옴
        	
        	/* 다음문자열과 합쳐진 문자열이 사전에 존재하는지 확인 ---> 없을 때까지 조사 */
        	for(int j=i+1; j<msg.length(); j++) {
        		sb.append(msg.charAt(j));
        		if(!dic.containsKey(sb.toString())) { // 해당 키가 없는 경우
        			break;
        		}
        		idx = dic.get(sb.toString()); // idx 갱신
        		i++;
        	}
        	
        	dic.put(sb.toString(), ++cnt);
        	idxList.add(idx);
        	sb.replace(0, sb.length(), "");
        }
        
        answer = new int[idxList.size()];
        for(int i=0; i<idxList.size(); i++) {
        	answer[i] = idxList.get(i);
        }
        
        return answer;
    }

}
