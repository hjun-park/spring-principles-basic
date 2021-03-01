package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static hello.core.scope.PrototypeTest.*;
import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {

        // DL 1) ObjectProvider - DL을 위한 편의기능 제공, 스프링 외 별도 의존관계 추가 필요 없기 때문에 편리 ( 추천 )
        // private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        // DL 2) JAVA JSR-330 Provider - 자바 표준, 다른 컨테이너에서도 사용할 수 있어야 한다면 이걸 사용.
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            // 해당하는 빈을 찾아서반환 ( DL; Dependency Lookup ) - 필요할 때마다 그때그때 생성해서 할 수 있도록
            // PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // DL 1번 방식
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // DL 2번 방식
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
