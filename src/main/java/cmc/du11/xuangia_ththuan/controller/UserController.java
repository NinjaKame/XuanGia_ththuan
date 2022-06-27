package cmc.du11.xuangia_ththuan.controller;

import cmc.du11.xuangia_ththuan.entity.Permission;
import cmc.du11.xuangia_ththuan.entity.User;
import cmc.du11.xuangia_ththuan.repository.UserRepo;
import cmc.du11.xuangia_ththuan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/api/")
public class UserController {

    private final UserService userService;

    private final UserRepo userRepo;

    @GetMapping("get")
    public ResponseEntity<?> getUserListByPermission(@RequestHeader String permissionId){
      try {
          return new ResponseEntity<>(userService.getUserByPermission(permissionId),HttpStatus.OK);
      } catch (Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userRepo.findAll(),HttpStatus.FOUND);
    }

    @PostMapping("insert")
    public ResponseEntity<?> insertUser(@RequestBody User newUser){
        try {
            if (userService.insertUser(newUser)) {
                return new ResponseEntity<>(userRepo.findAll(), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
