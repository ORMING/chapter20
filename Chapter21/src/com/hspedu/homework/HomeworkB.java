package com.hspedu.homework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//发送端
public class HomeworkB {
	

	public static void main(String[] args) throws IOException {
		//1 创建 DatagramSocket 对象,准备发送和接收数据
		DatagramSocket socket = new DatagramSocket(9998);
		
		//2 将需要发送的数据,封装到 DatagramPacket 对象
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入你的问题");
		String question = scanner.next();
		
		byte[] data = question.getBytes();
		
		//说明: 封装的 DatagramPacket 对象 data 内容字数组,发送长度,主机(IP),端口
		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.3.246"),8888);//getLocalHost()获取本地ip
		System.out.println("接收端 等待接收数据");
		socket.send(packet);
		
		//3=== 接收A端回复的信息
		//(1) 构建一个DatagramPacket对象,准备接收数据
		// 在前面讲解UDP协议时,说过一个数据包最大 64k
		byte[] buf = new byte[1024];
		packet = new DatagramPacket(buf, buf.length);
		//(2) 调用 接收方法,将通过网络传输的 DatagramPacket 对象
		// 填充到 packet 对象
		// 当有数据包发送到本机的9998端口时,就会接收到数据
		// 如果没有数据包发送到时,就会阻塞等待
		
		socket.receive(packet);
		//(3) 可以把packet 进行拆包,取出数据,并显示
		int length = packet.getLength();//实际接收到的数据长度
		data = packet.getData();
		
		String s = new String(data,0, length);
		System.out.println(s);
		//关闭资源
		scanner.close();
		socket.close();
		System.out.println("B端推出");
	}
}
