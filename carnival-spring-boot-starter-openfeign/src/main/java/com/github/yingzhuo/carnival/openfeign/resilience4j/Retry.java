/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.openfeign.resilience4j;

import java.lang.annotation.*;

/**
 * @author 应卓
 * @since 1.6.19
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Retry {

    public int maxAttempts() default 3;

    public Class<? extends Exception>[] exceptionTypes() default {};

    public Class<? extends Exception>[] ignoreExceptionTypes() default {};

}