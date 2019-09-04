/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.restful.security;

import com.github.yingzhuo.carnival.restful.security.annotation.Requires;
import com.github.yingzhuo.carnival.restful.security.auth.RequiresAnyRoleAuthComponent;

import java.lang.annotation.*;

/**
 * @author 应卓
 * @since 1.1.7
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Requires(RequiresAnyRoleAuthComponent.class)
public @interface RequiresAnyRole {

    public String[] value();

    public String errorMessage() default ":::<NO MESSAGE>:::";

}
