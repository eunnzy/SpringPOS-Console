package com.pos;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.pos.console.UserConsole;

@SpringBootApplication
public class POSApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:*appCtx.xml");
		UserConsole usercs = (UserConsole)ctx.getBean("userConsole");
		usercs.start(); // 로그인 화면
	}
}
