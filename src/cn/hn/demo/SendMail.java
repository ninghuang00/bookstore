package cn.hn.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import sun.misc.BASE64Encoder;


public class SendMail {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		SendMail sm = new SendMail();
		Socket socket = new Socket("smtp.sina.com",25);
		OutputStream out = socket.getOutputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//InputStream reader = socket.getInputStream();
		BASE64Encoder encoder = new BASE64Encoder();
		String username = encoder.encode("ninghuang00".getBytes());
		String password = encoder.encode("hn123456".getBytes());
		String data ="终于等到你caonima\r\n";
		 
//		System.out.println(reader.readLine());
//		out.write("ehlo ninghaung\r\n".getBytes());
//		
//		System.out.println(reader.readLine());
//		System.out.println(reader.readLine());
//		System.out.println(reader.readLine());
//		
//		out.write("auth login\r\n".getBytes());
//		System.out.println(reader.readLine());
		
		
		sm.write("ehlo ninghaung\r\n".getBytes(),reader,out);
		sm.write("auth login\r\n".getBytes(), reader, out);
		sm.write((username + "\r\n").getBytes(), reader, out);
		sm.write((password + "\r\n").getBytes(), reader, out);
		sm.write("mail from:<ninghuang00@sina.com>\r\n".getBytes(), reader, out);
		sm.write("rcpt to:<ninghuang00@gmail.com>\r\n\r\n".getBytes(), reader, out);
		sm.write("data\r\n".getBytes(), reader, out);
		sm.write("from:<ninghuang00@sina.com>\r\n".getBytes(), reader, out);
		sm.write("to:<ninghuang00@gmail.com>\r\n".getBytes(), reader, out);
		sm.write("subject:test222\r\n\r\n".getBytes(), reader, out);
		sm.write(data.getBytes(), reader, out);
		sm.write(".\r\n".getBytes(), reader, out);
		sm.write("quit\r\n".getBytes(), reader, out);
		
		socket.close();
		
		

	}
	
	public void write(byte[] b,BufferedReader reader, OutputStream out) throws IOException{
		int count = 0;
		//String line = null;
		int len = 0;
		//char[] buffer = new char[1024];
		while(( len = reader.read()) > 0 && count < 3){
			//System.out.println(new String(buffer));
			System.out.println(len);
			count ++;
		}
		
		System.out.println(new String(b));
		out.write(b);
	}

}
