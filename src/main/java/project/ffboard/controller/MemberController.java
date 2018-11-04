package project.ffboard.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.ffboard.dto.Member;
import project.ffboard.service.MemberService;

@Controller
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/member/signup")
    public String signUpForm(ModelMap modelMap,@RequestParam(value = "email", defaultValue = "") String email,
                             @RequestParam(value = "nickName", defaultValue = "")String nickName,
                             @RequestParam(value = "duplication", defaultValue = "") String duplication) {
        modelMap.addAttribute("email",email);
        modelMap.addAttribute("nickName",nickName);
        modelMap.addAttribute("duplication",duplication);

        return "signup";
    }

    @PostMapping("/member/signup")
    public String signUp(@ModelAttribute Member member) {
        Long result = memberService.signUp(member);
        // -1L은 이메일중복
        // -2L은 닉네임중복
        if(result==-1L){
            return "redirect:/member/signup?duplication=email&nickName="+member.getNickName()+"&email="+member.getEmail();
        }else if(result==-2L){
            return "redirect:/member/signup?duplication=nickName&nickName="+member.getNickName()+"&email="+member.getEmail();
        }else { // 로그인 성공
            return "redirect:/main";
        }
    }

    @GetMapping("/login")
    public String loginForm(ModelMap modelMap, @ModelAttribute Member member,
                            @RequestParam(value="loginCheck", defaultValue="") String loginCheck){
        modelMap.addAttribute("email", member.getEmail());
        modelMap.addAttribute("password", member.getPassword());
        modelMap.addAttribute("loginCheck", loginCheck);

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member){
        Member memberResult = memberService.login(member);
        if(memberResult == null){
            return "redirect:/login?loginCheck=problem";
        }
        else{
            return "redirect:/main";
        }
    }



}
