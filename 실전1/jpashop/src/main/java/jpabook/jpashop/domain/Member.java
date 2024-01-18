package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter // setter는 가급적이면 사용하지 않는 것을 권장함
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    
    @Embedded // 내장타입을 갖고 있다를 명시
    private Address address;

    @OneToMany(mappedBy = "member") // 하나의 멤버가 주문이 여러개(일대다) & order클래스에 있는 member필드의 거울에 해당한다.
    private List<Order> orders = new ArrayList<>();
}
