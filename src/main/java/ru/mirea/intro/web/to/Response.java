package ru.mirea.intro.web.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
@Getter
@Setter
public class Response<T> {
    private Meta meta;
    public T dataBlock;
}
