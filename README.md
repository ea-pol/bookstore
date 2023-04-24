# Bookstore

A simple web app that allows you to store information about books and their authors, and also calculate some statistics for the books' content.

## How to Run

1. Clone the repository.
2. Move to the project's folder.
3. Run `docker compose up`.

The application utilizes port 80. You can interact with the app using the browser (open localhost:80 or just localhost) or directly via [Bookstore API](#bookstore-api).

## Bookstore API

Bookstore API is an HTTP API that consumes and produces data in the JSON format. The API includes the following endpoints:

| Endpoint | Available HTTP methods |
| ------------- | ------------- |
| api/authors | GET, POST |
| api/authors/{id} | GET, PUT, DELETE |
| api/books | GET, POST |
| api/books/{id} | GET, PUT, DELETE |
| api/sentences-stats | GET |

### Schemas

Author:

```
{
  id,
  firstName,
  lastName
}
```

Book:

```
{
  id,
  authorId,
  title,
  firstSentence,
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

## Tech Stack

Java 17, Hibernate, PostgreSQL, Docker, Nginx, Spring Boot, Jersey, Maven, HTML, CSS, Vanilla JS.