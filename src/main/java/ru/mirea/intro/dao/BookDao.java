package ru.mirea.intro.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.mirea.intro.service.model.Book;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Book", schema = "public")
public class BookDao implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String author;
    private String name;
    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private RequestDAO requestDao;

    @Override
    public int compareTo(@NotNull Object o) {
        BookDao tmp = (BookDao) o;
        if(this.id < tmp.id) {
            return -1;
        }
        else if (this.id > tmp.id) {
            return 1;
        }
        else
            return 0;
    }
}
