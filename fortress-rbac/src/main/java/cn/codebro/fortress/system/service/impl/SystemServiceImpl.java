package cn.codebro.fortress.system.service.impl;

import cn.codebro.fortress.system.config.FortressSystemProperties;
import cn.codebro.fortress.system.persistence.mapper.SystemMapper;
import cn.codebro.fortress.system.pojo.SystemInfo;
import cn.codebro.fortress.system.service.ISystemService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-21 17:21:59
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, SystemInfo> implements ISystemService {
    private static final String SYSTEM_CONFIG_KEY = "system:config";

    private final RedisTemplate<Object, Object> redisTemplate;

    private final FortressSystemProperties fortressSystemProperties;

    public SystemServiceImpl(RedisTemplate<Object, Object> redisTemplate, FortressSystemProperties fortressSystemProperties) {
        this.redisTemplate = redisTemplate;
        this.fortressSystemProperties = fortressSystemProperties;
    }

    @PostConstruct
    public void initialize() {
        this.refresh();
    }

    @Override
    public SystemInfo getSystemInfo() {
        SystemInfo systemInfo = (SystemInfo) redisTemplate.opsForValue().get(SYSTEM_CONFIG_KEY);
        if (ObjectUtil.isNull(systemInfo)) {
            systemInfo = this.refresh();
        }
        return systemInfo;
    }

    @Override
    public boolean initialized() {
        SystemInfo systemInfo = this.getSystemInfo();
        return ObjectUtil.isNotNull(systemInfo) && systemInfo.getInitialized();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void preInitializeSystem() {
        if (!initialized() && ObjectUtil.isNull(getSystemInfo())) {
            SystemInfo defaultSystemInfo = this.getDefaultSystemInfo();
            this.save(defaultSystemInfo);
            this.refresh();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void initializeSystem(SystemInfo initializedSystemInfo) {
        SystemInfo uninitializedSystemInfo = this.getSystemInfo();
        String id = uninitializedSystemInfo.getId();
        initializedSystemInfo.setId(id);
        initializedSystemInfo.setInitialized(true);
        initializedSystemInfo.setInitializeTime(DateUtil.date().toJdkDate());
        this.updateById(initializedSystemInfo);
        this.refresh();
    }

    private SystemInfo refresh() {
        SystemInfo systemInfo = getOne(new QueryWrapper<>());
        redisTemplate.opsForValue().set(SYSTEM_CONFIG_KEY, systemInfo);
        return systemInfo;
    }

    private SystemInfo getDefaultSystemInfo() {
        SystemInfo info = new SystemInfo();
        info.setId(UUID.fastUUID().toString(true));
        info.setSystemName(fortressSystemProperties.getSystemName());
        info.setInitialized(false);
        info.setVersion(1);
        return info;
    }



}
