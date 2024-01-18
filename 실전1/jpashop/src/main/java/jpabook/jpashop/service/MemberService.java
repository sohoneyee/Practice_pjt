package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽는 기능에만 사용하자. JPA가 조회하는 곳에서 성능을 최적화할 수 있음
//@AllArgsConstructor // 필드 모든 걸 가지고 생성자 생성
@RequiredArgsConstructor // final이 있는 필드만 가지고 자동생성자 생성
public class MemberService {

    private final MemberRepository memberRepository; // final로 가는 것을 추천함

//    @Autowired // 생성자가 하나만 있다면 알아서 자동injection을 해줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원가입
    @Transactional // 따로 달아주면 얘는 false라는 뜻임. springframework에서 제공되는 것을 사용하자
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId(); // save에서 persist->영속성 컨텍스트에 키(pk)와 밸류가 들어감=>무조건 값이 있음
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 한 명 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}



