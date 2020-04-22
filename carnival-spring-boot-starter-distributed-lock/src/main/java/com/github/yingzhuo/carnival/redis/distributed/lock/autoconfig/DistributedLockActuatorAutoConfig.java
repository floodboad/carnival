/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.redis.distributed.lock.autoconfig;

import com.github.yingzhuo.carnival.redis.distributed.lock.actuator.DistributedLockHealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * @author 应卓
 * @since 1.5.1
 */
@ConditionalOnClass(name = "org.springframework.boot.actuate.endpoint.annotation.Endpoint")
public class DistributedLockActuatorAutoConfig {

    @Bean
    public DistributedLockHealthIndicator distributedLockHealthIndicator() {
        return new DistributedLockHealthIndicator();
    }

}