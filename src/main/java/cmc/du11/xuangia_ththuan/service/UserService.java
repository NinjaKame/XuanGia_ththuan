package cmc.du11.xuangia_ththuan.service;

import cmc.du11.xuangia_ththuan.entity.Permission;
import cmc.du11.xuangia_ththuan.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserByPermission(String permission);

    List<User> getAllUsers(int startPage, int pageSize, String sort);

    boolean insertUser(User user);
}
