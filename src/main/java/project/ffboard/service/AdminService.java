package project.ffboard.service;

import java.util.List;
import project.ffboard.dto.Member;

public interface AdminService {
    public List<Member> getList(String pg, String email, int limit);
}
