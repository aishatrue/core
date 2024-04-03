package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    private AnnotationConfigApplicationContext ac;

    @Test
    void prototypeBeanFind(){
        //지금 이거는 config파일을 올려서, 그안의 bean들을 올리는게 아니라.
        //아예 component어노테이션이 달린 애들을 바로 컨테이너에 등록하는거임.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isNotSameAs(bean2);

        ac.close();


    }

    @Component
    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeConfig.init");

        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeConfig.destroy");
        }


    }
}
