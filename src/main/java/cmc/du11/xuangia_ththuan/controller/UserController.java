package cmc.du11.xuangia_ththuan.controller;

import cmc.du11.xuangia_ththuan.entity.Permission;
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

    @GetMapping("get")
    public ResponseEntity<?> getUserListByPermission(@RequestHeader Permission permission){
      try {
          return new ResponseEntity<>(userService.getUserByPermission(permission),HttpStatus.OK);
      } catch (Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }
}
