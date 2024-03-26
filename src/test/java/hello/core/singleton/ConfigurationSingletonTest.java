package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //각 구현체에 만들어놓은 get메소드 꺼낼려고, 구체화된 클래스 타입 적은것
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberService -> memberRepository()= " + memberRepository1);
        System.out.println("orderService-> memberRepository() = " + memberRepository2);
        System.out.println(memberRepository);

        assertThat(memberRepository1).isSameAs(memberRepository2);


    }
    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //CGLIB이 사실 등록된거임. 근데 상속받았기 때문에, AppConfig.class해도 튀어나옴.
        //@Configuration빼면 순수한. 빼도 Bean등록은 됨. => 그러나 memberRepository가 세번호출돼서 싱글톤 깨짐.
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());

    }
}
