package org.eapol.sentenceanalyzer.analyzer.dto;

import java.util.List;

public class SentenceAnalyzerRequestParams {
  private List<String> sentences;
  private Integer minWordLength;
  private Integer maxNumOfWords;
  private List<String> wordsToBeExcluded;

  public SentenceAnalyzerRequestParams(
    List<String> sentences,
    Integer minWordLength,
    Integer maxNumOfWords,
    List<String> wordsToBeExcluded
  ) {
    this.sentences = sentences;
    this.minWordLength = minWordLength;
    this.maxNumOfWords = maxNumOfWords;
    this.wordsToBeExcluded = wordsToBeExcluded;
  }

  public List<String> getSentences() {
    return sentences;
  }

  public void setSentences(List<String> sentences) {
    this.sentences = sentences;
  }

  public Integer getMinWordLength() {
    return minWordLength;
  }

  public void setMinWordLength(Integer minWordLength) {
    this.minWordLength = minWordLength;
  }

  public Integer getMaxNumOfWords() {
    return maxNumOfWords;
  }

  public void setMaxNumOfWords(Integer maxNumOfWords) {
    this.maxNumOfWords = maxNumOfWords;
  }

  public List<String> getWordsToBeExcluded() {
    return wordsToBeExcluded;
  }

  public void setWordsToBeExcluded(List<String> wordsToBeExcluded) {
    this.wordsToBeExcluded = wordsToBeExcluded;
  }
}

