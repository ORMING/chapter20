package com.hspedu.homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Homework03Server {
	

	public static void main(String[] args) throws Exception {
		//1 监听9999端口
		ServerSocket serverSocket = new ServerSocket(9999);
		//2 等待客户端连接
		System.out.println("服务端在9999端口监听");
		Socket socket = serverSocket.accept();
		//3 读取 客户端发送要下载的文件名
		//  这里使用了循环读取文件名,是考虑将来客户端发送的数据较大的情况
		InputStream inputStream = socket.getInputStream();
		byte[] b = new byte[1024];
		int len = 0;
		String downLoadFileName = "";
		while ((len = inputStream.read(b))!= -1) {
			downLoadFileName += new String(b, 0 , len);
			
		}
		System.out.println("客户端希望下载文件名=" + downLoadFileName);
		
		
		//准备了两个文件,一个北京55日,一个没有天降英雄
		// 要求55日时返回,否则默认返回天降英雄
		String resFileName = "";
		if("《北京55日》".equals(downLoadFileName)) {
			resFileName = "src\\《北京55日》.mp3";
		}else {
			resFileName = "src\\【反贼金曲】世上没有天降的英雄.mp3";
		}
		
		//4 创建一个输入流,读取文件
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(resFileName));
		
		//5 使用工具类StreamUtils,读取文件到一个字节数组
		byte[] bytes = StreamUtils.streamToByteArray(bis);
		//6 得到Socket关联的输出流
		BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
		
		//7 写入到数据通道,返回给客户端
		bos.write(bytes);
		socket.shutdownInput();
		
		//8 关闭相关的资源
		bis.close();
		inputStream.close();
		socket.close();
		serverSocket.close();
		
	}
}
