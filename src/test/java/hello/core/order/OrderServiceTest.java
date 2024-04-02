package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder(){
        //given
        Member member = new Member(1L,"test", Grade.VIP);
        memberService.join(member);



        //when
        Order order = orderService.createOrder(member.getId(), "도라에몽", 3000);


        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(300);


    }
//    @Test
//    void fieldInjectionTest(){
//        OrderServiceImpl orderService1 = new OrderServiceImpl();
//        orderService1.setMemberRepository(new MemoryMemberRepository());
//        orderService1.setDiscountPolicy(new FixDiscountPolicy());
//        orderService1.createOrder(1L,"itemA",10000);
//
//    }
}
