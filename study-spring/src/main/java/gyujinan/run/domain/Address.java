package gyujinan.run.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * @author agj017@gmail.com
 * @since 2021/09/13
 */

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;


    //JPA 스펙상 기본 생성자를 생성해줘야됨. 다른 곳에서 호출되지 않도록 protected를 추가할수 있음
    //JPA 구현 라이브러리가 객체를 생성할 떼 리플랙션 같은 기술을 사용 할 수 있도록 지원해야되기 때문에
    //리플랙션이란 어떤 기술일까?
    protected Address() {

    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


}
