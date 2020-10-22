package Commandline;

import javafx.fxml.FXML;

import java.util.Objects;

public class Word {
    @FXML
    private String word_target;
    private String word_explain;

    public Word(String a,String b) {
        this.word_target=a;
        this.word_explain=b;
    }

    public Word() {

    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word_target='" + word_target + '\'' +
                ", word_explain='" + word_explain + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return Objects.equals(word_target, word.word_target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word_target, word_explain);
    }
}