package com.ezen.www.service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImple implements CommentService{

    private final CommentMapper commentMapper;

    @Override
    public int post(CommentVO cvo) {
        return commentMapper.post(cvo);
    }

    @Override
    public List<CommentVO> getList(int bno) {
        return commentMapper.getList(bno);
    }

    @Override
    public int remove(int cno) {
        return commentMapper.remove(cno);
    }
}
