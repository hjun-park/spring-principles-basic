package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// annotation 클래스에 있는 부분 복붙
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")    // 추가적으로 이 부분도 작성
public @interface MainDiscountPolicy {  // annotation 방법 말고 웬만하면 primary로 해결하는 것도 좋긴 하다.
                                        // command + B를 누르면 추적 가능 ( 어디서 통해서 어디로 호출되는지 : 인텔리제이 기능 )
}
