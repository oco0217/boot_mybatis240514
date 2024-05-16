package com.ezen.www.service;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceImple implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public int insert(MemberVO mvo) {

        int isOk = memberMapper.insert(mvo);



        return (isOk > 0 ? memberMapper.insertAuth(mvo.getEmail()) : 0);
    }

    @Override
    public List<MemberVO> getList() {

        List<MemberVO> list  =memberMapper.getList();
        for(MemberVO member : list){

            member.setAuthList(memberMapper.getAuthList(member.getEmail()));

        }
        return list;
    }

    @Override
    public void update(MemberVO mvo) {

         memberMapper.update(mvo);

    }

    @Override
    public int remove(String email) {
        memberMapper.authRemove(email);
        return memberMapper.remove(email);
    }

}
