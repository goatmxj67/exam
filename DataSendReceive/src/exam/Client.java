package exam;

/**
 * 프로젝트명 : DataSendReceive 
 * 파일명 : Client.java
 * 작성자 : 이율기
 * 작성일자 : 2021-04-05
 * 설명 : 서버에게 메시지 전달
 */

import java.io.BufferedOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		Socket client = null;
		Scanner sc = null;
		BufferedOutputStream bos = null;
		
		try {
			
			client = new Socket();
			client.connect(new InetSocketAddress("localhost", 1234));
			System.out.println("서버에 접속되었습니다.");
			
			sc = new Scanner(System.in);
			System.out.print("서버에게 전송할 메시지를 입력해주세요. >>> ");
			String message = sc.nextLine();
			bos = new BufferedOutputStream(client.getOutputStream());
			bos.write(message.getBytes("UTF-8"));
			bos.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) { bos.close(); }
				if (!client.isClosed()) { client.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}