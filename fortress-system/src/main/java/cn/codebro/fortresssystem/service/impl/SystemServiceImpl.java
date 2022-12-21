package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresssystem.config.FortressSystemProperties;
import cn.codebro.fortresssystem.persistence.mapper.SystemMapper;
import cn.codebro.fortresssystem.pojo.SystemInfo;
import cn.codebro.fortresssystem.service.ISystemService;
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
        // 加载系统配置到redis
        SystemInfo systemInfo = getOne(new QueryWrapper<>());
        redisTemplate.opsForValue().set(SYSTEM_CONFIG_KEY, systemInfo);
    }

    @Override
    public SystemInfo getSystemInfo() {
        SystemInfo systemInfo = (SystemInfo) redisTemplate.opsForValue().get(SYSTEM_CONFIG_KEY);
        if (ObjectUtil.isNull(systemInfo)) {
            systemInfo = getOne(new QueryWrapper<>());
            redisTemplate.opsForValue().set(SYSTEM_CONFIG_KEY, systemInfo);
        }
        return systemInfo;
    }

    @Override
    public boolean initialized() {
        SystemInfo systemInfo = getSystemInfo();
        return ObjectUtil.isNotNull(systemInfo) && systemInfo.getInitialized();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void preInitializeSystem() {
        synchronized (SystemServiceImpl.class) {
            if (!initialized()) {
                SystemInfo info = new SystemInfo();
                info.setId(UUID.fastUUID().toString(true));
                info.setSystemName(fortressSystemProperties.getSystemName());
                info.setInitialized(false);
                save(info);
            }
        }
    }

    @Override
    public void initializeSystem(SystemInfo systemInfo) {

    }


}
