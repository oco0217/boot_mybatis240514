package com.ezen.www.repository;

import com.ezen.www.domain.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int post(CommentVO cvo);

    List<CommentVO> getList(int bno);

    int remove(int cno);
}
