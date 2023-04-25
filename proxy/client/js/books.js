function fetchBooksList() {
  var xhttp = new XMLHttpRequest();

  xhttp.onload = function() {
    var books = JSON.parse(xhttp.responseText);
    var booksList = document.getElementById("books-list");
    booksList.innerHTML = "";

    for (i = 0; i < books.length; i++) {
      addBookToList(booksList, books[i]);
    }
  }

  xhttp.open("GET", "http://localhost/api/books", true);
  xhttp.send();
}

function addBookToList(booksList, book) {
  var bookDiv = document.createElement("div");
  bookDiv.innerHTML += "<strong>" + book.title 
    + " by " + book.author.firstName + " " 
    + book.author.lastName + ", " 
    + book.publicationYear + "</strong>";

  bookDiv.className = "book-list-item-content-item";
  
  var firstSentenceDiv = document.createElement("div");
  firstSentenceDiv.innerHTML += book.firstSentence;
  firstSentenceDiv.className = "book-list-item-content-item";

  var bookContentDiv = document.createElement("div");
  bookContentDiv.appendChild(bookDiv);
  bookContentDiv.appendChild(firstSentenceDiv);
  bookContentDiv.className = "book-list-item-content";

  var removeButton = document.createElement("div");
  removeButton.innerHTML += "x"
  removeButton.addEventListener("click", removeBook);
  removeButton.className = "book-list-item-remove-button";

  var booksListItem = document.createElement("div");
  booksListItem.appendChild(bookContentDiv);
  booksListItem.appendChild(removeButton);
  booksListItem.className = "book-list-item";
  booksListItem.setAttribute("data-book-id", book.bookId);

  booksList.appendChild(booksListItem);  
}

function removeBookFromList(bookId) {
  var book = document.querySelector(`[data-book-id='${bookId}']`);
  book.parentNode.removeChild(book);
}

function createBook() {
  var bookTitle = document.getElementById("book-title").value;
  var bookAuthorId = document.getElementById("author-selector").value;
  var bookFirstSentence = document.getElementById("book-first-sentence").value;
  var bookPublicationYear = document.getElementById("book-publication-year").value;

  if (bookAuthorId === "") {
    alert("Please specify book author");
    return;
  }

  if (bookTitle === "") {
    alert("Please specify book title");
    return;
  }

  if (bookPublicationYear === "") {
    alert("Please specify book publication year");
    return;
  }

  if (bookFirstSentence === "") {
    alert("Please specify book first sentence");
    return;
  }

  var newBook = {
    authorId: bookAuthorId,
    title: bookTitle,
    firstSentence: bookFirstSentence,
    publicationYear: bookPublicationYear
  };

  var xhttp = new XMLHttpRequest();

  xhttp.onload = function() {
    if (xhttp.status == 422) {
      alert(`The selected author already has a book called ${bookTitle}`);
      return;
    }

    var book = JSON.parse(xhttp.responseText);
    var booksList = document.getElementById("books-list");
    addBookToList(booksList, book);
  }
  
  xhttp.open("POST", "http://localhost/api/books", true);
  xhttp.setRequestHeader('Content-type', 'application/json');
  xhttp.send(JSON.stringify(newBook));
}

function removeBook(event) {
  var bookToRemoveId = event.currentTarget.parentNode.dataset.bookId;
  var bookTitle = event.currentTarget.parentNode.children[0].children[0].innerHTML;
  var bookTitle = bookTitle.split(" by")[0].split(">")[1];

  var remove = confirm(`Are you sure that you want to`
    + ` remove "${bookTitle}" from the list of books?`);

  if (remove) {
    var xhttp = new XMLHttpRequest();

    xhttp.onload = function() {
      removeBookFromList(bookToRemoveId);
    }
  
    xhttp.open("DELETE", "http://localhost/api/books/" + bookToRemoveId, true);
    xhttp.send();
  }
}
