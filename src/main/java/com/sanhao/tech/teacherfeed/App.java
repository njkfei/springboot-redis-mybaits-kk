package com.sanhao.tech.teacherfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class App 
{
  public static void main( String[] args )
  {
  	 SpringApplication.run(App.class, args);
  }
}
