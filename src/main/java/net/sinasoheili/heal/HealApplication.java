package net.sinasoheili.heal;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ApplicationPath("/api")
public class HealApplication extends ResourceConfig {

	public HealApplication() {
		packages("net.sinasoheili.heal");
	}

	public static void main(String[] args) {
		SpringApplication.run(HealApplication.class, args);
	}

}
