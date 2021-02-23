package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // default로 지정하지 않으면 hello.core로부터 시작해서
//        // hello.core.member부터 componentScan을 수행하도록 설정
//        basePackages = "hello.core.member",
//        // autoappconfig 클래스부터 탐색 시작 즉, hello.core 부터 시작
//        basePackageClasses = AutoAppConfig.class,
        // 스프링 빈 자동 등록에서 제외 ( 여기서는 AppConfig를 제외함, 즉 @Configuration 이라는 annotation 달린 클래스는 빈에 자동등록 X )
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
