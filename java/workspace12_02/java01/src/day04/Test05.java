package day04;

public class Test05 {

	public static void main(String[] args) {
		int[] eng;
		eng = new int[5];

		String[] names = new String[5];

		names[0] = "김영구";
		names[1] = "홍길동";
		names[2] = "";
		names[3] = "랄라라";
		names[4] = "랠래래";

		for (int i = 0; i < eng.length; i++) {
			eng[i] = (int) (Math.random() * 41) + 60;
			if (names[i] != null && names[i].length()>0) {
				System.out.printf("%c** : %d %n", names[i].charAt(0), eng[i]);
			}
		}
	}

}
