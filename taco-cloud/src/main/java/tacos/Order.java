package tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import tacos.jpa.entity.TacoEntity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2021/10/12
 */

@Data
public class Order {

    private Long id;

    private Date placedAt;

    @NotBlank(message="Name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private List<TacoEntity> tacos = new ArrayList<>();

    public void addDesign(TacoEntity design){
        this.tacos.add(design);
    }
}
