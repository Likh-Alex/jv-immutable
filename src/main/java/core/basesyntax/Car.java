package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Car implements Cloneable {
    private final int year;
    private final String color;
    private final List<Wheel> wheels;
    private final Engine engine;

    public Car(int year, String color, List<Wheel> wheels, Engine engine) {
        this.year = year;
        this.color = color;
        this.wheels = clonedWheels(wheels);
        this.engine = checkEngine(engine) ? engine.clone() : null;
    }

    public Car changeEngine(Engine engine) {
        return new Car(year, color, wheels, engine.clone());
    }

    public Car changeColor(String newColor) {
        return new Car(year, newColor, wheels, engine.clone());
    }

    public Car addWheel(Wheel newWheel) {
        List<Wheel> newWheels = new ArrayList<>();
        for (Wheel wheel : wheels) {
            newWheels.add(wheel.clone());
        }
        newWheels.add(newWheel);
        return new Car(year, color, newWheels, engine.clone());
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    private List<Wheel> clonedWheels(List<Wheel> wheels) {
        List<Wheel> clonedWheels = new ArrayList<>();
        for (Wheel wheel : wheels) {
            clonedWheels.add(wheel.clone());
        }
        return clonedWheels;
    }

    public List<Wheel> getWheels() {
        return new ArrayList<>(clonedWheels(wheels));
    }

    private boolean checkEngine(Engine engine) {
        return engine != null;
    }

    public Engine getEngine() {
        return checkEngine(engine) ? engine.clone() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return year == car.year
                && Objects.equals(color, car.color)
                && Objects.equals(wheels, car.wheels)
                && Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, color, wheels, engine);
    }

    @Override
    protected Car clone() {
        try {
            return (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can not clone car", e);
        }
    }
}
