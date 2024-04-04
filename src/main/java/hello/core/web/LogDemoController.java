package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    //myLogger를 주입받는게 아니라,찾을 수 있는 DL할 수 있는애가 주입되는것.
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private  final MyLogger myLogger;
    //myLogger를 컨테이너에서 내놔야 하는데, request scope인데 request가 없음. 고객요청이 들어와서 나갈때까지가 생존범윈데, 리퀘스트 준게 없음. -> provider를 쓰자.


//    @Autowired
//    public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
//        this.logDemoService = logDemoService;
//        this.myLogger = myLogger;
//    }


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject(); //정확히 이 시점에 만들어지는것.


        System.out.println("myLoggeer = " +myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testID");
        return "OK";



    }

}
