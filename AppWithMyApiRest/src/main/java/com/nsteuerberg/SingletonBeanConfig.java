package com.nsteuerberg;

import com.nsteuerberg.controller.ApiUse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.nsteuerberg")
public class SingletonBeanConfig {

    @Bean
    public ApiUse apiUse(){
        return new ApiUse();
    }
}
