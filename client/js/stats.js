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

  stats.push(wordStats1);
  stats.push(wordStats2);

  return stats;
}

function displayStats() {
  var statsContainer = document.getElementById("stats-container");
  statsContainer.innerHTML = "";
  
  for (i = 0; i < stats.length; i++) {
    var wordStats = document.createElement("div");
    wordStats.innerHTML += stats[i].word + ": " + stats[i].count;
    statsContainer.appendChild(wordStats);
  }
}