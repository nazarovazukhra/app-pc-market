package uz.pdp.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Integer mark;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Product product;
}
