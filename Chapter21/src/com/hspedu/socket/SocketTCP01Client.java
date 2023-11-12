package com.hspedu.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//客户端 发送"hello,server"给服务的
public class SocketTCP01Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//思路
		//1连接服务器(ip,端口)
		//解读:连接本机的9999端口,如果连接成功,返回Socket对象
		Socket socket = new Socket(InetAddress.getLocalHost(),9999);
		System.out.println("服务端 socket" + socket.getClass());
		//2连接上后,生成Socket,通过 socket.getOutPutStream() 
		//得到 和 socket关联的输出流对象
		OutputStream outputStream = socket.getOutputStream();		//
		//3通过输出流写入数据到 数据通道
		outputStream.write("hello,server".getBytes());
		//4关闭流对象和socket,必须关闭
		outputStream.close();
		socket.close();
		System.out.println("客户端退出了");
		
		
	}
}
