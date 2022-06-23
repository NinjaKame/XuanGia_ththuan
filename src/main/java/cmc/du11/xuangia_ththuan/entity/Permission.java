package cmc.du11.xuangia_ththuan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String role;

    @ManyToMany(mappedBy = "permissions")
    @JsonBackReference
    private List<User> userList;

    public Permission(String role) {
        this.role = role;
    }

}