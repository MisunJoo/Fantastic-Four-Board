package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.ffboard.service.AdminService;

import java.util.List;

@Controller
public class AdminController {
    private AdminService adminService;
    private static final int LIMIT = 10; // 리스트 갯수

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/list")
    public String getList(ModelMap modelMap,
                          @RequestParam(value = "pg", defaultValue = "1")String pg,
                          @RequestParam(value = "email", defaultValue = "") String email){
        // 세션으로 관리자가 접근한건지 한번 더 체크 해주기

        // pg가 숫자외의 값이 입력되었을 때
        if(!pg.equals("(^[0-9]*$)"))
            pg="1";
        List<String> permissions = adminService.getPermissions();
        modelMap.addAttribute("permissions",permissions);
        modelMap.addAttribute("permSize",permissions.size());
        modelMap.addAttribute("members",adminService.getMembers(pg,email, LIMIT));
        return "admin/list";
    }

}
