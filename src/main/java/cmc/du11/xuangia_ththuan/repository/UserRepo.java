package cmc.du11.xuangia_ththuan.repository;

import cmc.du11.xuangia_ththuan.entity.Permission;
import cmc.du11.xuangia_ththuan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
