function fetchBooks() {
  var books = [];

  var book1 = {
    id: 1,
    author: "Ray Bradbury",
    title: "Fahrenheit 451",
    firstSentence: "It was a special pleasure to see things eaten, to see things blackened and changed..",
    publicationDate: 1953
  };

  var book2 = {
    id: 2,
    author: "Ray Bradbury",
    title: "Dandelion Wine",
    firstSentence: "It was a quiet morning, the town covered over with darkness and at ease in bed..",
    publicationDate: 1957
  };

  var book3 = {
    id: 3,
    author: "Leo Tolstoy",
    title: "Anna Karenina",
    firstSentence: "Happy families are all alike; every unhappy family is unhappy in its own way..",
    publicationDate: 1878
  };

  var book4 = {
    id: 4,
    author: "Leo Tolstoy",
    title: "War and Peace",
    firstSentence: "It was in July, 1805, and the speaker was the well-known Anna Pavlovna Scherer, maid of honor and favorite of the Empress Marya Fedorovna..",
    publicationDate: 1869
  };

  var book5 = {
    id: 5,
    author: "Gabriel Garcia Marquez",
    title: "One Hundred Years of Solitude",
    firstSentence: "Many years later, as he faced the firing squad, Colonel Aureliano Buendia was to remember that distant afternoon when his father took him to discover ice..",
    publicationDate: 1967
  };

  books.push(book1);
  books.push(book2);
  books.push(book3);
  books.push(book4);
  books.push(book5);

  return books;
}

function displayBooks() {
  var booksList = document.getElementById("books-list");
  booksList.innerHTML = "";

  for (i = 0; i < books.length; i++) {
    var book = document.createElement("div");
    book.innerHTML += "<strong>" + books[i].title 
      + " by " + books[i].author + ", " 
      + books[i].publicationDate + "</strong>";

    book.className = "book-list-item-content-item";
    
    var firstSentence = document.createElement("div");
    firstSentence.innerHTML += books[i].firstSentence;
    firstSentence.className = "book-list-item-content-item";

    var bookContent = document.createElement("div");
    bookContent.appendChild(book);
    bookContent.appendChild(firstSentence);
    bookContent.className = "book-list-item-content";

    var removeButton = document.createElement("div");
    removeButton.innerHTML += "x"
    removeButton.addEventListener("click", removeBook);
    removeButton.setAttribute("data-book-id", books[i].id);
    removeButton.className = "book-list-item-remove-button";

    var booksListItem = document.createElement("div");
    booksListItem.appendChild(bookContent);
    booksListItem.appendChild(removeButton);
    booksListItem.className = "book-list-item";

    booksList.appendChild(booksListItem);
  }
}

function updateAuthorSelector() {
  var authorSelector = document.getElementById("author-selector");
  var authorsList = document.getElementById("authors-list").children;

  authorSelector.innerHTML = "";

  for (i = 0; i < authorsList.length; i++) {
    var author = document.createElement("option");
    author.innerHTML += authorsList[i].children[0].innerHTML;
    author.setAttribute("value", authorsList[i].children[1].dataset.authorId);
    authorSelector.appendChild(author);
  }
}

function addNewBook() {
  var bookTitle = document.getElementById("book-title").value;

  var authorSelector = document.getElementById("author-selector");
  var bookAuthor = authorSelector.options[authorSelector.selectedIndex].text;

  var bookFirstSentence = document.getElementById("book-first-sentence").value;
  var bookPublicationDate = document.getElementById("book-publication-date").value;

  var book = {
    id: currentBookId,
    author: bookAuthor,
    title: bookTitle,
    firstSentence: bookFirstSentence,
    publicationDate: bookPublicationDate
  };

  currentBookId += 1;
  books.push(book);

  displayBooks();
}

function removeBook(event) {
  var bookToRemoveId = event.currentTarget.dataset.bookId;
  books = books.filter(book => book.id != bookToRemoveId);
  displayBooks();
}
