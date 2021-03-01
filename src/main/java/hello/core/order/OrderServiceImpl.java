package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // 주문에는 회원정보가 필요하니까 회원리포지토리 불러오기
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 할인된 가격이 필요하므로 고정할인가격정책 불러오기
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // final키워드는 기본생성자로만 할당 가능

//    // qualifier는 중복된 스프링 빈이 2개가 있으면 해당 지정해 준 것을 가지고 의존관계 주입을 해준다.
//    @Autowired  // 스프링이 자동으로 생성될 때 아래 2가지 인자를 자동으로 의존관계를 자동으로 주입해줌
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // Primary 어노테이션이 들어간 걸 우선적으로 해준다. ( 이 방법이 제일 Best인 듯 )
    @Autowired  // 스프링이 자동으로 생성될 때 아래 2가지 인자를 자동으로 의존관계를 자동으로 주입해줌
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 멤버 찾고
        Member member = memberRepository.findById(memberId);
        // 난 모르겠고 멤버와 상품가격을 넘겨줄게
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
