package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetApiApplication.class, args);

//		String url="https://robotstakeover20210903110417.azurewebsites.net/robotcpu";
//
//		WebClient.Builder builder=WebClient.builder();
//
//		builder.build()
//				.get()
//				.uri(url)
//				.retrieve()
//				.bodyToMono(String.class)
//				.map(catFact -> {
//					System.out.println("----------------");
//					System.out.println(catFact);
//					System.out.println("-----------");
//					return catFact; // You can return the result if needed
//				})
//				.subscribe();
	}

}
