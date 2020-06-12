package Utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName TestLogBack
 * @Description 测试日志是否引入成功
 * @Author wk
 * @Date 2020/6/12 0012 08:58
 * @Version 1.0
 */
public class TestLogBack {
    Logger log = LoggerFactory.getLogger(TestLogBack.class);

    @Test
    public  void Testlogger() {
        //日志级别由低到高
        log.trace("hello trace");
        log.debug("hello debug");
        log.info("hello info" );
        log.warn("hello warn");
        log.error("hello error");
    }
}
