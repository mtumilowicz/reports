package dao;

import com.google.common.base.Preconditions;
import entity.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static database.DatabaseMock.getAllBooks;

/**
 * Created by mtumilowicz on 2017-07-05.
 */
public class BookDAO {
    public static List<Book> getAllEntities() {
        return getAllBooks();
    }
    
    public static Optional<BigDecimal> sumPrice(List<Book> books) {
        Preconditions.checkArgument(books != null);
        
        return books.stream().map(Book::getPrice).filter(Objects::nonNull).reduce(BigDecimal::add);
    }

    public static Optional<BigDecimal> sumPriceOfAllEntities() {
        List<Book> allEntities = getAllEntities();
        Objects.requireNonNull(allEntities);

        return sumPrice(allEntities);
    }
}
