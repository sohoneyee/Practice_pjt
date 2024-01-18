package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ordinal : 컬럼에 숫자가 들어감 -> ready와 comp가 아닌 다른 상태가 나오면 개망ㅎ이므로 무조건 String
    private DeliveryStatus status; // [READY, COMP]
}
