package com.ezen.www.contoller;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment/*")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService csv;

//    @PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//    ResponseEntity<String>post(@RequestBody CommentVO cvo){
//
//        int isOk = csv.post(cvo);
//
//        return isOk > 0 ?new ResponseEntity<String>("1", HttpStatus.OK) :
//        new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody CommentVO cvo){

        int isOk = csv.post(cvo);

        return isOk > 0 ? "1" : "0";

    }

    //SELECT * FROM comment ORDER BY cno DESC LIMIT 0,5
    @GetMapping("/list/{bno}/{page}")
    @ResponseBody
    public PagingHandler list(@PathVariable("bno")long bno, @PathVariable("page")int page){

        PagingVO pgvo = new PagingVO(page,5);
        PagingHandler ph = csv.getList(bno, pgvo);

        return ph;

    }

    @PutMapping
    @ResponseBody
    public String modify(@RequestBody CommentVO cvo){

        log.info("cvo확인>>>>>{}",cvo);
        int isOk = csv.modify(cvo);

        return isOk > 0 ? "1" : "0";

    }

    @DeleteMapping(value = "/remove/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String>remove(@PathVariable("cno")long cno){
        int isOk= csv.remove(cno);
        return isOk>0 ? new ResponseEntity<String>("1",HttpStatus.OK) :
                new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
