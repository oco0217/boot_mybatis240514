package com.ezen.www.service;

import com.ezen.www.domain.CommentVO;

import java.util.List;

public interface CommentService {
    int post(CommentVO cvo);

    List<CommentVO> getList(int bno);

    int remove(int cno);
}
