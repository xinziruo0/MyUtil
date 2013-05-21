package com.lsz.utils.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lsz.utils.es.BeanEsClient;
import com.lsz.utils.es.BeanEsSetting;
import com.lsz.utils.es.MySettings;

public class ESClinetBeanTest {
	private static ApplicationContext ac;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext(new String[] {
			"es-context.xml"
		});
	}
	
	@Test
	public void test() {
//		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "cluster1").build();
//    	Client client = new TransportClient(settings);
//        ((TransportClient) client).addTransportAddress(new InetSocketTransportAddress("192.168.43.135", 9300));
    	
		Client client = (BeanEsClient)ac.getBean("esClient");
		MySettings settings = (BeanEsSetting)ac.getBean("settings");
	
    	SearchResponse response = client.prepareSearch("myindex")
    	        .setTypes("myindex")
    	        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
    	        .setQuery(QueryBuilders.termQuery("name", "123"))             // Query
    	       // Filter
    	        .setFrom(0).setSize(60).setExplain(true)
    	        .execute()
    	        .actionGet();
    	System.out.println(response);
		
		
	}

}
