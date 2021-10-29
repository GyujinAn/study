package tacos.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author agj017@gmail.com
 * @since 2021/10/28
 */

@Data
@Component
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {

    @Min(value=5, message = "must be between 5 and 25")
    @Max(value=25, message = "must be between 5 and 25")
    private int pageSize = 20;
}
