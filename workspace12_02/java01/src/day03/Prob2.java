package day03;

public class Prob2 {

	public static void main(String[] args) {
		String sourceString = 
		"everyday we have is one more than we deserve";
		String encodedString = "";
		
		StringBuilder sb = new StringBuilder();
		
		// 프로그램을 구현부 시작.	
		// 참고 : 문자 'a'의 정수값은 97이며, 'z'는 122입니다. 
		
		for(int i=0; i<sourceString.length(); i++) {
			char a = sourceString.charAt(i);
			if(a>=97&&a<=119) {
				a = (char) (a+3);
			}
			else if(a>=120){
				a = (char) (a+3-26);
				
			}
			//encodedString+=a;
			sb.append(a);
		
		}

		// 프로그램 구현부 끝.
		encodedString = sb.toString();
		System.out.println(encodedString.equals("hyhubgdb zh kdyh lv rqh pruh wkdq zh ghvhuyh"));
		System.out.println("암호화할 문자열 : " + sourceString);
		System.out.println("암호화된 문자열 : " + encodedString);
	}
}
