package tern.block.demo;

import javax.annotation.PostConstruct;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@SpringBootApplication
@EnableZuulProxy
@MapperScan(basePackages = "tern.block.demo.*",annotationClass = Mapper.class)
@EnableAutoConfiguration
@EnableFeignClients
public class TernDemoApplication {
    
	
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate = null;
	
	//使用@PostConstruct,使得Spring IOC 容器在装配当前Bean的时候，调度这个方法

    @PostConstruct
    public void init(){
        //获取字符串序列化器
        RedisSerializer<String> strSerializer = redisTemplate.getStringSerializer();
        //设置Redis键（key）序列化器
        redisTemplate.setKeySerializer(strSerializer);
        //redisTemplate.setValueSerializer(strSerializer);
        //设置Redis散列结构字段(field)序列化器
        redisTemplate.setHashKeySerializer(strSerializer);
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(TernDemoApplication.class, args);
	}

}
