package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Qualifier("mainDiscountPolicy")   // 해당 방식은 타이핑 에러가 발생해도 컴파일 되는 경우가 있음
@MainDiscountPolicy                // 따라서 이렇게 만들어준 annotation을 집어넣어주면 타이핑을 잘못하면 컴파일 에러가 뜬다.
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
