package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;

import java.util.List;

public interface MemberService {
    int insert(MemberVO mvo);

    List<MemberVO> getList();

    void update(MemberVO mvo);

    int remove(String email);
}
