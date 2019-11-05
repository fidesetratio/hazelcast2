package com.app.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@Configuration
public class HazelcastConfiguration {
	 @Bean
	    public Config hazelCastConfig() {
	        Config config = new Config();

	        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
	        joinConfig.getMulticastConfig()
	        .setEnabled(false);
	        
	        joinConfig.getTcpIpConfig()
	        .setMembers(Collections.singletonList("localhost"))
	        .setEnabled(true);
	        
	        MapConfig usersMapConfig = new MapConfig()
	                .setName("hazelcast-instance")
	                .setTimeToLiveSeconds(600)
	                .setEvictionPolicy(EvictionPolicy.LFU);
	        
	        config.addMapConfig(usersMapConfig);
	        return config;
	    }
}