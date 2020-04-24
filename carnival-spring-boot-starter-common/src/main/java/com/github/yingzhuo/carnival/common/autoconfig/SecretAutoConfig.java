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

import com.github.yingzhuo.carnival.common.Configurer;
import com.github.yingzhuo.carnival.common.io.ResourceText;
import com.github.yingzhuo.carnival.secret.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.FormatterRegistry;

/**
 * @author 应卓
 */
@Slf4j
@EnableConfigurationProperties({
        SecretAutoConfig.SecretProps.class,
        SecretAutoConfig.AESProps.class,
        SecretAutoConfig.RSAProps.class
})
@ConditionalOnProperty(prefix = "carnival.secret", name = "enabled", havingValue = "true", matchIfMissing = true)
@Lazy(false)
public class SecretAutoConfig implements Configurer<FormatterRegistry> {

    @Override
    @Autowired
    public void config(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new Base64.Encoding.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new Base64.Decoding.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new RSA.EncryptByPrivateKey.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new RSA.EncryptByPublicKey.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new RSA.DecryptByPrivateKey.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new RSA.DecryptByPublicKey.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new AES.Encrypting.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new AES.Decrypting.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new MD5.Encrypting.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new MD2.Encrypting.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new SHA1.Encrypting.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new SHA256.Encrypting.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new SHA384.Encrypting.FormatterFactory());
        registry.addFormatterForFieldAnnotation(new SHA512.Encrypting.FormatterFactory());
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "carnival.secret")
    static class SecretProps {
        private boolean enabled = true;
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "carnival.secret.rsa")
    public static class RSAProps implements InitializingBean {

        private String publicKey;
        private String privateKey;
        private ResourceText publicKeyLocation;
        private ResourceText privateKeyLocation;

        @Override
        public void afterPropertiesSet() {
            if (publicKeyLocation != null) {
                this.publicKey = publicKeyLocation.getTextAsOneLine().trim();
            }

            if (privateKeyLocation != null) {
                this.privateKey = privateKeyLocation.getTextAsOneLine().trim();
            }
        }
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "carnival.secret.aes")
    public static class AESProps implements InitializingBean {

        private String passphrase;
        private ResourceText passphraseLocation;

        @Override
        public void afterPropertiesSet() {
            if (passphraseLocation != null) {
                this.passphrase = passphraseLocation.getTextAsOneLine().trim();
            }
        }
    }

}
