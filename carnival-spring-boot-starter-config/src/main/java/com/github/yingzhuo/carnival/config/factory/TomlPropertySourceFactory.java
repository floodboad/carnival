/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.config.factory;

import com.github.yingzhuo.carnival.config.loader.TomlPropertySourceLoader;
import com.github.yingzhuo.carnival.config.support.AbstractPropertySourceFactory;

/**
 * @author 应卓
 * @since 1.4.1
 */
public class TomlPropertySourceFactory extends AbstractPropertySourceFactory {

    public TomlPropertySourceFactory() {
        super(new TomlPropertySourceLoader());
    }
}
