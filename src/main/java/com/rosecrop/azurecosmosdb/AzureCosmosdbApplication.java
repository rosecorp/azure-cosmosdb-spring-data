package com.rosecrop.azurecosmosdb;

import com.rosecrop.azurecosmosdb.domain.User;
import com.rosecrop.azurecosmosdb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootApplication
public class AzureCosmosdbApplication implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(AzureCosmosdbApplication.class);

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(AzureCosmosdbApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final User testUser = new User("test@test.com", "testFirstName", "testLastName");

        repository.deleteAll();
        repository.save(testUser);

        final User result1 = repository.findByLastName("testLastName").stream().findFirst().get();

        System.out.println("count users: " + repository.count());

        Assert.state(result1.getFirstName().equals(testUser.getFirstName()), "query result firstName doesn't match!");
        Assert.state(result1.getLastName().equals(testUser.getLastName()), "query result lastName doesn't match!");

        LOGGER.info("findOne in User collection get result: {}", result1.toString());
        System.out.println("findOne in User collection get result:" + result1.toString());

        final List<User> result2 = repository.findByFirstName(testUser.getFirstName());

        Assert.state(result2.get(0).getFirstName().equals(testUser.getFirstName()),
                "query result firstName doesn't match!");
        Assert.state(result2.get(0).getLastName().equals(testUser.getLastName()),
                "query result lastName doesn't match!");

        LOGGER.info("findByFirstName in User collection get result: {}", result2.get(0).toString());
        System.out.println("findByFirstName in User collection get result:" + result2.get(0).toString());

        final List<User> result3 = repository.findByLastName(testUser.getLastName());

        Assert.state(result3.get(0).getFirstName().equals(testUser.getFirstName()),
                "query result firstName doesn't match!");
        Assert.state(result3.get(0).getLastName().equals(testUser.getLastName()),
                "query result lastName doesn't match!");

        LOGGER.info("findByFirstName in User collection get result: {}", result3.get(0).toString());
        System.out.println("findByFirstName in User collection get result:" + result3.get(0).toString());


    }
}
