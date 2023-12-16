package com.hspedu.homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Homework01Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//思路
		//1连接服务器(ip,端口)
		//解读:连接本机的9999端口,如果连接成功,返回Socket对象
		Socket socket = new Socket(InetAddress.getLocalHost(),9999);

		//2连接上后,生成Socket,通过 socket.getOutPutStream() 
		//得到 和 socket关联的输出流对象
		OutputStream outputStream = socket.getOutputStream();		//
		//3通过输出流写入数据到 数据通道,使用字符流
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		
		//从键盘读取用户问题
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入问题");
		String question = scanner.next();
		bufferedWriter.write(question);
		
		bufferedWriter.newLine();//插入一个换行符表示写入内容结束,注意,要求对方使用readLine()!!!
		bufferedWriter.flush();//如果使用的字符流,需要手动刷新,否则不会写入数据通道
		//  设置结束标记
		socket.shutdownOutput();
		//4获取和socket关联的输入流,读取数据(字符),并显示
		InputStream inputStream = socket.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String s = bufferedReader.readLine();
		System.out.println(s);
		
		
		
		//5关闭流对象和socket,必须关闭
		scanner.close();
		inputStream.close();
		outputStream.close();
		socket.close();
		System.out.println("客户端退出了");
	}
}