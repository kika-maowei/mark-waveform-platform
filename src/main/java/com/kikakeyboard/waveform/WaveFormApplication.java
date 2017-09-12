package com.kikakeyboard.waveform;

import com.kikakeyboard.waveform.config.ESConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@MapperScan("com.kikakeyboard.waveform.mapper")
@Import({
        ESConfiguration.class
})
public class WaveFormApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WaveFormApplication.class, args);
    }
}
