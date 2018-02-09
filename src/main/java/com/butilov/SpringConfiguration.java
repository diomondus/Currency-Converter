package com.butilov;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */

import com.butilov.services.RatesDeserializerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.butilov")
public class SpringConfiguration {
    @Bean
    public RatesDeserializerService getRatesDeserializerBean() {
        return new RatesDeserializerService();
    }
}