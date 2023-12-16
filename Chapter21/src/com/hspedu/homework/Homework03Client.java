package com.hspedu.homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Homework03Client {
	public static void main(String[] args) throws Exception {
		//1接收用户输入,指定下载文件名
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入下载的文件名");
		String downloadFileName = scanner.next();
		
		//2客户端连接服务器端,准备发送
		Socket socket = new Socket(InetAddress.getLocalHost(),9999);
		
		//3获取和Socket关联的输出流
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(downloadFileName.getBytes());

		// 设置写入结束的标志
		socket.shutdownOutput();

		
		//4读取服务端返回的文件(字节数据)
		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
		
		byte[] bytes = StreamUtils.streamToByteArray(bis);
		
		//5得到一个输出流,准备将 bytes 写入到磁盘文件
		//比如你下载的是 55日 => 下载的就是 55日.mp3
		//  你下载的是 123 => 下载的就是 天降英雄 文件名是 123.mp3
		String filePath = "D:\\新建文件夹\\"+downloadFileName+".mp3";
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		bos.write(bytes);
		
		//6关闭相关的资源
		bos.close();
		bis.close();
		outputStream.close();
		socket.close();
		scanner.close();
		System.out.println("客户端下载完毕,推出");
		
	}
}
