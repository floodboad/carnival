/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.spring;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * @author 应卓
 * @since 1.6.2
 */
public class ResourceLoaderUtils {

    private static final ResourceLoader DEFAULT = new DefaultResourceLoader();

    public static ResourceLoader getDefault() {
        return DEFAULT;
    }

    private ResourceLoaderUtils() {
    }

}
