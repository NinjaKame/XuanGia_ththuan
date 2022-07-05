package cmc.du11.xuangia_ththuan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item(String itemName) {
        this.itemName = itemName;
    }
}
