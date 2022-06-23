package cmc.du11.xuangia_ththuan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permissions_id"))
    private List<Permission> permissions = new ArrayList<>();

    public User(String userName) {
        this.name = userName;
    }
}
