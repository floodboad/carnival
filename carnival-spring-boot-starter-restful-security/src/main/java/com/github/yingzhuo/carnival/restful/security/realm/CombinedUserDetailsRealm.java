/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.restful.security.realm;

import com.github.yingzhuo.carnival.restful.security.token.Token;
import com.github.yingzhuo.carnival.restful.security.userdetails.UserDetails;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.4.7
 */
@Slf4j
public class CombinedUserDetailsRealm implements UserDetailsRealm, InitializingBean {

    private int order = 0;
    private final List<UserDetailsRealm> realms;

    public CombinedUserDetailsRealm(UserDetailsRealm... realms) {
        this.realms = Arrays.asList(realms);
    }

    @Override
    public Optional<UserDetails> loadUserDetails(Token token) {
        for (val realm : realms) {
            val op = realm.loadUserDetails(token);
            if (op.isPresent()) {
                return op;
            }
        }
        return Optional.empty();
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notEmpty(realms, "No UserDetailsRealm found.");
    }

}
