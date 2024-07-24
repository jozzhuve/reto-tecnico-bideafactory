package pe.bideafactory.api.booking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "api.booking")
public class BookConfigurationProperties {

  private DiscountApiProperties discountApi;

}
