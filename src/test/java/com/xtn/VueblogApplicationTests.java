package com.xtn;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VueblogApplicationTests {

    @Test
    void contextLoads() {

        String s = SecureUtil.md5("123");
        System.out.println(s);

    }

}
