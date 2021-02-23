package hello.core.singleton;

public class SingletonService {

    // static level에 올라가기 떄문에 해당 변수는 하나만 존재할 수 있다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }



}
