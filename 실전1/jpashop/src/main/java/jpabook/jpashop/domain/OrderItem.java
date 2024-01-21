package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected 생성자와 같은 의미
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id") // 하나의 order는 여러 개의 item을 가질 수 있음
    private Order order;

    private int orderPrice; // 주문가격
    private int count; // 주문수량

    /**
     * 어디서는 생성메서드로 객체생성하고 어디서는 new로 객체생성하는 등 다르게 하면 유지보수가 어려워진다!!!
     * 이를 막기 위해 기본생성자의 접근제한자를 protected로 하면 new로 만든 객체생성에 빨간줄이 뜨게 된다!
     * ( ∵ jpa에서 protected를 쓰면 이건 쓰지 말라는 소리와 같음)
     */
//    protected OrderItem() {
//
//    }

    // OrderItem 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    // 비즈니스 로직
    /**
     * 주문 상품 취소
     */
    public void cancel() {
        getItem().addStock(count); // 재고수량 원복
    }

    /**
     * 주문 상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}

