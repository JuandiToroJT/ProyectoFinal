package juanalcuadradosas.soc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "juanalcuadradosas.soc.Entity")
public class SOCApplication {
    public static void main(String[] args) {
        SpringApplication.run(SOCApplication.class, args);
    }
}
