package com.ezen.www.handler;

import com.ezen.www.domain.PagingVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {

private int startPage;
private int endPage;
private int realEndPage;
private boolean prev, next;

private int totalCount;
private PagingVO pgvo;

public PagingHandler(PagingVO pgvo, int totalCount){

    this.pgvo = pgvo;
    this.totalCount = totalCount;

    this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double) 10) *10;
    this.startPage = this.endPage - 9;

    this.realEndPage = (int)Math.ceil(this.totalCount / (double)pgvo.getQty());

    if(this.endPage > this.realEndPage){
        this.endPage = this.realEndPage;
    }

    this.prev = this.startPage > 1;
    this.next = this.endPage < this.realEndPage;

}

}
