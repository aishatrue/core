package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycle {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();


    }

    @Configuration
    static class LifeCycleConfig{
//        @Bean(initMethod = "init",destroyMethod = "close")
        @Bean
        //예를 들어, 이 네트워크 클라이언트가 내가 짠게 아니고 외부 클래스라 치면, 여기에다가 어노테이션 못붙일거 아님? 그니까 Bean의 init,destroy붙이라는거.
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }


}
