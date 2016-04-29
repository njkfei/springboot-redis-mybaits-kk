package com.sanhao.tech.teacherfeed.config;

import java.util.Properties;

import javax.sql.DataSource;

import com.sanhao.tech.teacherfeed.model.Postinfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@EnableAutoConfiguration
@Configuration
@PropertySource(value = { "classpath:conf/redis.properties" })
public class RedisConfiguration {

	@Value("${redis.host:127.0.0.1}")
	private String hostName;
	@Value("${redis.index:0}")
	private int index;
	@Value("${redis.password:}")
	private String password;
	@Value("${redis.port:6379}")
	private int port;

	@Value("${redis.timeout:20000}")
	private int timeout;

    //You need this
   @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean
	public JedisConnectionFactory jedisConnFactory() {
		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
		jedisConnFactory.setUsePool(true);
		jedisConnFactory.setHostName(hostName);
		jedisConnFactory.setDatabase(index);
		jedisConnFactory.setPassword(password);
		jedisConnFactory.setPort(port);
		jedisConnFactory.setTimeout(timeout);

		return jedisConnFactory;
	}

	@Bean(name="redisTemplate")
	RedisTemplate<String, Postinfo> redisTemplate() {
		RedisTemplate<String, Postinfo> redisTemplate = new RedisTemplate<String, Postinfo>();
		redisTemplate.setConnectionFactory(jedisConnFactory());
		redisTemplate.setKeySerializer(redisSerializer());
		redisTemplate.setHashKeySerializer(redisSerializer());
		redisTemplate.setHashValueSerializer(redisSerializer());
		//redisTemplate.setHashValueSerializer(new JacksonJsonRedisSerializer<TeacherFeed>(TeacherFeed.class));
		return redisTemplate;
	}

	@Bean
	public StringRedisSerializer redisSerializer(){
		return new StringRedisSerializer();
	}
}
