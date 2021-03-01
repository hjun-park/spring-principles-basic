package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final 붙은 변수를 만들고 생성자를 만들어줌 ( 주석처리한 부분 )
                        // 확인할 수 있는 부분은 Navigate -> File Structure 가서 확인할 수 있음
                        // final 붙은 변수에 Autowired 붙이는 것보다 무지 간단한 방법
public class OrderServiceImpl implements OrderService {

    // 주문에는 회원정보가 필요하니까 회원리포지토리 불러오기
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 할인된 가격이 필요하므로 고정할인가격정책 불러오기
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // final키워드는 기본생성자로만 할당 가능

//    @Autowired  // 스프링이 자동으로 생성될 때 아래 2가지 인자를 자동으로 의존관계를 자동으로 주입해줌
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 멤버 찾고
        Member member = memberRepository.findById(memberId);
        // 난 모르겠고 멤버와 상품가격을 넘겨줄게
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
