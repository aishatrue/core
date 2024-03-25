package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {


    //다른 인스턴스 쓰는게 애매하다
//    MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join(){
        //given: 이런 환경이 주어졌을 때
        Member member = new Member(2L,"B",Grade.VIP);

        //when : 이렇게 했을 때
        memberService.join(member);


        //then : 이렇게 된다.
        Member member1 = memberService.findMember(member.getId());
        Assertions.assertThat(member).isEqualTo(member1);


    }

}
