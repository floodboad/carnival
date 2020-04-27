/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.feign.autoconfig;

import com.github.yingzhuo.carnival.feign.contract.XSpringMvcContract;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @author 应卓
 * @since 1.6.0
 */
@Lazy(false)
@ConditionalOnProperty(prefix = "carnival.feign", name = "enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureAfter(FeignBeanAutoConfig.class)
public class FeignCoreAutoConfig {

    @Bean
    public XSpringMvcContract xSpringMvcContract() {
        return new XSpringMvcContract();
    }

}
