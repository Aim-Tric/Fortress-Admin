package cn.codebro.fortresssystem;

import cn.codebro.fortresssystem.pojo.SystemInfo;
import cn.codebro.fortresssystem.service.ISystemService;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


@SpringBootApplication
public class FortressSystemApplication {

    private static final Logger logger = LoggerFactory.getLogger(FortressSystemApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FortressSystemApplication.class, args);
        logger.info("正在检查系统初始化状态...");
        ISystemService systemService = context.getBean(ISystemService.class);
        if (!systemService.initialized()) {
            logger.info("系统未进行初始化，进行预初始化操作...");
            systemService.preInitializeSystem();
            logger.info("预初始化完成...");
        }

        SystemInfo systemInfo = systemService.getSystemInfo();
        String systemName = systemInfo.getSystemName();

        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path", "/");
        logger.info("{}已经运行在端口：{}，API地址：http://localhost:{}{}", systemName, port, port, contextPath);
    }

}
