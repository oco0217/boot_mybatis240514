package com.ezen.www.service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;

import java.util.List;

public interface CommentService {
    int post(CommentVO cvo);

    PagingHandler getList(long bno, PagingVO pgvo);

    int remove(long cno);

    int modify(CommentVO cvo);
}
