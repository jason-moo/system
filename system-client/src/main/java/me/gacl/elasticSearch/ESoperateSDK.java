package me.gacl.elasticSearch;

import com.xkeshi.core.elasticsearch.EsOperateSdk;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import java.net.InetAddress;

/**
 * Created by jason_moo on 2018/2/24.
 */
public class ESoperateSDK implements InitializingBean {

    private static volatile TransportClient transportClient;

    private String esClusterNodes = "127.0.0.1:9300";

    /**
     * es集群名称
     */
    private static String esClusterName = "";

    /**
     * es所有操作的超时时间,单位秒
     */
    private static int esOperateTimeoutSeconds = 5;

    private ESoperateSDK(){

    }

    @PostConstruct
    public static void init(){
        System.out.println("进来啊");
        if (transportClient == null){
            synchronized (EsOperateSdk.class){
                if (transportClient == null){
                    initTransportClient();
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    private static void initTransportClient(){

        transportClient = new PreBuiltTransportClient(Settings.builder().put("cluster.name", esClusterName)
                // 自动嗅探其他es节点
                .put("client.transport.sniff", true)
                .put("client.transport.ignore_cluster_name", false)
                .put("client.transport.ping_timeout", "5s")
                .put("client.transport.nodes_sampler_interval", "5s").build());
        try {

            transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),3300));

        }catch (Exception e){

        }

    }

    public static void main(String[] args) {
        new ESoperateSDK();
    }
}
