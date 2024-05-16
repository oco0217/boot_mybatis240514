package com.ezen.www.contoller;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member/*")
@Controller
@Slf4j
public class MemberController {

    private final MemberService msv;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String regitser(MemberVO mvo){

        log.info(">>>> mvo >>> {}",mvo);

        mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
        int isOk = msv.insert(mvo);

        return "/index";
    }

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/memberList")
    public void memberList(Model m){

        List<MemberVO> list = msv.getList();

        log.info(">>>>>멤버리스트>>>>{}",list);

        m.addAttribute("list",list);
    }

    @GetMapping("/modify")
    public void moidfy(){}

    @PostMapping("/modify")
    public String modify(MemberVO mvo){

        if(mvo.getPwd()==null || mvo.getPwd()=="" || mvo.getPwd().length()==0){
            mvo.setPwd(null);
        }else{
            mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
        }
        msv.update(mvo);
        return "redirect:/member/logout";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("email")String email){
        int isOk = msv.remove(email);
        return "redirect:/member/logout";
    }

}
