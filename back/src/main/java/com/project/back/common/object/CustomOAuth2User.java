package com.project.back.common.object;

import java.util.Map;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomOAuth2User implements OAuth2User 
{
    private String id;
    private boolean status;
    private String joinPath;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomOAuth2User(String id, Map<String, Object> attributes, boolean status, String joinPath) 
    {
        this.id = id;
        this.status = status;
        this.joinPath = joinPath;
        this.attributes = attributes;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Map<String, Object> getAttributes() 
    {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        return this.authorities;
    }

    @Override
    public String getName() 
    {
        return this.id;
    }

    public boolean getStatus() 
    {
        return this.status;
    }

    public String getJoinPath() 
    {
        return this.joinPath;
    }
}
/* 분석 완료 */
