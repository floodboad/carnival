/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.common.autoconfig;

import com.github.yingzhuo.carnival.exception.business.BusinessExceptionFactory;
import com.github.yingzhuo.carnival.exception.business.BusinessExceptionMap;
import com.github.yingzhuo.carnival.exception.business.EmptyBusinessExceptionFactory;
import com.github.yingzhuo.carnival.exception.business.MapBusinessExceptionFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 应卓
 */
@Lazy(false)
@EnableConfigurationProperties({
        BusinessExceptionMap.class,
        BusinessExceptionAutoConfig.Props.class
})
@ConditionalOnProperty(prefix = "carnival.business-exception", name = "enabled", havingValue = "true", matchIfMissing = true)
public class BusinessExceptionAutoConfig {

    @Autowired(required = false)
    private MessageSourceAccessor accessor;

    @Bean
    @ConditionalOnMissingBean
    public BusinessExceptionFactory businessExceptionFactory(Props props, BusinessExceptionMap env) {
        final Map<String, String> merged = new HashMap<>();
        merged.putAll(props.getMessages());
        merged.putAll(env);
        return merged.isEmpty() ? new EmptyBusinessExceptionFactory() : new MapBusinessExceptionFactory(merged, accessor);
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "carnival.business-exception")
    static final class Props {
        private boolean enabled = true;
        private Map<String, String> messages = new HashMap<>();
    }

}