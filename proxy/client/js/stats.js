function fetchStats(queryParams) {
  var xhttp = new XMLHttpRequest();

  xhttp.onload = function() {
    var stats = JSON.parse(xhttp.responseText);
    displayStats(stats);
  }

  var queryString = "?";

  if (queryParams !== undefined) {
    queryString += "minWordLength=" + queryParams.minWordLength + "&";
    queryString += "maxNumOfWords=" + queryParams.maxNumOfWords + "&";
    queryString += "wordsToBeExcluded=" + queryParams.wordsToBeExcluded;
  }

  xhttp.open("GET", "http://localhost/api/sentences-stats" + queryString, true);
  xhttp.send();
}

function updateStats() {
  var minWordLength = document.getElementById("min-word-length").value;
  var maxNumOfWords = document.getElementById("max-num-of-words").value;
  var wordsToBeExcluded = document.getElementById("words-tobe-excluded").value;

  if (minWordLength === "") {
    alert("Please specify minimal word length");
    return;
  }

  if (maxNumOfWords === "") {
    alert("Please specify maximum number of words to display");
    return;
  }

  var queryParams = {
    minWordLength: minWordLength,
    maxNumOfWords: maxNumOfWords,
    wordsToBeExcluded: wordsToBeExcluded
  };

  fetchStats(queryParams);
}

function displayStats(stats) {
  var statsTable = document.getElementById("stats-table");
  statsTable.innerHTML = "<tr><th>#</th><th>Word</th><th>Count</th></tr>";
  
  for (i = 0; i < stats.length; i++) {
    var wordNumber = document.createElement("td");
    wordNumber.innerHTML += i + 1;

    var word = document.createElement("td");
    word.innerHTML += stats[i].word;

    var wordCount = document.createElement("td");
    wordCount.innerHTML += stats[i].count;

    var wordStats = document.createElement("tr");

    wordStats.appendChild(wordNumber);
    wordStats.appendChild(word);
    wordStats.appendChild(wordCount);

    statsTable.appendChild(wordStats);
  }
}