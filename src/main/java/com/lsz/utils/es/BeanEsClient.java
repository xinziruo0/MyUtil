package com.lsz.utils.es;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
/**
 * 小小滴增加下用spring来管理client
 * @author lsz
 *
 */
public class BeanEsClient extends TransportClient{
	private List<String> netAddrs ;
	
	public BeanEsClient() {
		// TODO Auto-generated constructor stub
		super();
	}
	public BeanEsClient(MySettings settings){
		super(settings.build());
	
	
	}
	
	/**
	 * 从配置文件解析地址端口信息
	 * @return 地址还有端口
	 */
	private List<String[]> parseAddrs(){
		List<String[]> addrs = new ArrayList<String[]>();
		for(String addr:netAddrs){
			String[] s = new String[2];
			s[0] = addr.substring(0,addr.indexOf(':'));
			s[1] = addr.substring(addr.indexOf(':')+1,addr.length());
			addrs.add(s);
		}
		return addrs;
	}
	public List<String> getNetAddrs() {
		return netAddrs;
	}
	public void setNetAddrs(List<String> netAddrs) {
		this.netAddrs = netAddrs;
		
		/**
		 * 添加es节点ip port
		 */
		List<String[]> addrs = parseAddrs();
		
		for(String[] s:addrs){
			this.addTransportAddress(new InetSocketTransportAddress(s[0],Integer.parseInt(s[1])));
			System.out.println(s[0]);
			System.out.println(s[1]);
		}
	}
}
