package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B5373 {
	static char[][][] cube;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		
		
		
		
		while (TC-- > 0) {
			//point index color
			//top 1 white , bottom 2 yellow , front 3 red, back 4 orange, left 5 green, right 6 blue   
			cube=new char[7][4][4];
			
			
			char[] color=new char[]{'0','w','y','r','o','g','b'};
			
			for(int i=1;i<=6;i++) {
				for(int j=1;j<=3;j++) {
					for(int k=1;k<=3;k++) {
						cube[i][j][k]=color[i];
					}
				}
			}
		
			/*
			 * for(int i=1;i<=6;i++) { for(int j=1;j<=3;j++) { for(int k=1;k<=3;k++) {
			 * System.out.print(cube[i][j][k]); } System.out.println(); }
			 * System.out.println(); }
			 */
			
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer stz = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				String input = stz.nextToken();
				char Point = input.charAt(0);
				char oper = input.charAt(1);
				
				char[] temp=new char[4];
				switch (Point) {
				
				case 'U':
					// index 1
					if (oper == '+') {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[4][3][j];
							cube[4][3][j]=cube[5][j][3];
							cube[5][j][3]=cube[3][1][j];
							cube[3][1][j]=cube[6][j][1];
							cube[6][j][1]=temp[j];
							
						}
						innerChange(oper, 1);
					} else {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[4][3][j];
							cube[4][3][j]=cube[6][j][1];
							cube[6][j][1]=cube[3][1][j];
							cube[3][1][j]=cube[5][j][3];
							cube[5][j][3]=temp[j];
							
						}
						innerChange(oper, 1);
					}

					break;
				case 'D':
					//index 2
					if (oper == '+') {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[3][3][j];
							cube[3][3][j]=cube[5][j][1];
							cube[5][j][1]=cube[4][1][j];
							cube[4][1][j]=cube[6][j][3];
							cube[6][j][3]=temp[j];
							
						}
						innerChange(oper, 2);
					} else {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[3][3][j];
							cube[3][3][j]=cube[6][j][3];
							cube[6][j][3]=cube[4][1][j];
							cube[4][1][j]=cube[5][j][1];
							cube[5][j][1]=temp[j];
							
						}
						innerChange(oper, 2);
					}

					break;
				case 'F':
					//index 3
					if (oper == '+') {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[1][3][j];
							cube[1][3][j]=cube[5][3][j];
							cube[5][3][j]=cube[2][1][j];
							cube[2][1][j]=cube[6][3][j];
							cube[6][3][j]=temp[j];
							
						}
						innerChange(oper, 3);
					} else {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[1][3][j];
							cube[1][3][j]=cube[6][3][j];
							cube[6][3][j]=cube[2][1][j];
							cube[2][1][j]=cube[5][3][j];
							cube[5][3][j]=temp[j];
							
						}
						innerChange(oper, 3);
					}
					break;
				case 'B':
					//index 4
					if (oper == '+') {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[1][1][j];
							cube[1][1][j]=cube[6][1][j];
							cube[6][1][j]=cube[2][3][j];
							cube[2][3][j]=cube[5][1][j];
							cube[5][1][j]=temp[j];
							
						}
						innerChange(oper, 4);
					} else {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[1][1][j];
							cube[1][1][j]=cube[5][1][j];
							cube[5][1][j]=cube[2][3][j];
							cube[2][3][j]=cube[6][1][j];
							cube[6][1][j]=temp[j];
							
						}
						innerChange(oper, 4);
					}

					break;
				case 'L':
					//index 5
					if (oper == '+') {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[1][j][1];
							cube[1][j][1]=cube[4][j][1];
							cube[4][j][1]=cube[2][j][1];
							cube[2][j][1]=cube[3][j][1];
							cube[3][j][1]=temp[j];
							
						}
						innerChange(oper, 5);
					} else {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[1][j][1];
							cube[1][j][1]=cube[3][j][1];
							cube[3][j][1]=cube[2][j][1];
							cube[2][j][1]=cube[4][j][1];
							cube[4][j][1]=temp[j];
							
						}
						innerChange(oper, 5);
					}

					break;
				case 'R':
					//index 6
					if (oper == '+') {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[1][j][3];
							cube[1][j][3]=cube[3][j][3];
							cube[3][j][3]=cube[2][j][3];
							cube[2][j][3]=cube[4][j][3];
							cube[4][j][3]=temp[j];
							
						}
						innerChange(oper, 6);
					} else {
						for(int j=1;j<=3;j++) {
							temp[j]=cube[1][j][3];
							cube[1][j][3]=cube[4][j][3];
							cube[4][j][3]=cube[2][j][3];
							cube[2][j][3]=cube[3][j][3];
							cube[3][j][3]=temp[j];
							
						}
						innerChange(oper, 6);
					}

					break;
					
				}
//				for (int z = 1; z <= 6; z++) {
//					System.out.println("색 구분"+z);
//					for (int h = 1; h <= 3; h++) {
//						for (int k = 1; k <= 3; k++) {
//							System.out.print(cube[z][h][k]);
//						}
//						System.out.println();
//					}
//					System.out.println("-------------------------------");
//				}
			}
			
			for(int i=1;i<=3;i++) {
				for(int j=1;j<=3;j++) {
					System.out.print(cube[1][i][j]);
				}
				System.out.println();
			}

		}
	}
	
	static public void innerChange(char oper,int color) {
		char temp='i';
		if(oper=='+') {
			temp=cube[color][1][1];
			cube[color][1][1]=cube[color][3][1];
			cube[color][3][1]=cube[color][3][3];
			cube[color][3][3]=cube[color][1][3];
			cube[color][1][3]=temp;
			
			temp=cube[color][2][1];
			cube[color][2][1]=cube[color][3][2];
			cube[color][3][2]=cube[color][2][3];
			cube[color][2][3]=cube[color][1][2];
			cube[color][1][2]=temp;

		}else {
			temp=cube[color][1][1];
			cube[color][1][1]=cube[color][1][3];
			cube[color][1][3]=cube[color][3][3];
			cube[color][3][3]=cube[color][3][1];
			cube[color][3][1]=temp;
			
//			for(int i=1;i<=3;i++) {
//				for(int j=1;j<=3;j++) {
//					System.out.print(cube[1][i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println("--!--------------------!--");
			
			temp=cube[color][2][1];
			cube[color][2][1]=cube[color][1][2];
			cube[color][1][2]=cube[color][2][3];
			cube[color][2][3]=cube[color][3][2];
			cube[color][3][2]=temp;
		}
		
		
	}
}
