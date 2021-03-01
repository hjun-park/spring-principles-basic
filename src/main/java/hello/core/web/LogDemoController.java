package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor    // private final 붙은 키워드에 값을 넣을 수 있는 생성자를 자동 생성 ( Autowired도 넣어주고 )
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;    // http request가 들어와서 나갈 때까지에만 사용할 수 있음 ( 스코프 범위가 request니까 )
                                        // 근데 스프링 컨테이너 실행 시점엔 Http Request가 들어오지는 않음
                                        // 없는데 자꾸 의존성 달라고 하니까 실행 시 오류가 나타난다.
                                        // 해결방법 : Provider 사용하면 됨

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString(); // 사용자 요청 URL을 읽어들여 변수에 저장
        myLogger.setRequestURL(requestURL); // Setter

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
