import java.util.Scanner;

public class Bj2669 {
	public static int[] x1,x2,y1,y2;
 	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		x1 = new int[4];
		y1 = new int[4];
		x2 = new int[4];
		y2 = new int[4];
		int sum = 0;
		for(int i=0; i<4; i++) {
			x1[i] = sc.nextInt();
			y1[i] = sc.nextInt();
			x2[i] = sc.nextInt();
			y2[i] = sc.nextInt();
			sum+=rectangle(i);
		}
		for(int i=0; i<3; i++) {
			for(int j=i+1; j<4; j++) {
				if((x1[i]>=x1[j]&&x2[i]<=x2[j])&&(y1[i]>=y1[j]&&y2[i]<=y2[j])) {
					// i 번째 사각형이 아예 겹치는 경우
					sum -= rectangle(i);
				}
				else if((x2[i]<x1[j]&&x2[i]>x1[j])) {
					// i번째 사각형의 오른쪽 x축이 다른 사각형 안에 겹치는 경우
					if(y1[i]>=y1[j]&&y2[i]<=y2[j]) {
						// y축이 아예 겹치는 경우
						sum -= rectangle(x1[j],y1[i],x2[i],y2[i]);
					}
					else if(y2[i]>=y1[j]&&y2[i]<=y2[j]) {
						sum -= rectangle(x1[j],y1[j],x2[i],y2[i]);
					}
					else if(y1[i]>=y1[j]&&y1[i]<=y2[j]) {
						sum -= rectangle(x1[j],y1[i],x2[i],y2[j]);
					}
				}
				else if(x1[i]>=x1[j]&&x1[i]<=x2[j]) {
					if(y1[i]>=y1[j]&&y2[i]<=y2[j]) {
						sum -= rectangle(x1[i],y1[i],x2[j],y2[i]);
					}
					else if(y2[i]>=y1[j]&&y2[i]<=y2[j]) {
						sum -= rectangle(x1[j],y1[j],x2[j],y2[i]);
					}
					else if(y1[i]>=y1[j]&&y1[i]<=y2[j]) {
						sum -= rectangle(x1[j],y1[i],x2[j],y2[j]);
					}
				}
				else if(x1[i]>=x1[j]&&x2[i]<=x2[j]) {
					if(y2[i]>=y1[j]&&y2[i]<=y2[j]) {
						sum -= rectangle(x1[j],y1[j],x2[j],y2[i]);
					}
					else if(y1[i]>=y1[j]&&y1[i]<=y2[j]) {
						sum -= rectangle(x1[j],y1[i],x2[j],y2[j]);
					}
				}
			}
		}
		
		System.out.println(sum);
	}
	
	public static int rectangle(int index) {
		return (x2[index]-x1[index])*(y2[index]-y1[index]);
	}
	public static int rectangle(int x1,int y1, int x2, int y2) {
		return (x2-x1)*(y2-y1);
	}
}
