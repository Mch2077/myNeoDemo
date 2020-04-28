package mig.chen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Ethan Chain 陈科军
 **/
@SpringBootApplication
@EnableNeo4jRepositories("mig.chen.repositories")
public class SampleNeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleNeoApplication.class, args);
    }
}