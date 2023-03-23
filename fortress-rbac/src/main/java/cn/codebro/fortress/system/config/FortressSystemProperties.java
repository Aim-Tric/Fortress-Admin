package cn.codebro.fortress.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-21 22:02:21
 */
@Configuration
@ConfigurationProperties(prefix = "fortress")
public class FortressSystemProperties {

    private String systemName;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
