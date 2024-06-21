package br.com.renan.verificadornumerosprimos.model;

public interface ResponseFormat {

    int getColor();

    String getDescription();

    String getDivisorMessage(int divisor);

}
