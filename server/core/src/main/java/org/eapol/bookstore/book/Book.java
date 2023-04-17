package org.eapol.bookstore.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "title")
    private String title;

    @Column(name = "first_sentence")
    private String firstSentence;

    @Column(name = "price")
    private Long price;

    @Column(name = "amount")
    private Long amount;
}
