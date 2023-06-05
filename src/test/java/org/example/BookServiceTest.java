package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {
    private BookService bookService;
    private User user;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setup() {
        bookService = new BookService();
        user = new User("Alice", "password123", "alice@example.com");

        book1 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 9.99);
        book2 = new Book("To Kill a Mockingbird", "Harper Lee", "Classic", 14.99);
        book3 = new Book("1984", "George Orwell", "Science Fiction", 12.99);

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
    }

    @AfterEach
    public void teardown() {
        bookService = null;
        user = null;
    }

    @Test
    public void testSearchByTitle() {
        String keyword = "Catcher";
        List<Book> result = bookService.searchBook(keyword);
        assertEquals(Arrays.asList(book1), result);
    }

    @Test
    public void testSearchByAuthor() {
        String keyword = "Harper Lee";
        List<Book> result = bookService.searchBook(keyword);
        assertEquals(Arrays.asList(book2), result);
    }

    @Test
    public void testSearchByGenre() {
        String keyword = "Fiction";
        List<Book> result = bookService.searchBook(keyword);
        assertEquals(Arrays.asList(book1), result);
    }

    @Test
    public void testSearchNotFound() {
        String keyword = "Unknown";
        List<Book> result = bookService.searchBook(keyword);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testPurchaseBook() {
        boolean success = bookService.purchaseBook(user, book1);
        assertTrue(success);
    }

    @Test
    public void testPurchaseBookNotAvailable() {
        Book unavailableBook = new Book("Unavailable Book", "Unknown Author", "Unknown Genre", 9.99);
        boolean success = bookService.purchaseBook(user, unavailableBook);
        assertFalse(success);
    }
}


