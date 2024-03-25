package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        OrderService orderService1 = appConfig.orderService();
    }


    @Test
    void createOrder(){
        //given
        Member member = new Member(1L,"test", Grade.VIP);
        memberService.join(member);



        //when
        Order order = orderService.createOrder(member.getId(), "도라에몽", 3000);


        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }
}
