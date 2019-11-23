package com.example.rental.backend;

/**
 *
 Temat: Wypożyczalnia samochodów
 * @author Leszek Baca
 * @version 1.0
 */


public class Car {

    private Integer id;

    private String producer;


    private String model;


    private String yearOfProduction;


    private String color;


    public Car(Integer id, String producer, String model, String yearOfProduction, String color) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.color = color;
    }

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
