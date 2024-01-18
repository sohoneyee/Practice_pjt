package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 어딘가에 내장이 될 수 있다
@Getter // 값타입은 기본적으로 변경불가능해야함 -> setter는 제공하지 않음 => 생성자에서 값을 모두 초기화하여 변경불가능한 클래스를 생성
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
