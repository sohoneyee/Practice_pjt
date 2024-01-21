package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


//@Runwith(SpringRunner.class) // junit 실행할 때 스프링과 같이 실행한다는 뜻. junit5 이후로는 안써도 지원됨
@SpringBootTest // springboot를 띄운 상태로 테스트함
@Transactional // commit을 하는 순간 flush를 하면서 영속성 컨텍스트에 있는 것이 insert됨 -> rollback을 false로 둬야 commit됨
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    @Rollback(false) // select까지만 나오는 게 아닌 insert까지 되도록 false처리함.
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);

        // then
        // assertThrows(예외 클래스, 예외로 throw하는 작업)
        assertThrows(IllegalStateException.class, ()->memberService.join(member2));
    }


}