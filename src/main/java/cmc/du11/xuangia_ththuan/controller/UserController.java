package cmc.du11.xuangia_ththuan.controller;

import cmc.du11.xuangia_ththuan.entity.User;
import cmc.du11.xuangia_ththuan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("getByPermission")
    public ResponseEntity<?> getUserListByPermission(@RequestHeader String permissionId){
      try {
          return new ResponseEntity<>(userService.getUserByPermission(permissionId),HttpStatus.OK);
      } catch (Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "id") String sort){
        return userService.getAllUsers(page,size,sort) == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(userService.getAllUsers(page,size,sort));
    }

    @PostMapping("insert")
    public ResponseEntity<?> insertUser(@RequestBody User newUser){
        try {
            if (userService.insertUser(newUser)) {
                return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
