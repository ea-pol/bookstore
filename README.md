# Bookstore

A simple web app that allows you to store information about books and their authors, and also calculate some statistics for the books' content.

- [How to run](#how-to-run)
- [Bookstore API](#bookstore-api)
- [Request and Response Samples](#request-and-response-samples)
- [Architecture](#architecture)

## How to Run

1. Clone the repository.
2. Move to the project's folder.
3. Run `docker compose up`.

The application utilizes port 80. You can interact with the app using the browser (open `localhost:80` or just `localhost`) or directly via [Bookstore API](#bookstore-api).

## Bookstore API

Bookstore API is an HTTP API that consumes and produces data in the JSON format. The API includes the following endpoints:

| Endpoint | Available HTTP Methods |
| ------------- | ------------- |
| api/authors | GET, POST |
| api/authors/{id} | GET, PUT, DELETE |
| api/books | GET, POST |
| api/books/{id} | GET, PUT, DELETE |
| api/sentences-stats | GET |

### Schemas

#### Author

```
{
  id,
  firstName,
  lastName
}
```

#### Book

```
{
  id,
  authorId,
  title,
  firstSentence,
  publicationYear
}
```

#### Sentences Stats

```
[
  {
    word,
    count
  }
]
```

### Request and Response Samples

#### Get All Authors

Request:

```
...
```

Response:

```
...
```

#### Create a New Book

Request:

```
...
```

Response:

```
...
```

#### Get Stats for the Books' Content

Request:

```
...
```

Response:

```
...
```

## Architecture

...

Components:

- **Web Server** (Nginx). Depending on the specified URL, the web server either returns HTML page to the client or proxies the request to the application server.
- **Application Server** (Java 17, Maven, Spring Boot, Jersey, Hibernate). Contains the core application logic. Provides an HTTP API that allows for creating, reading, updating, and deleting the application's resources, such as authors and books.
- **Sentence Analyzer**. A service that calculates stats for the books' content. Specifically, the service filter words using a filter condition and counts the number of occurrences for each word.
- **Database** (PostgreSQL). Stores information about authors and books.
