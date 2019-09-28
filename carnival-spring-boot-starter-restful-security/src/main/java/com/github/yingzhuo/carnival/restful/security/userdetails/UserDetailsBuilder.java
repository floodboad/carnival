/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.restful.security.userdetails;

import com.github.yingzhuo.carnival.common.datamodel.Gender;
import com.github.yingzhuo.carnival.restful.security.permission.Permission;
import com.github.yingzhuo.carnival.restful.security.role.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 应卓
 * @since 1.1.13
 */
public final class UserDetailsBuilder {

    private final SimpleUserDetails ud = new SimpleUserDetails();

    public UserDetailsBuilder() {
    }

    public UserDetailsBuilder id(Object id) {
        ud.setId(id);
        return this;
    }

    public UserDetailsBuilder username(String username) {
        ud.setUsername(username);
        return this;
    }

    public UserDetailsBuilder email(String emailAddress) {
        ud.setEmail(emailAddress);
        return this;
    }

    public UserDetailsBuilder password(String password) {
        ud.setPassword(password);
        return this;
    }

    public UserDetailsBuilder gender(Gender gender) {
        ud.setGender(gender);
        return this;
    }

    public UserDetailsBuilder dateOfBirth(Date date) {
        ud.setDateOfBirth(date);
        return this;
    }

    public UserDetailsBuilder dateOfBirth(int year, int month, int day) {
        return dateOfBirth(new GregorianCalendar(year, month - 1, day).getTime());
    }

    public UserDetailsBuilder expired(boolean expired) {
        ud.setExpired(expired);
        return this;
    }

    public UserDetailsBuilder locked(boolean locked) {
        ud.setLocked(locked);
        return this;
    }

    public UserDetailsBuilder admin(boolean admin) {
        ud.setAdmin(admin);
        return this;
    }

    public UserDetailsBuilder roles(Role... roles) {
        ud.setRoles(Arrays.asList(roles));
        return this;
    }

    public UserDetailsBuilder roles(String... roleNames) {
        ud.setRoles(Arrays.stream(roleNames).map(Role::of).collect(Collectors.toList()));
        return this;
    }

    public UserDetailsBuilder permissions(Permission... permissions) {
        ud.setPermissions(Arrays.asList(permissions));
        return this;
    }

    public UserDetailsBuilder permissions(String... permissionNames) {
        ud.setPermissions(Arrays.stream(permissionNames).map(Permission::of).collect(Collectors.toList()));
        return this;
    }

    public UserDetailsBuilder nativeUser(Object nativeUser) {
        ud.setNativeUser(nativeUser);
        return this;
    }

    public UserDetails build() {
        return ud;
    }

}

@Getter
@Setter
class SimpleUserDetails implements UserDetails {

    private Object id = null;
    private String username = null;
    private String email = null;
    private String password = null;
    private Date dateOfBirth = null;
    private Gender gender = null;
    private boolean expired = false;
    private boolean locked = false;
    private Collection<Role> roles = Collections.emptyList();
    private Collection<Permission> permissions = Collections.emptyList();
    private boolean admin = false;
    private Object nativeUser = null;

}