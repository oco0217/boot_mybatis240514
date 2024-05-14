package com.ezen.www.repository;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int register(BoardVO bvo);

    List<BoardVO> getList(PagingVO pgvo);

    BoardVO getDetail(long bno);

    void update(BoardVO bvo);

    void remove(long bno);

    int getTotalCount(PagingVO pgvo);

    long getBno();
}
