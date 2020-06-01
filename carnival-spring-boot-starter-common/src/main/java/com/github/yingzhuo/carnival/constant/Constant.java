/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.constant;

import com.github.yingzhuo.carnival.spring.SpringUtils;

/**
 * @author 应卓
 * @since 1.6.12
 */
public interface Constant {

    public static final String DEFAULT_GROUP = "default";

    public static <T> T get(String group, String name) {
        return get(group, name, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String group, String name, T defaultIfNull) {
        try {
            T obj = (T) SpringUtils.getBean(ConstantFactory.MapConstant.class).getMap().get(group).get(name);
            return obj != null ? obj : defaultIfNull;
        } catch (NullPointerException e) {
            return defaultIfNull;
        }
    }

}