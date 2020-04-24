/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.password.autoconfig;

import com.github.yingzhuo.carnival.password.Algorithm;
import com.github.yingzhuo.carnival.password.PasswordEncrypter;
import com.github.yingzhuo.carnival.password.impl.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @author 应卓
 */
@Lazy(false)
@EnableConfigurationProperties(PasswordEncrypterAutoConfig.Props.class)
@ConditionalOnProperty(prefix = "carnival.password", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PasswordEncrypterAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncrypter passwordEncrypter(Props props) {
        switch (props.getAlgorithm()) {
            case MD5:
                return new MD5PasswordEncrypter();
            case BCRYPT:
                return new BCryptPasswordEncrypter();
            case SHA1:
                return new SHA1PasswordEncrypter();
            case SHA256:
                return new SHA256PasswordEncrypter();
            case NONE:
                return new NonePasswordEncrypter();
        }
        throw new AssertionError();
    }

    @Getter
    @Setter
    @ConfigurationProperties("carnival.password")
    static class Props {
        private boolean enabled = true;
        private Algorithm algorithm = Algorithm.MD5;
    }

}
