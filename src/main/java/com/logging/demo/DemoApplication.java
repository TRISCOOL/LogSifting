package com.logging.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	private Logger logger2 = LoggerFactory.getLogger("testLog");

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/**
		 * SiftingAppender 基于不同的标识，对不同任务输出到不同的日志文件
		 */
		MDC.put("taskId","taskA"); //该值会存在当前线程，并会根据taskA 将日志输出到 taskA.log 文件
		logger.info("任务A 日志输出..."); //该日志会输出到taskA
		MDC.put("taskId","taskB");
		logger.info("任务B 日志输出...");
		MDC.remove("taskB"); //清除标识


		/**
		 * 指定某行日志，输出到某个文件
		 */
		logger2.info("将该行日志输出到某一行");
	}
}
