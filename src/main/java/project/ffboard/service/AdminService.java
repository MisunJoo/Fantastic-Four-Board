package project.ffboard.service;

import java.util.List;
import project.ffboard.dto.Member;

public interface AdminService {
    public List<Member> getMembers(String pg, String email, int limit);
    public List<String> getPermissions();
    void update(String id, String[] checkPerm);
}
