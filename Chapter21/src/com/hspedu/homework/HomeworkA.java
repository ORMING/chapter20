package com.hspedu.homework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//接收端
public class HomeworkA {
	public static void main(String[] args) throws IOException {
		//1 创建一个 DatagramSocket 对象,准备在8888接收数据
		DatagramSocket socket = new DatagramSocket(8888);
		//2 构建一个 DatagramPacket 对象,准备接收数据
		//  前面讲解UDP 协议的时候 讲过一个数据包最大64k 64 * 1024
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);//现在还是空的 准备填充(制作接收用的空包packet)
		//3 调用接收方法 ,将通过网络传输的 DatagramPacket 对象
		// 填充到 packet 对象
		System.out.println("接收端 等待接收数据");
		socket.receive(packet);
		
		//4 可以把packet 进行拆包,取出数据,并显示
		int length = packet.getLength();//实际接收到的数据长度
		byte[] data = packet.getData();
		
		String s = new String(data,0, length);
		
		//判断接收到的信息是什么
		String answer = "";
		if ("四大名著是哪些".equals(s)) {
			answer = "四大名著<红楼梦><三国><西游记><水浒传>";
		}else {
			answer = "what?";
		}
		
		//===============回复消息给b
		// 将需要发送的数据,封装到 DatagramPacket 对象
		data = answer.getBytes();
		
		//说明: 封装的 DatagramPacket 对象 data 内容字数组,发送长度,主机(IP),端口
		packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.3.246"),9998);//getLocalHost()获取本地ip
		
		socket.send(packet);
		
		//5 关闭资源
		socket.close();
		System.out.println("A端推出");

		
		
		
		
	}
}
