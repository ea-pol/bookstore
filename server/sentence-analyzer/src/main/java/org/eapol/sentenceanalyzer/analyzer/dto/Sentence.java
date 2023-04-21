package org.eapol.sentenceanalyzer.analyzer.dto;

public class Sentence {
  private String sentence;

  public Sentence() {}

  public Sentence(String sentence) {
    this.sentence = sentence;
  }

  public String getSentence() {
    return sentence;
  }

  public void setSentence(String sentence) {
    this.sentence = sentence;
  }
}
