\c bookstore;

INSERT INTO
    author (first_name, last_name)
VALUES
    ('Ray', 'Bradbury'),
    ('Leo', 'Tolstoy'),
    ('Gabriel', 'Garcia Marquez');

INSERT INTO
    book (author_id, title, first_sentence, publication_year)
VALUES
    (1, 'Fahrenheit 451', 'It was a special pleasure to see things eaten, to see things blackened and changed..', 1953),
    (1, 'Dandelion Wine', 'It was a quiet morning, the town covered over with darkness and at ease in bed..', 1957),
    (2, 'Anna Karenina', 'Happy families are all alike; every unhappy family is unhappy in its own way..', 1878),
    (2, 'War and Peace', 'It was in July, 1805, and the speaker was the well-known Anna Pávlovna Schérer, maid of honor and favorite of the Empress Márya Fëdorovna..', 1869),
    (3, 'One Hundred Years of Solitude', 'Many years later, as he faced the firing squad, Colonel Aureliano Buendia was to remember that distant afternoon when his father took him to discover ice..', 1967);
