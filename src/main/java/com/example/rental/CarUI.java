package com.example.rental;

import com.example.rental.backend.Car;
import com.example.rental.service.CarService;
import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Temat: Wypożyczalnia samochodów
 *
 * @author Leszek Baca
 * @version 1.0
 */


@Title("Rental car")
@SpringUI
public class CarUI extends UI {


    private CarService service;

    private Car car = new Car();
    private Grid<Car> grid;
    Binder<Car> binder;

    TextField producer;
    TextField model;
    TextField yearOfProduction;
    TextField color;

    private Button add;
    private Button delete;
    private Button save;


    @Autowired
    public CarUI(CarService service) {
        this.service = service;
        this.grid = new Grid<>(Car.class);
        this.binder = new Binder<>(Car.class);

        this.model = new TextField("Model");

        this.producer = new TextField("Producer");

        this.yearOfProduction = new TextField("Year of production");

        this.color = new TextField("Color");


    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Label label = new Label("Rental car");
        label.addStyleName(ValoTheme.LABEL_H1);
        label.setSizeUndefined();
        Button save = new Button("Save", FontAwesome.SAVE);
        Button add = new Button("Add");

        Button delete = new Button("Delete", FontAwesome.TRASH_O);

        binder.forField(producer).withValidator(producer -> producer.length() > 0, "The field can't be empty").
                bind(Car::getProducer, Car::setProducer);
        binder.forField(model).withValidator(producer -> producer.length() > 0, "The field can't be empty").
                bind(Car::getModel, Car::setModel);
        binder.forField(yearOfProduction).withValidator(producer -> producer.length() > 0, "The field can't be empty").
                bind(Car::getYearOfProduction, Car::setYearOfProduction);
        binder.forField(color).withValidator(producer -> producer.length() > 0, "The field can't be empty").
                bind(Car::getColor, Car::setColor);

        binder.setBean(this.car);
        binder.bindInstanceFields(this);

        grid.setColumns("id", "producer", "model", "yearOfProduction", "color");
        grid.setSizeFull();
        grid.addSelectionListener(e -> updateForm());

        HorizontalLayout layout = new HorizontalLayout(producer, model, yearOfProduction, color);
        HorizontalLayout layoutButton = new HorizontalLayout(add, save, delete);
        VerticalLayout mainLayout = new VerticalLayout(label, grid, layout, layoutButton);

        setContent(mainLayout);
        updateGrid();
        updateForm();

        delete.addClickListener(e -> deleteCar());

        save.addClickListener(e -> editCar());

        add.addClickListener(e -> {
            if (!(producer.isEmpty() || model.isEmpty() || yearOfProduction.isEmpty() || color.isEmpty())) {
                saveCar();
                setClear();

            }


        });

    }

    void updateGrid() {
        List<Car> car = service.findAll();
        grid.setItems(car);
        grid.deselectAll();
        setFormVisible(true);


    }

    void setFormVisible(boolean visible) {
        producer.setVisible(visible);
        model.setVisible(visible);
        yearOfProduction.setVisible(visible);
        color.setVisible(visible);

    }

    void setClear() {

        producer.setValue("");
        model.setValue("");
        yearOfProduction.setValue("");
        color.setValue("");
        car.setId(0);

    }

    void updateForm() {
        if (grid.asSingleSelect().isEmpty()) {
            setFormVisible(true);
        } else {

            car = grid.asSingleSelect().getValue();
            binder.setBean(car);
            grid.deselectAll();

        }
    }

    void saveCar() {

        service.insert(car);
        updateGrid();

    }

    void editCar() {

        service.update(car);
        updateGrid();

    }

    void deleteCar() {

        service.delete(car);
        updateGrid();


    }
}
