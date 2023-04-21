# Bookstore

A simple web app that allows you to store information about books and their authors, and also calculate some statistics for the books' content.

## How to Run

1. Clone the repository.
2. Move to the project's folder.
3. Run `docker compose up -d`.

...

## Bookstore API

- api/authors (GET, POST)
- api/authors/{id} (GET, PUT, DELETE)
- api/books (GET, POST)
- api/books/{id} (GET, PUT, DELETE)
- api/sentences-stats (GET)

### Schemas

Author:

```
{
  id,
  first_name,
  last_name
}
```

Book:

```
{
  id,
  author_id,
  title,
  first_sentence,
  price,
  amount
}
```

Sentences stats:

```
[
  {
    word,
    count
  }
]
```

### Request and Response Samples

...

## Architecture

...

## Database Schema

...
