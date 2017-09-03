package dao;

import entity.Book;
import org.apache.commons.collections4.ListUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static database.DatabaseMock.getAllBooks;

/**
 * Created by mtumilowicz on 2017-07-05.
 */
public class BookDAOMock {
    public static List<Book> getAllEntities() {
        return getAllBooks();
    }
    
    public static Optional<BigDecimal> sumPrice(List<Book> books) {
        return ListUtils.emptyIfNull(books).stream().map(Book::getPrice).filter(Objects::nonNull).reduce(BigDecimal::add);
    }

    public static Optional<BigDecimal> sumPriceOfAllEntities() {
        List<Book> allEntities = Objects.requireNonNull(getAllEntities());

        return sumPrice(allEntities);
    }
}
