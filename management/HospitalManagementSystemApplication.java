package com.hospital.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class HospitalManagementSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}
   @Bean
   public PlatformTransactionManager add(MongoDatabaseFactory dbFactory)
   {
	   return new MongoTransactionManager(dbFactory);
   }
}
