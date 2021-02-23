package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 배우가 상대 대역을 직접 데려오는 꼴 ( 직무 분리 필요 )
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 이렇게 생성자에 붙여주면 의존관계 주입을 자동으로 해줌 ( Component로 빈을 등록하고 Autowired를 이용해서 자동으로 주입 )
    @Autowired  // ac.getBean(MemberRepository.class)  이렇게 주입된다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
