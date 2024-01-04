package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository // @Component를 포함하고 있어 자동으로 스프링 빈이 등록됨
public class MemberRepository {
    @PersistenceContext // spring boot가 이 어노테이션으로 자동으로 주입해줌
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 커맨드와 쿼리를 분리하기 위해 member 객체가 아닌 id만 조회하도록 설계함
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
