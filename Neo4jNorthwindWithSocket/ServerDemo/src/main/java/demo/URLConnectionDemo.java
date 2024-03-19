package demo;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class URLConnectionDemo {
	public static void main(String[] args) throws IOException {
		
		URL url = new URL("https://eclipse.dev/eclipselink/documentation/");
		
		Scanner sc = new Scanner(url.openStream());
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			System.out.println(line);
		}
		
		sc.close();
	}
}
