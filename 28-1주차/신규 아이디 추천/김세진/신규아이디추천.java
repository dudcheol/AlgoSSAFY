
public class 신규아이디추천 {

	public static void main(String[] args){
		
		System.out.println(Sol("...!@BaT#*..y.abcdefghijklm"));
		System.out.println(Sol("abcdefghijklmn.p"));
	}
	
	public static String Sol(String new_id) {
		
		//1단계
		String output=new_id.toLowerCase();
		
		//2단계
		output=output.replaceAll("[^a-z0-9-_.]", "");
		
		//3단계
		while(output.contains("..")) {
			output=output.replace("..", ".");
		}
		//4단계
		if(output.startsWith(".")){
			output=output.substring(1);
		}
		
		if(output.endsWith(".")) {
			output=output.substring(0,output.length()-1);
		}
		//5단계
		if(output.length()==0) {
			output="a";
		}
		//6단계
		if(output.length()>=16) {
			output=output.substring(0,15);
		}
		if(output.endsWith(".")) {
			output=output.substring(0,output.length()-1);
		}
		
		
		StringBuilder sb=new StringBuilder();
		sb.append(output);
		//7단계
		while(sb.length()<3) {
			sb.append(output.charAt(output.length()-1));
		}
		
		
		return sb.toString();
	}

}
