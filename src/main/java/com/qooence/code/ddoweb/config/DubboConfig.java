package com.qooence.code.ddoweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations= {"classpath*:META-INF/client/*.xml"})
public class DubboConfig {

}
