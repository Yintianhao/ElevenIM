package cn.izzer.elevenimserver;

import cn.izzer.elevenimserver.register.ZKRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevenimServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ElevenimServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Thread thread = new Thread(new ZKRegistry());
        thread.setName("zk register");
        thread.start();
    }
}
