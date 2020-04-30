/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.mvc.support;

import com.github.yingzhuo.carnival.common.mvc.interceptor.HandlerInterceptorSupport;
import com.github.yingzhuo.carnival.mvc.NoDebug;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * 日志输出拦截器
 *
 * @author 应卓
 */
@Slf4j
public class DebugMvcInterceptor extends HandlerInterceptorSupport {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (!log.isDebugEnabled()) {
            return true;
        }

        // since 1.6.2
        if (hasMethodAnnotation(NoDebug.class, handler) || hasClassAnnotation(NoDebug.class, handler)) {
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        try {
            doLog(request, (HandlerMethod) handler);
        } catch (Throwable e) {
            // NOP
        }

        return true;
    }

    private void doLog(HttpServletRequest request, HandlerMethod handlerMethod) {
        log.debug(StringUtils.repeat('-', 120));

        log.debug("[Path]: ");
        log.debug("\t\t\t{}", decode(request.getRequestURI()));

        log.debug("[Method]: ");
        log.debug("\t\t\t{}", request.getMethod());

        log.debug("[Headers]: ");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            log.debug("\t\t\t{} = {}", name, name.equalsIgnoreCase("cookie") ? StringUtils.abbreviate(value, 60) : value);
        }

        log.debug("[Params]: ");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String value = request.getParameter(name);
            log.debug("\t\t\t{} = {}", name, value);
        }

        if (handlerMethod != null) {
            final Method method = handlerMethod.getMethod();
            final Class<?> type = handlerMethod.getBeanType();
            boolean methodDeprecated = method.getAnnotation(Deprecated.class) != null;
            boolean typeDeprecated = type.getAnnotation(Deprecated.class) != null;

            log.debug("[Controller]: ");
            log.debug("\t\t\ttype = {}{}", type.getName(), methodDeprecated ? "(Deprecated)" : "");
            log.debug("\t\t\tmethod-name = {}{}", method.getName(), typeDeprecated ? "(Deprecated)" : "");
        }

        log.debug(StringUtils.repeat('-', 120));
    }

    private String decode(String path) {
        try {
            return URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }

}
