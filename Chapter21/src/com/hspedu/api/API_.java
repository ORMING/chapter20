package com.hspedu.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class API_ {
	public static void main(String[] args) throws UnknownHostException {
		//1获取本机的InetAddress对象
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println(localHost);//OMNLDUDQKPPKGRI/192.168.3.246
		
		//2根据指定主机名 获取 InetAddress对象
		InetAddress byName = InetAddress.getByName("OMNLDUDQKPPKGRI");
		System.out.println("host=" + byName);//OMNLDUDQKPPKGRI/192.168.3.246

		//3更据域名 返回 InetAddress对象,比如 www.baidu.com 对应
		InetAddress byName2 = InetAddress.getByName("www.baidu.com");
		System.out.println(byName2);//www.baidu.com/119.63.197.151
		
		//4通过 InetAddress 对象,获取对应的地址
		String hostAddress = byName2.getHostAddress();//IP 119.63.197.151
		System.out.println("byName2 对应的ip "  + byName2);
		
		//5通过 InetAddress 对象,获取对应的主机名/或者的域名
		String hostName = byName2.getHostName();
		System.out.println("byName2对应的主机名/域名" + hostName);
		
	}
}
