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

import com.github.yingzhuo.carnival.restful.security.annotation.AuthenticationComponent;
import com.github.yingzhuo.carnival.restful.security.annotation.Requires;
import com.github.yingzhuo.carnival.restful.security.core.CheckUtils;
import com.github.yingzhuo.carnival.restful.security.exception.AuthorizationException;
import com.github.yingzhuo.carnival.restful.security.exception.RestfulSecurityException;
import com.github.yingzhuo.carnival.restful.security.userdetails.UserDetails;
import lombok.val;

import java.lang.annotation.*;
import java.util.Objects;

/**
 * @author 应卓
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Requires(RequiresId.AuthComponent.class)
public @interface RequiresId {

    public String value();

    public String errorMessage() default ":::<NO MESSAGE>:::";

    public static class AuthComponent implements AuthenticationComponent<RequiresId> {

        @Override
        public void authenticate(UserDetails userDetails, RequiresId annotation) throws RestfulSecurityException {
            val expect = annotation.value();

            if (userDetails == null || userDetails.getId() == null || !Objects.equals(expect, userDetails.getId())) {
                throw new AuthorizationException(CheckUtils.getMessage(annotation.errorMessage()));
            }
        }
    }

}