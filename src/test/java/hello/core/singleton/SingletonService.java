package hello.core.singleton;

public class SingletonService {

    //여기서 딱 한번 생성되고 초기화 됨.
    private static final SingletonService instance = new SingletonService();

    //이 메소드를 호출하면 항상 같은 인스턴스를 반환.
    public static SingletonService getInstance(){
        return instance;
    }

    //이건 바깥에서 생성되는거 막는 기본 생성자
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }



}
