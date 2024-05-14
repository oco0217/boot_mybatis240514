package com.ezen.www.contoller;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ezen.www.service.BoardService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
@Slf4j

public class BoardController {

    private final BoardService bsv;
    private final FileHandler fh;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String register(BoardVO bvo, @RequestParam(name = "files", required=false) MultipartFile[] files){

        log.info(">>>>>파일 확인>>>>>{}",files);

        List<FileVO> flist = null;
        if(files[0].getSize() > 0 || files != null){
            flist = fh.uploadFiles(files);
        }

        BoardDTO bdto = new BoardDTO(bvo,flist);

        int isOk = bsv.register(bdto);

        return "/index";
    }

    @GetMapping("/list")
    public void list(Model m,PagingVO pgvo){

        log.info(">>>>> pgvo >>>>{}",pgvo);
//        log.info(">>>list print>>>>{}",list);
        int totalCount = bsv.getTotalCount(pgvo);

        List<BoardVO> list = bsv.getList(pgvo);

        PagingHandler ph = new PagingHandler(pgvo,totalCount);

        log.info(">>>>>ph>>>>>{}",ph);

        m.addAttribute("list", list);
        m.addAttribute("ph",ph);
    }

    @GetMapping("/detail")
    public void detail(@RequestParam("bno")long bno, Model m){


        BoardDTO bdto = bsv.getDetail(bno);

        log.info(">>>>>bdto 확인>>>>>{}",bdto);

        m.addAttribute("bdto", bdto);
    }

    @PostMapping("/modify")
    public String modify(BoardVO bvo){
        bsv.update(bvo);

        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno")long bno){

        bsv.remove(bno);

        return "redirect:/board/list";
    }
}
