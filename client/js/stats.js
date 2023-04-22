function fetchStats() {
  var stats = []

  var wordStats1 = {
    word: "the",
    count: 42
  };

  var wordStats2 = {
    word: "a",
    count: 92
  };

  var wordStats3 = {
    word: "hello",
    count: 144
  };

  stats.push(wordStats1);
  stats.push(wordStats2);
  stats.push(wordStats3);

  return stats;
}

function displayStats() {
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