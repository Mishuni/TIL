package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import day06.Book;

public class Test08 {
	public static void main(String[] args) {
		System.out.println("-----------START------------");
		
		try(Scanner scanner = new Scanner(new File("bookdata.txt"));) {
			StringTokenizer st = null;
			while(scanner.hasNextLine()) {
                st = new StringTokenizer(scanner.nextLine());
                String title = st.nextToken();
                int price = Integer.parseInt(st.nextToken());
                Book book = new Book(title,price);
                book.print();
                
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("bookdata.txt 파일 확인 해 주세요");
		}
		

		System.out.println("-----------END------------");
	}
}
