package org.lvmp.emailapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;

@Configuration
public class AwsConfig {

    @Bean
    public SesV2Client sesV2Client() {
        return SesV2Client.builder().region(Region.AF_SOUTH_1).build();
    }
}
