package com.example.rental.service;

import com.example.rental.backend.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Temat: Wypożyczalnia samochodów
 *
 * @author Leszek Baca
 * @version 1.0
 */

@Service
public class CarService {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Car> findAll() {
        List<Car> list = jdbcTemplate.query(
                "SELECT id,  producer,model,yearOfProduction, color FROM car",
                (rs, rowNum) -> new Car(rs.getInt("id"),
                        rs.getString("producer"), rs.getString("model"),
                        rs.getString("yearOfProduction"), rs.getString("color")));
        return list;
    }

    public void update(Car car) {
        jdbcTemplate.update(
                "UPDATE car SET producer=?, model=?, yearOfProduction =?, color =? WHERE id=?",
                car.getProducer(), car.getModel(), car.getYearOfProduction(), car.getColor(), car.getId());

    }


    public void insert(Car car) {
        jdbcTemplate.update(
                "INSERT into car (PRODUCER, MODEL,YEAROFPRODUCTION,COLOR) VALUES (?,?,?,?)",
                car.getProducer(), car.getModel(), car.getYearOfProduction(), car.getColor());


    }

    public void delete(Car car) {
        jdbcTemplate.update("DELETE FROM car WHERE id=?", car.getId());


    }

}