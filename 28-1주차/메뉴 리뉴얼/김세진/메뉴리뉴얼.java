import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 메뉴리뉴얼 {
	static List<Map<String,Integer>> foodMaps;
	static int[] MaxCount;
	public static void main(String[] args) {
		String[] orders=new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course=new int[] {2,3,4};
		
		
		System.out.println(Arrays.toString(solution(orders, course)));
	}
    static public String[] solution(String[] orders, int[] course) {
    	
    	//메뉴 구성 갯수 별로 해시 맵 사용
    	foodMaps=new ArrayList<>();
    	
    	//0,1 은 사용하지 않긴함 
    	MaxCount=new int[11];
    	
    	
    	for(int i=0;i<11;i++) {
    		foodMaps.add(new HashMap<String, Integer>());
    	}
    	for(String str:orders) {
    		char[] arr=str.toCharArray();
    		Arrays.sort(arr);
    		comb(arr,0,new StringBuilder());
    	}
    	
    	List<String> list=new ArrayList<String>();
    	
    	for(int len:course) {
    		for (Map.Entry<String, Integer> entry : foodMaps.get(len).entrySet()) {
    			if(entry.getValue()>=2 && entry.getValue()==MaxCount[len]) {
    				list.add(entry.getKey());
    			}
    		}
    	}
    	Collections.sort(list);
    	
    	
        String[] answer = new String[list.size()];
        for(int i=0;i<list.size();i++) {
        	answer[i]=list.get(i);
        }
        return answer;
    }
	private static void comb(char[] str, int pos, StringBuilder candi) {
		if(pos>=str.length) {
			int len=candi.length();
			
			if(len>=2) {
				//없으면 0
				int cnt=foodMaps.get(len).getOrDefault(candi.toString(), 0)+1;
				foodMaps.get(len).put(candi.toString(),cnt);
				MaxCount[len]=Math.max(MaxCount[len], cnt);
			}
			return;
		}
		
		
		comb(str,pos+1,candi.append(str[pos]));
		candi.setLength(candi.length()-1);
		comb(str,pos+1,candi);
	}
}
