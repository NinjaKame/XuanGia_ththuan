package cmc.du11.xuangia_ththuan.service.impl;

import cmc.du11.xuangia_ththuan.entity.Item;
import cmc.du11.xuangia_ththuan.entity.Permission;
import cmc.du11.xuangia_ththuan.entity.User;
import cmc.du11.xuangia_ththuan.repository.ItemRepo;
import cmc.du11.xuangia_ththuan.repository.PermissionRepo;
import cmc.du11.xuangia_ththuan.repository.UserRepo;
import cmc.du11.xuangia_ththuan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, CommandLineRunner {

    private final UserRepo userRepo;

    private final PermissionRepo permissionRepo;

    private final ItemRepo itemRepo;

    @Override
    public List<User> getUserByPermission(String permissionId) {
        Permission result = permissionRepo.findById(Long.valueOf(permissionId)).orElseThrow(()-> new NullPointerException("PermissionId NOT found"));
        return userRepo.findAll()
                .stream()
                .filter(u -> u.getPermissionList().contains(result))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsers(int startPage, int pageSize, String sort) {
        Pageable pageRequest = PageRequest.of(startPage, pageSize, Sort.by(sort));
        Page<User> pageResult = userRepo.findAll(pageRequest);
        if (pageResult.hasContent()){
            return pageResult.getContent();
        } else {
            return null;
        }
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
                new User("Captain America"),
                new User("Lionel Messi"),
                new User("Kento Momota"),
                new User("Cong Phuong"),
                new User("Quang Hai"),
                new User("Van Toan"),
                new User("Xuan Truong"),
                new User("Hoang Duc")
        );
/*        userList.get(0).setPermissionList(permissionRepo.findAll().subList(1,3));
        userList.get(1).setPermissionList(permissionRepo.findAll().subList(1,1));
        userList.get(2).setPermissionList(permissionRepo.findAll().subList(0,2));
        userList.get(3).setPermissionList(permissionRepo.findAll().subList(1,2));*/
        List<Item> itemList = Arrays.asList(
                new Item("Gun"),
                new Item("Bow"),
                new Item("Rock"),
                new Item("Spoon"),
                new Item("Bat"),
                new Item("Spear")
                );
        itemRepo.saveAll(itemList);

        userList.get(0).setPermissionList(permissionList.subList(1,3)).setItems(itemList.stream().filter(s -> s.getItemName().contains("o")).collect(Collectors.toSet()));
        userList.get(1).setPermissionList(permissionList.subList(2,3)).setItems(itemList.stream().filter(s -> s.getItemName().startsWith("B")).collect(Collectors.toSet()));
        userList.get(2).setPermissionList(permissionList.subList(0,2)).setItems(itemList.stream().filter(s -> s.getItemName().length()>3).collect(Collectors.toSet()));
        userList.get(3).setPermissionList(permissionList.subList(2,4)).setItems(itemList.stream().filter(s -> s.getItemName().startsWith("S")).collect(Collectors.toSet()));
        userList.get(4).setPermissionList(permissionList.subList(0,1)).setItems(itemList.stream().filter(s -> s.getItemName().contains("a")).collect(Collectors.toSet()));
        userList.get(5).setPermissionList(permissionList.subList(3,4)).setItems(new HashSet<>(itemList.subList(0,2)));
        userList.get(6).setPermissionList(permissionList.subList(0,4)).setItems(new HashSet<>(itemList.subList(1,4)));
        userList.get(7).setPermissionList(permissionList.subList(1,4)).setItems(new HashSet<>(itemList.subList(2,5)));

        userRepo.saveAll(userList);
    }
}
