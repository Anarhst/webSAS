package ru.mirea.intro.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Book", schema = "public")
public class BookDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String author;
    private String name;
    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private RequestDAO requestDao;
}
