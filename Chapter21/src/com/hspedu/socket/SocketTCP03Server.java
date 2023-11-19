package com.hspedu.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP03Server {
	 public static void main(String[] args) throws IOException {
		//思路
		//1. 在本机 的 9999 端口监听, 等待连接
		// 细节: 要求在本机没有其它服务在监听 9999
		// 细节：这个 ServerSocket 可以通过 accept() 返回多个 Socket[多个客户端连接服务器的并发]
		 ServerSocket serverSocket = new ServerSocket(9999);
		 System.out.println("服务器,在9999端口监听,等待连接..");
		//2. 当没有客户端连接 9999 端口时，程序会 阻塞, 等待连接
		// 如果有客户端连接，则会返回 Socket 对象，程序继续
		 Socket socket = serverSocket.accept();
		 System.out.println("socket=" + socket.getClass());
		 //
		//3. 通过 socket.getInputStream() 读取客户端写入到数据通道的数据, 显示
		 InputStream inputStream = socket.getInputStream();
		//4. IO 读取 ,使用字符流,这里使用 InputStreamReader 将 inputStream 转换字符流
		 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		 String s = bufferedReader.readLine();
		 System.out.println(s);
		 //5 获取socket相关联的输出流
		 OutputStream outputStream = socket.getOutputStream();
		 
		 //使用字符输出流的方式回复消息
		 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		 bufferedWriter.write("hello client 字符流");
		 bufferedWriter.newLine();//插入一个换行符,表示回复内容结束
		 bufferedWriter.flush();//注意需要手动刷新
		 
		 //6关闭流和socket
		 outputStream.close();
		 inputStream.close();
		 socket.close();
		 serverSocket.close();//关闭
		 System.out.println("服务器关闭");
	}
}
