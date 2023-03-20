package cn.codebro.fortress.system.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-01-03 12:36:20
 */
@Configuration
@ConfigurationProperties(prefix = "fortress.upload")
public class FileUploadProperties {
    private String localPath;
    private Long localStoreMaxSize;
    private String cacheKey;

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public Long getLocalStoreMaxSize() {
        return localStoreMaxSize;
    }

    public void setLocalStoreMaxSize(Long localStoreMaxSize) {
        this.localStoreMaxSize = localStoreMaxSize;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        String finalCacheKey = cacheKey;
        if (StrUtil.isBlank(finalCacheKey)) {
            finalCacheKey = "UPLOAD_ID:";
        }
        this.cacheKey = finalCacheKey;
    }
}
