import java.util.*;

public class 파일명정렬 {
	public String[] solution(String[] files) {
        Arrays.sort(files,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				StringBuilder head1=new StringBuilder();
				StringBuilder num1=new StringBuilder();
				StringBuilder head2=new StringBuilder();
				StringBuilder num2=new StringBuilder();
				boolean check=false;//head와 num만 뽑아내기위함
				boolean check2=false;
				
				for (int i = 0; i < o1.length(); i++) {
					char k=o1.charAt(i);
					if(!Character.isDigit(k)&&!check)//문자이고 숫자등장 안한경우
						head1.append(k);
					else if(Character.isDigit(k)) {//숫자등장
						check=true;
						num1.append(k);
					}
					else //문자인데 숫자가등장했던경우
						break;
						
				}
				for (int i = 0; i < o2.length(); i++) {
					char k=o2.charAt(i);
					if(!Character.isDigit(k)&&!check2)//문자이고 숫자등장 안한경우
						head2.append(k);
					else if(Character.isDigit(k)) {//숫자등장
						check2=true;
						num2.append(k);
					}
					else //문자인데 숫자가등장했던경우
						break;
					
				}
				String h1= head1.toString().toUpperCase();
				String h2= head2.toString().toUpperCase();
				
				if(h1.equals(h2)) {//head가 같은경우 숫자로 판별
					return Integer.parseInt(num1.toString())-Integer.parseInt(num2.toString());
				}
				return h1.compareTo(h2);
			}
        	
        });
        return files;
    }
}
