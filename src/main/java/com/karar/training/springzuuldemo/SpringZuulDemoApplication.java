package com.karar.training.springzuuldemo;

import com.karar.training.springzuuldemo.filter.impl.ChangeUriFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class SpringZuulDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuulDemoApplication.class, args);
	}

	@Bean
	public ChangeUriFilter changeUriFilter(){
		return new ChangeUriFilter();
	}

}
