package ru.job4j.weather;

/**
 * Weather class.
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 1.0
 * @since 19.11.2021
 */
public class Weather {
    private final int id;
    private final String city;
    private final int temperature;

    /**
     * Instantiates a new Weather.
     *
     * @param id          the id
     * @param city        the city
     * @param temperature the temperature
     */
    public Weather(int id, String city, int temperature) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets temperature.
     *
     * @return the temperature
     */
    public int getTemperature() {
        return temperature;
    }
}
