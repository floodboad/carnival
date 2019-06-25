/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.rsa;

import com.github.yingzhuo.carnival.rsa.autoconfig.RSAAutoconfig;
import com.github.yingzhuo.carnival.rsa.util.RSAUtils;
import com.github.yingzhuo.carnival.spring.SpringUtils;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.lang.annotation.*;
import java.util.Collections;
import java.util.Set;

/**
 * @author 应卓
 */
public interface RSA {

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface DecryptByPrivateKey {

        public static final class FormatterFactory implements AnnotationFormatterFactory<DecryptByPrivateKey> {

            @Override
            public Set<Class<?>> getFieldTypes() {
                return Collections.singleton(String.class);
            }

            @Override
            public Printer<?> getPrinter(DecryptByPrivateKey decryptByPrivateKey, Class<?> aClass) {
                return (Printer<String>) (o, locale) -> o;
            }

            @Override
            public Parser<?> getParser(DecryptByPrivateKey decryptByPrivateKey, Class<?> aClass) {
                return (Parser<String>) (s, locale) -> RSAUtils.decryptByPrivateKey(s, SpringUtils.getBean(RSAAutoconfig.Props.class).getPrivateKey());
            }
        }
    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface DecryptByPublicKey {

        public static final class FormatterFactory implements AnnotationFormatterFactory<DecryptByPublicKey> {

            @Override
            public Set<Class<?>> getFieldTypes() {
                return Collections.singleton(String.class);
            }

            @Override
            public Printer<?> getPrinter(DecryptByPublicKey decryptByPublicKey, Class<?> aClass) {
                return (Printer<String>) (o, locale) -> o;
            }

            @Override
            public Parser<?> getParser(DecryptByPublicKey decryptByPublicKey, Class<?> aClass) {
                return (Parser<String>) (s, locale) -> RSAUtils.decryptByPublicKey(s, SpringUtils.getBean(RSAAutoconfig.Props.class).getPublicKey());
            }
        }
    }

}
