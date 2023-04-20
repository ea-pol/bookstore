package org.eapol.bookstore;

import jakarta.inject.Inject;
import org.eapol.bookstore.author.Author;
import org.eapol.bookstore.author.AuthorService;
import org.eapol.bookstore.book.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(BookstoreApplication.class, args);
  }

  @Inject
  private AuthorService authorService;

  @Override
  public void run(String... args) throws Exception {
    Author rayBradbury = new Author("Ray", "Bradbury");
    Author leoTolstoy = new Author("Leo", "Tolstoy");

    Book book1 = new Book(rayBradbury,
      "Fahrenheit 451",
      "It was a special pleasure to see things " +
        "eaten, to see things blackened and changed.",
      92L,
      5L);

    Book book2 = new Book(rayBradbury,
      "Dandelion Wine",
      "It was a quiet morning, the town covered " +
        "over with darkness and at ease in bed.",
      42L,
      7L);

    rayBradbury.addBook(book1);
    rayBradbury.addBook(book2);

    Book book3 = new Book(leoTolstoy,
      "Anna Karenina",
      "Happy families are all alike; every unhappy " +
        "family is unhappy in its own way.",
      92L,
      7L);

    Book book4 = new Book(leoTolstoy,
      "War and Peace",
      "It was in July, 1805, and the speaker was the well-known " +
        "Anna Pávlovna Schérer, maid of honor and favorite of the " +
        "Empress Márya Fëdorovna.",
      93L,
      3L);

    leoTolstoy.addBook(book3);
    leoTolstoy.addBook(book4);

    authorService.save(rayBradbury);
    authorService.save(leoTolstoy);
  }
}
