package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    // psvm
    public static void main(String[] args) {

        // 기존방식
//        MemberService memberService = new MemberServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        // memberService 달라고 하면 appConfig에서 다 결정을 해서 memberServiceImpl 줌
//        MemberService memberService = appConfig.memberService();

        // 스프링 사용 ( 스프링 컨테이너가 모든 객체들을 관리함 )
        // appconfig에 있는 환경 설정을 가지고 @Bean 가지고 있는 것들을 스프링 컨테이너에 넣어서 다 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        // config에서 멤버서비스 이름을 적어주고 두번째는 반환타입을 적어주는 거임
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);


        // find member
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName()); // sout

    }
}
