package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    // 스프링 빈은 객체 생성 -> 의존관계 주입 순의 라이프사이클이 가진다.
    // 객체 생성하고 의존관계 주입이 되어야 setter를 통해 값을 입력받을 수 있다.
    // 그런데 의존관계 주입이 완료된 시점을 어떻게 알 수 있을까 ?
    //   => 스프링은 의존관계 주입이 완료되면 스프링 빈에게 callback 메소드를 보냄으로써 초기화 시점을 알려준다.
    //   => 또한 스프링은 스프링 컨테이너가 종료되기 전에 소멸 callback 을 보냄으로써 종료작업을 알려준다.

    // 스프링 빈의 event life cycle을 보자면
    // "스프링 컨테이너(DI) 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

    // 빈 생명주기 콜백 3가지
    //  1) 인터페이스 (InitializingBean, DisposableBean)
    //  2) 설정 정보 초기화 메소드, 종료 메소드 지정
    //  3) @PostConstruct, @PreDestory annotation 지정

    @Test
    public void lifeCycleTest() {
        // 애플리케이션 컨텍스는 열고 사용했으면 닫아줘야 한다. close는 ConfigurableApplicationContext에 있는 메소드
        // ConfigurableApplicationContext는 AnnotationConfigApplicationContext의 부모 클래스 이기 때문에 아래처럼 담는게 가능
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();  // 객체를 생성한 다음
            networkClient.seturl("http://hello-spring.dev");    // setter 이용 설정을 집어넣어줌 이렇게 하면 생성자 connect에 null값이 출력됨
            return networkClient;
        }
    }
}
