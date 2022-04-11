package cn.itcast.wanxinp2p.repayment.job;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 利用zookepper的选举机制，选举多个任务的leader,防止一个任务挂掉后重新选举
 * 选举策略默认
 */
@Configuration
public class ZKRegistryCenterConfig {

    @Value("${p2p.zookeeper.connString}")
    private String ZOOKEEPER_CONNECTION_STRING ;

    @Value("${p2p.job.namespace}")
    private String JOB_NAMESPACE;

    //创建注册中心
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter setUpRegistryCenter(){
        //zk的配置
        ZookeeperConfiguration zookeeperConfiguration = new
                ZookeeperConfiguration(ZOOKEEPER_CONNECTION_STRING, JOB_NAMESPACE);
        //创建注册中心
        ZookeeperRegistryCenter zookeeperRegistryCenter = new   ZookeeperRegistryCenter(zookeeperConfiguration);
        return zookeeperRegistryCenter;
    }

}
