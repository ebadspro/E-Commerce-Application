package segments.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "segments.info.repository")
@ComponentScan(basePackages = {"segments.info.controller", "segments.info.auth", "segments.info.services"})
@EntityScan(basePackages = "segments.info.entities")

public class SegmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SegmentsApplication.class, args);
    }

}
