package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 역할의 분리
// 여기서는 실제 동작 필요한 구현 객체를 생성
@Configuration
public class AppConfig {

    // DI : 생성자 주입
    // extract func : option + command + M
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        // 멤버 서비스를 생성하고 그에 대한 참조값인 리포지토리를 넘겨준다.
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // 주문하는 서비스도 마찬가지로 화원정보는 메모리 리포지토리 회원에 대한 할인정책도 따로 만들어준다.
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }


}
