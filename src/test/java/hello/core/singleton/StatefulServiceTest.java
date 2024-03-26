package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //threadA: A사용자 10000원 주문
        int userA = statefulService1.order("userA", 10000);

        //threadB: B사용자 20000원 주문
        int userB = statefulService2.order("userB", 20000);


        //threadA: 사용자 A가 주문 금액 조회. 원래는 10000원이 나와야하지만,,싱글톤이라서 안됨ㅁ.
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userA);

        Assertions.assertThat(userA).isEqualTo(10000);


    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }

}