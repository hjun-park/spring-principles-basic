package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok이 있으면 자동으로 getter, setter 만들어준다.
// 생성자 만들어주는 기능도 있음
@Setter
@Getter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("asdf");
        String name = helloLombok.getName();

        System.out.println("name = " + name);
    }
}
