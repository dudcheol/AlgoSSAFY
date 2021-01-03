package de;

import java.util.Arrays;
import java.util.Comparator;

public class FileSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(solution(new String[] {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));
	}
	/*
	 * 아이디어
	 * 헤드-넘버-테일 구분 
	 * 헤드-넘버 순으로 sort하면된다.
	 * tail 필요? ㄴㄴ
	 * 
	 */
	
    static public String[] solution(String[] files)  {
    	
    	
        Arrays.sort(files,new Comparator<String>() {

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				String head=arg0.split("[0-9]")[0];
				//헤드를 비운다.
				arg0=arg0.replace(head, "");
				//대소문자 구분이 없도록한다.
				head=head.toLowerCase();
				
				
				StringBuilder sb=new StringBuilder();
				
				//넘버 부분 추출
				char[] arr=arg0.toCharArray();
				for(int i=0;i<arr.length;i++) {
					if(Character.isDigit(arr[i]) && sb.length()<5) {
						sb.append(arr[i]);
					}else {
						
						break;
					}
				}
				int number=Integer.parseInt(sb.toString());
				sb.setLength(0);
				//arg1
				String head2=arg1.split("[0-9]")[0];
				//헤드를 비운다.
				arg1=arg1.replace(head2, "");
				//대소문자 구분이 없도록한다.
				head2=head2.toLowerCase();
				
				
				StringBuilder sb2=new StringBuilder();
				
				//넘버 부분 추출
				char[] arr2=arg1.toCharArray();
				for(int i=0;i<arr2.length;i++) {
					if(Character.isDigit(arr2[i]) && sb2.length()<5) {
						sb2.append(arr2[i]);
					}else {
						break;
					}
				}
				int number2=Integer.parseInt(sb2.toString());
				sb2.setLength(0);
				
				
				if(head.equals(head2)) {
					return number-number2;
				}else {
					return head.compareTo(head2);
				}
			}
        	
        	
		});
        
        
        
        
        return files;
    }
}
