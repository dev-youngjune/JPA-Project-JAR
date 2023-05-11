package com.app.projectjar.provider;

import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.app.projectjar.type.Role;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Collection;

@Component
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetail implements UserDetails {

    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberNickName;
    private BadgeType badgeType;
    private Role memberType;

    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public UserDetail(Long memberId, String memberEmail, String memberPassword, String memberPhoneNumber, String memberName, String memberNickName, BadgeType badgeType, Role memberType, Collection<? extends GrantedAuthority> authorities) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
        this.memberNickName = memberNickName;
        this.badgeType = badgeType;
        this.memberType = memberType;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() { return memberEmail; }

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
        return true;
    }
}
