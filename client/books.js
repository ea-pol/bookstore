function fetchBooks() {
  var books = [];

  var book1 = {
    id: 1,
    author: "Ray Bradbury",
    title: "Title 1",
    firstSentence: "First sentence 1",
    price: "42",
    amount: "92"
  };

  var book2 = {
    id: 2,
    author: "Ray Bradbury",
    title: "Title 2",
    firstSentence: "First sentence 2",
    price: "42",
    amount: "92"
  };

  var book3 = {
    id: 3,
    author: "Ray Bradbury",
    title: "Title 3",
    firstSentence: "First sentence 3",
    price: "42",
    amount: "92"
  }

  var book4 = {
    id: 4,
    author: "Ray Bradbury",
    title: "Title 4",
    firstSentence: "First sentence 4",
    price: "42",
    amount: "92"
  }

  books.push(book1);
  books.push(book2);
  books.push(book3);
  books.push(book4);

  return books;
}

function displayBooks() {
  var booksList = document.getElementById("books-list");
  booksList.innerHTML = "";

  for (i = 0; i < books.length; i++) {
    var book = document.createElement("div");
    book.innerHTML += "Title: " + books[i].title + " ";
    book.innerHTML += "Author: " + books[i].author + " ";
    book.innerHTML += "Price: " + books[i].price + " ";
    book.innerHTML += "Amount: " + books[i].amount + " ";

    var firstSentence = document.createElement("div");
    firstSentence.innerHTML += books[i].firstSentence;

    var removeButton = document.createElement("div");
    removeButton.innerHTML += "x"
    removeButton.addEventListener("click", removeBook);
    removeButton.setAttribute("data-book-id", books[i].id);

    var booksListItem = document.createElement("div");
    booksListItem.appendChild(book);
    booksListItem.appendChild(firstSentence);
    booksListItem.appendChild(removeButton);

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
  var bookPrice = document.getElementById("book-price").value;
  var bookAmount = document.getElementById("book-amount").value;

  var book = {
    id: currentBookId,
    author: bookAuthor,
    title: bookTitle,
    firstSentence: bookFirstSentence,
    price: bookPrice,
    amount: bookAmount
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
