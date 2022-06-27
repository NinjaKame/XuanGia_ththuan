package cmc.du11.xuangia_ththuan.repository;

import cmc.du11.xuangia_ththuan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
