package com.ezen.www.security;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO loginMvo = memberMapper.selectEmail(username);
        loginMvo.setAuthList(memberMapper.selectAuths(username));

        log.info(">>>>loginMvo>>> {}",loginMvo);

        AuthMember auth = new AuthMember(loginMvo);
        if(auth != null){
        memberMapper.lastLogin(username);
        }

        //MemberVO를  UserDetails의 객체로 바꾸어서 return 해야 하기 떄문에 AuthMember Class 생성
        return auth;
    }
}
