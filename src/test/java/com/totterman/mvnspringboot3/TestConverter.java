package com.totterman.mvnspringboot3;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@TestConfiguration
public class TestConverter {
    
    @Bean
    @ConfigurationPropertiesBinding
    Converter<String, GrantedAuthority> converter() {
        return new Converter<String, GrantedAuthority>() {
            @Override
            public GrantedAuthority convert(String source) {
                return new SimpleGrantedAuthority(source);
            }
        };
    }
}
