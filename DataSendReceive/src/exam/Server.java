package exam;

/**
 * 프로젝트명 : DataSendReceive 
 * 파일명 : Server.java
 * 작성자 : 이율기
 * 작성일자 : 2021-04-05
 * 설명 : 클라이언트로부터 메시지 수신
 */

import java.io.BufferedInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket client = null;
		BufferedInputStream bis = null;
		
		try {
			
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 1234));
			
			while (true) {
				
				System.out.println("=====서버가 동작하고 있습니다.=====");
				client = server.accept();
				InetSocketAddress isa = (InetSocketAddress)client.getRemoteSocketAddress();
				System.out.println("접속 클라이언트: [" + isa.getHostName() + "]");
				
				bis = new BufferedInputStream(client.getInputStream());
				byte[] b = new byte[1024];
				int length = bis.read(b);  
				String receiveMsg = new String(b, 0, length, "UTF-8");
				System.out.println(receiveMsg);
					
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) { bis.close(); }
				if (!server.isClosed()) {	server.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}