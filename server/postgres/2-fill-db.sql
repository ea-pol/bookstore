\c bookstore;

INSERT INTO
    author (first_name, last_name)
VALUES
    ('Ray', 'Bradbury'),
    ('Leo', 'Tolstoy');

INSERT INTO
    book (author_id, title, first_sentence, price, amount)
VALUES
    (1, 'Fahrenheit 451', 'It was a special pleasure to see things eaten, to see things blackened and changed..', 42, 5),
    (1, 'Dandelion Wine', 'It was a quiet morning, the town covered over with darkness and at ease in bed..', 43, 6),
    (2, 'Anna Karenina', 'Happy families are all alike; every unhappy family is unhappy in its own way..', 44, 7),
    (2, 'War and Peace', 'It was in July, 1805, and the speaker was the well-known Anna Pávlovna Schérer, maid of honor and favorite of the Empress Márya Fëdorovna..', 45, 8);
