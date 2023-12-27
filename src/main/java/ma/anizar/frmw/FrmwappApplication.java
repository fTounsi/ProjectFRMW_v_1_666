package ma.anizar.frmw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FrmwappApplication {

  public static void main(String[] args) {
    SpringApplication.run(FrmwappApplication.class, args);
  }
}
