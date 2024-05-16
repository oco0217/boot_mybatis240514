package com.ezen.www.contoller;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment/*")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService csv;

    @PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String>post(@RequestBody CommentVO cvo){

        int isOk = csv.post(cvo);

        return isOk > 0 ?new ResponseEntity<String>("1", HttpStatus.OK) :
        new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value = "/getList/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommentVO>> getList(@PathVariable("bno")int bno){
        List<CommentVO> list = csv.getList(bno);
        return new ResponseEntity<List<CommentVO>>(list,HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String>remove(@PathVariable("cno")int cno){
        int isOk= csv.remove(cno);
        return isOk>0 ? new ResponseEntity<String>("1",HttpStatus.OK) :
                new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
