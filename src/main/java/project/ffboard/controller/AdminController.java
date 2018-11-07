package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import project.ffboard.service.AdminService;
import project.ffboard.dto.Member;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class AdminController {
    private AdminService adminService;
    private static final int LIMIT = 5; // 리스트 갯수

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/list")
    public String getList(ModelMap modelMap,
                          @RequestParam(value = "pg", defaultValue = "1") String pg,
                          @RequestParam(value = "email", defaultValue = "") String email,
                          HttpSession session) {
        // 세션으로 관리자가 접근한건지 한번 더 체크 해주기
        Member member = (Member)session.getAttribute("member");
        if(member.getId().intValue() != 1)
            return "redirect:/";


        // pg가 숫자외의 값이 입력되었을 때
        if (!Pattern.matches("^[0-9]*$", pg))
            pg = "1";
        List<String> permissions = adminService.getPermissions();
        modelMap.addAttribute("permissions", permissions);
        modelMap.addAttribute("permSize", permissions.size());
        modelMap.addAttribute("email",email);
        modelMap.addAttribute("pg",pg);
        modelMap.addAttribute("memberCount",adminService.memberCount()/LIMIT+1);
        modelMap.addAttribute("members", adminService.getMembers(pg, email, LIMIT));
        return "admin/list";
    }

    @PostMapping("/admin/update")
    public String update(@RequestParam(value = "email", defaultValue = "") String email,
                         @RequestParam(value = "pg", defaultValue = "1") String pg,
                         @RequestParam(value = "checkPerm",defaultValue = "") String[] perms,
                         @RequestParam(value = "id") String id) {

        adminService.update(id,perms);
        return "redirect:/admin/list?email=" + email+"&pg="+pg;
    }

    @PostMapping("/admin/addCategory")
    public String addCategory(@RequestParam(value="name") String name){
        adminService.addCategory(name);
        return "redirect:/";
    }
}
