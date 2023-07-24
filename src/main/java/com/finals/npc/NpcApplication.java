package com.finals.npc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.finals.npc")
public class NpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(NpcApplication.class, args);
	}

}
