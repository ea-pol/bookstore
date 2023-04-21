package org.eapol.bookstore.stats.dto;

public class WordStats {
  private String word;
  private Long count;

  public WordStats() { }

  public WordStats(String word, Long count) {
    this.word = word;
    this.count = count;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }
}
