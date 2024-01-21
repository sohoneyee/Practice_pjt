package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 한 테이블에 다 때려박기
@DiscriminatorColumn(name = "dtype") // value가 book이라면 어쩔건지 album이라면 어쩔건지 등
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직. 가격과 재고가 등록되어있는 item entity 자체로 해결할 수 있는 로직은 엔티티에 넣어놓음(객체지향)
    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity+=quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity-quantity;
        if (restStock<0) {
            throw new NotEnoughStockException("need more stock!!");
        }
        this.stockQuantity=restStock;
    }


}
