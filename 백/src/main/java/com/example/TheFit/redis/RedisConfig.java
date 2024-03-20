package com.example.TheFit.redis;

import com.example.TheFit.sse.FeedBackNotificationRes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    // 리프레시 토큰을 저장할 레디스 0번 구역
    @Bean
    @Primary
    LettuceConnectionFactory connectionFactory1() { return createConnectionFactoryWith(0); }

    @Bean
    @Primary
    RedisTemplate<String,String> redisTemplate1(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    // ----------------------------------------------------------------------------
    // 못받은 메시지를 저장할 레디스 1번 구역
    @Bean
    @Qualifier("2")
    LettuceConnectionFactory connectionFactory2() { return createConnectionFactoryWith(1); }

    @Bean
    @Qualifier("2")
    RedisTemplate<String,Object> redisTemplate2(@Qualifier("2") RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    //-------------------------------------------------------------------------------
    // 채널들을 등록할 레디스 3번 구역
    @Bean
    @Qualifier("3")
    LettuceConnectionFactory connectionFactory3() { return createConnectionFactoryWith(2); }
    @Bean
    public RedisMessageListenerContainer redisMessageListener(@Qualifier("3")RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
    @Bean
    @Qualifier("3")
    public RedisTemplate<String, String> redisTemplate3(@Qualifier("3")RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }


    public LettuceConnectionFactory createConnectionFactoryWith(int index) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setDatabase(index);
        // n번 레디스가 정상 연결 확인 메시지 출력
        //System.out.println("set db"+index);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
}
