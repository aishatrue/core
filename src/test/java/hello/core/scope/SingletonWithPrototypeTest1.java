package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);



    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);




    }

   @Scope("singleton")
   @Component
    static class ClientBean{
//        private final PrototypeBean prototypeBean; //생성시점에 주입 x01

       @Autowired
       private Provider<PrototypeBean> prototypeBeanProvider;


//       @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//           this.prototypeBean = prototypeBean;
//       }

       public int logic(){

           PrototypeBean prototypeBean = prototypeBeanProvider.get();

           prototypeBean.addCount();
           int count = prototypeBean.getCount();
           //cmd+option+n 하면 합쳐짐.
           return count;
       }
   }

//    이런 경우에 프로토타입빈이 각각 생김
//    @Scope("singleton")
//    @Component
//    static class ClientBean2{
//        private final PrototypeBean prototypeBean; //생성시점에 주입 x02
//
//
//        @Autowired
//        public ClientBean2(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic(){
//
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            //cmd+option+n 하면 합쳐짐.
//            return count;
//        }
//    }
    @Scope("prototype")
    @Component
    static class PrototypeBean{
        private  int count =0;
        public void addCount(){
                count++;
            }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init"+this);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }

    }
}
