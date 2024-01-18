package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // @Component를 포함했기 때문에 스프링 빈으로 등록해줌
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // 스프링+어노테이션을 통해 엔티티 매니저 주입받음. spring data jpa의 지원으로 @Autowired로 바꾸기 가능
//    => 얘 역시 @RequiredArgsConstructor로 바꿀 수 있음 => 필드에 final 추가 필수
    private final EntityManager em;

    // 영속성 컨텍스트에 멤버 엔티티를 넣음 -> 트랜잭션이 커밋되는 시점에 DB에 반영됨
    public void save(Member member) {
        em.persist(member);
    }

    // 단건 조회. find(타입, pk)
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // JPQL을 사용함. from의 대상이 테이블이 아닌 엔티티라고 보면 됨
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 파라미터 바인딩해서 특정 이름의 회원만 찾는 메소드
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name=name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
