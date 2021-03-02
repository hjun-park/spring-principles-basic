package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor    // private final 붙은 키워드에 값을 넣을 수 있는 생성자를 자동 생성 ( Autowired도 넣어주고 )
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;    // http request가 들어와서 나갈 때까지에만 사용할 수 있음 ( 스코프 범위가 request니까 )
                                                                // 근데 스프링 컨테이너 실행 시점엔 Http Request가 들어오지는 않음
                                                                // 없는데 자꾸 의존성 달라고 하니까 실행 시 오류가 나타난다.
                                                                // 해결방법 : Provider 사용하면 됨


    // 컨트롤러 요청 받으면 http request가 들어온 상태이므로 스코프 꺼낼 수 있음
    @RequestMapping("log-demo") // 01. URL에 log-demo 요청을 받음
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {

        String requestURL = request.getRequestURL().toString(); // 사용자 요청 URL을 읽어들여 변수에 저장

        System.out.println("myLogger = " + myLogger.getClass());  // MyLogger 의존관계에 가짜 프록시가 개입됨
//        MyLogger myLogger = myLoggerProvider.getObject();       // 02. Object를 생성
        myLogger.setRequestURL(requestURL); // Setter             // 04. 요청받은 URL을 SET

        myLogger.log("controller test");                        // 05. Log를 찍는다. ( 이미 uuid와 URL이 있기 때문에 모두 로깅 )
        Thread.sleep(10000);
        logDemoService.logic("testId");                      // 06. Log 출력

        return "OK";
    }
}
