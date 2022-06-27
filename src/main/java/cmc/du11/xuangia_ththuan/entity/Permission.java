package cmc.du11.xuangia_ththuan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String role;

    @ManyToMany(mappedBy = "permissionList")
    @JsonBackReference
    private List<User> userList = new ArrayList<>();

    public Permission(String role) {
        this.role = role;
    }

}