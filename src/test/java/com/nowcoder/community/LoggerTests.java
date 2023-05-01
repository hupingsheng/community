package com.nowcoder.community;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
@Slf4j
public class LoggerTests {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTests.class);


    @Test
    public void testLogger(){
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }
}
