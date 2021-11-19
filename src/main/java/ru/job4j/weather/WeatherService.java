package ru.job4j.weather;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WeatherService class.
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 1.0
 * @since 19.11.2021
 */
@Service
public class WeatherService {
    private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();

    {
        weathers.put(1, new Weather(1, "Msc", 20));
        weathers.put(2, new Weather(2, "SPb", 15));
        weathers.put(3, new Weather(3, "Bryansk", 15));
        weathers.put(4, new Weather(4, "Smolensk", 15));
        weathers.put(5, new Weather(5, "Kiev", 15));
        weathers.put(6, new Weather(6, "Minsk", 15));
    }

    /**
     * Find by id mono.
     *
     * @param id the id
     * @return element by id
     */
    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weathers.get(id));
    }

    /**
     * All flux.
     *
     * @return all elements
     */
    public Flux<Weather> all() {
        return Flux.fromIterable(weathers.values());
    }

    /**
     * Find hottest mono.
     *
     * @return hottest element
     */
    public Mono<Weather> findHottest() {
        return Mono.justOrEmpty(weathers.values().stream().max(Comparator.comparingInt(Weather::getTemperature)));
    }

    /**
     * City great then flux.
     *
     * @param temp the temp
     * @return element wis temp great
     */
    public Flux<Weather> cityGreatThen(Integer temp) {
        return Flux.fromStream(
                weathers.values()
                        .stream()
                        .filter(weather -> weather.getTemperature() > temp)
        );
    }
}
