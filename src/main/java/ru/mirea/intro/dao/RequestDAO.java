package ru.mirea.intro.dao;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Requests", schema = "public")
public class RequestDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "request_id")
    @Column(name = "id")
    private Long id;
    @Column(name = "request_value")
    private String requestValue;
    @OneToMany(mappedBy = "requestDao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookDao> bookDaoList;

    public void sort_List() {
        Collections.sort(bookDaoList);
    }

    @Override
    public String toString() {
        return "RequestDAO{" +
                "id=" + id +
                ", requestValue='" + requestValue + '\'' +
                ", bookDaoList=" + bookDaoList +
                '}';
    }
}

