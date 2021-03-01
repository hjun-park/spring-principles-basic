package hello.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient { // implements InitializingBean, DisposableBean { // Command + B 해당 이동  / Ctrl + Alt + <- 로 복귀

    private String url;
    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void seturl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("url = " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    // 03.PostConstruct, PreDestroy 를 가장 많이 사용함 단순히 Annotation을 호출함으로써 빈 생성과 소멸을 담당할 수 있음
    @PostConstruct
    public void init() {
        System.out.println("NetworkCleint.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
