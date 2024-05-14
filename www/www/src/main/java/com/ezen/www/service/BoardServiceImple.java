package com.ezen.www.service;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardMapper;
import com.ezen.www.repository.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImple implements BoardService{

    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    @Transactional
    @Override
    public int register(BoardDTO bdto) {
        int isOk = boardMapper.register(bdto.getBvo());

        long bno = boardMapper.getBno();

        if(isOk > 0 && bdto.getFlist().size() > 0){
            for(FileVO fvo : bdto.getFlist()){
                fvo.setBno(bno);
                isOk *= fileMapper.insertFile(fvo);
            }
        }
        return isOk;
    }

    @Override
    public List<BoardVO> getList(PagingVO pgvo) {
        return boardMapper.getList(pgvo);
    }

    @Override
    public BoardVO getDetail(long bno) {
        return boardMapper.getDetail(bno);
    }

    @Override
    public void update(BoardVO bvo) {
        boardMapper.update(bvo);
    }

    @Override
    public void remove(long bno) {
        boardMapper.remove(bno);
    }

    @Override
    public int getTotalCount(PagingVO pgvo) {
        return boardMapper.getTotalCount(pgvo);
    }
}
