package com.yss.shopping.user.auth;

import com.yss.shopping.user.vo.user.PrivilegeUserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * <p>
 * SpringSecurity需要的用户详情
 * </p>
 *
 * @author yss
 * @since 2020-05-15 17:41
 */
public class PrivilegeUserDetails implements UserDetails {

    private PrivilegeUserVO privilegeUserVO;

    public PrivilegeUserDetails(PrivilegeUserVO privilegeUserVO) {
        this.privilegeUserVO = privilegeUserVO;
    }


    /**
     * 返回当前用户的权限列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.privilegeUserVO.getPrivilegeMenuVOList().stream()
                .filter(eachPrivilegeMenuVO -> eachPrivilegeMenuVO.getMenuCode() != null)
                .map(eachPrivilegeMenuVO -> new SimpleGrantedAuthority(eachPrivilegeMenuVO.getMenuCode()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.privilegeUserVO.getPassword();
    }

    @Override
    public String getUsername() {
        return this.privilegeUserVO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO 在此处判断用户状态是否可用
        return true;
    }
}
