package cn.codebro.fortresssystem;

import cn.codebro.fortresssystem.pojo.SystemInfo;
import cn.codebro.fortresssystem.service.ISystemService;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

@SpringBootApplication
public class FortressSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FortressSystemApplication.class, args);
        System.out.println("正在检查系统初始化状态...");
        ISystemService systemService = context.getBean(ISystemService.class);
        if (!systemService.initialized()) {
            System.out.println("系统未进行初始化，进行预初始化操作...");
            systemService.preInitializeSystem();
            System.out.println("预初始化完成...");
        }

        SystemInfo systemInfo = systemService.getSystemInfo();
        String systemName = systemInfo.getSystemName();

        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path", "/");
        System.out.println(StrUtil.format("{}已经运行在端口：{}，API地址：http://localhost:{}{}", systemName, port, port, contextPath));
    }

}
