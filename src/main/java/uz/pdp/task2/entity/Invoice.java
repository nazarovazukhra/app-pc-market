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
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Boolean status;

    @OneToOne
    private Order order;

    @ManyToOne
    private User customer;
}
