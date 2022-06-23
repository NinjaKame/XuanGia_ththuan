package cmc.du11.xuangia_ththuan.service.impl;

import cmc.du11.xuangia_ththuan.entity.Permission;
import cmc.du11.xuangia_ththuan.entity.User;
import cmc.du11.xuangia_ththuan.repository.PermissionRepo;
import cmc.du11.xuangia_ththuan.repository.UserRepo;
import cmc.du11.xuangia_ththuan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, CommandLineRunner {
//    @Autowired
    private final UserRepo userRepo;
//    @Autowired
    private final PermissionRepo permissionRepo;

    @Override
    public List<User> getUserByPermission(Permission permission) {
        return userRepo.findAll()
                .stream()
                .filter(u -> u.getPermissions().contains(permission))
                .collect(Collectors.toList());
    }

    @Override
    public boolean insertUser(User user) {
        if(userRepo.findById(user.getId()).isPresent()){
            return false;
        }
        userRepo.save(user);
        return true;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Permission> permissionList = Arrays.asList(
                new Permission("admin"),
                new Permission("client"),
                new Permission("hacker"),
                new Permission("anonymous")
        );
        permissionRepo.saveAll(permissionList);

        List<User> userList = Arrays.asList(
                new User("John Cena"),
                new User("Phong Tom"),
                new User("Lionel Messi"),
                new User("Kento Momota")
        );
        userList.get(0).setPermissions(permissionRepo.findAll().subList(1,3));
        userList.get(1).setPermissions(permissionRepo.findAll().subList(1,1));
        userList.get(2).setPermissions(permissionRepo.findAll().subList(0,2));
        userList.get(3).setPermissions(permissionRepo.findAll().subList(1,2));

        userRepo.saveAll(userList);
    }
}
