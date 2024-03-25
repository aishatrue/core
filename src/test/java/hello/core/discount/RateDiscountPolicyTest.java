package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RateDiscountPolicyTest {
    MemberService memberService;
 DiscountPolicy discountPolicy;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        discountPolicy = appConfig.discountPolicy();

    }

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    void discountPrice_o(){

        //given
        Member member = new Member(1L,"ㅇㅜ와우", Grade.VIP);
        memberService.join(member);

        //when
        int discountPrice = discountPolicy.discountPrice(member, 3000);

        //then
        assertThat(discountPrice).isEqualTo(300);

    }
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void discountPrice_x(){
        //given
        Member member = new Member(1L,"ㅇㅜ와우", Grade.BASIC);
        memberService.join(member);

        //when
        int discountPrice = discountPolicy.discountPrice(member, 3000);

        //then
        assertThat(discountPrice).isEqualTo(0);



    }

}