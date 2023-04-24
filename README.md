# Bookstore

A simple web app that allows you to store information about books and their authors, and also calculate some statistics for the books' content.

- [How to run](#how-to-run)
- [Bookstore API](#bookstore-api)
- [Request and Response Samples](#request-and-response-samples)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)

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
curl localhost/api/authors | json_pp
```

Response:

```
[
   {
      "authorId" : 1,
      "firstName" : "Ray",
      "lastName" : "Bradbury"
   },
   {
      "authorId" : 2,
      "firstName" : "Leo",
      "lastName" : "Tolstoy"
   },
   {
      "authorId" : 3,
      "firstName" : "Gabriel",
      "lastName" : "Garcia Marquez"
   }
]
```

#### Create a New Book

Request:

```
curl -X POST localhost/api/books\
   -H "Content-Type: application/json"\
   -d '{"authorId": 1, "title": "The Martian Chronicles", "firstSentence": "One minute it was Ohio winter, with doors closed, windows locked, the panes blind with frost, icicles fringing every roof, children skiing on slopes, housewives lumbering like great black bears in their furs along the icy streets.", "publicationYear": 1950}' | json_pp
```

Response:

```
{
   "author" : {
      "authorId" : 1,
      "firstName" : "Ray",
      "lastName" : "Bradbury"
   },
   "bookId" : 7,
   "firstSentence" : "One minute it was Ohio winter, with doors closed, windows locked, the panes blind with frost, icicles fringing every roof, children skiing on slopes, housewives lumbering like great black bears in their furs along the icy streets.",
   "publicationYear" : 1950,
   "title" : "The Martian Chronicles"
}
```

#### Get Stats for the Books' Content

Request:

```
curl localhost/api/sentences-stats | json_pp
```

Response:

```
[
   {
      "count" : 9,
      "word" : "the"
   },
   {
      "count" : 7,
      "word" : "was"
   },
   {
      "count" : 5,
      "word" : "in"
   },
   {
      "count" : 5,
      "word" : "it"
   },
   {
      "count" : 5,
      "word" : "with"
   }
]
```

## Architecture

...

The bookstore application consists of the following components:

- **Web Server**. Depending on the specified URL, the web server either returns HTML page with UI or makes a proxy pass to the application server.
- **Application Server**. Contains the core application logic. Provides an [HTTP API](#bookstore-api) that allows for creating, reading, updating, and deleting the application's resources, such as authors and books.
- **Sentence Analyzer**. A service that calculates stats for the books' sentences. Specifically, the service splits all sentences into separate words, filters them using the supplied conditions, and counts the number of times a word appears in the sentences. 
- **Database**. Stores information about authors and books.

## Tech Stack

Nginx, Java 17, Maven, Spring Boot, Jersey, Hibernate, Docker, PostgreSQL, HTML, CSS, Vanilla JS.