package test.task.reportservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("test.task")
@EntityScan("test.task.csproj.events.CountryKafkaEvent")
public class ReportConfig {
}