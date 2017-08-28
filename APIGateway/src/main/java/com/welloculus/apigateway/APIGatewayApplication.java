package com.welloculus.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.welloculus.apigateway.util.CustomLogger;

@Configuration
@SpringBootApplication
public class APIGatewayApplication{

  static CustomLogger logger = CustomLogger.getLogger();
  
  public static void main(String[] args) {
    SpringApplication.run(APIGatewayApplication.class, args);
  }

}
