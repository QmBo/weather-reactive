package ru.job4j.weather;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

/**
 * WeatherControl class.
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 1.0
 * @since 19.11.2021
 */
@RestController
public class WeatherControl {

    private final WeatherService weathers;

    /**
     * Instantiates a new Weather control.
     *
     * @param weathers the weathers
     */
    public WeatherControl(WeatherService weathers) {
        this.weathers = weathers;
    }

    /**
     * All flux.
     *
     * @return all elements
     */
    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> all() {
        Flux<Weather> data = weathers.all();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }

    /**
     * Get mono.
     *
     * @param id the id
     * @return element by id
     */
    @GetMapping(value = "/get/{id}")
    public Mono<Weather> get(@PathVariable Integer id) {
        return weathers.findById(id);
    }

    /**
     * Hottest mono.
     *
     * @return hottest element
     */
    @GetMapping(value = "/hottest")
    public Mono<Weather> hottest() {
        return weathers.findHottest();
    }

    /**
     * City great then flux.
     *
     * @param temp the temp
     * @return element ove temp
     */
    @GetMapping(value = "/cityGreatThen/{temp}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> cityGreatThen(@PathVariable Integer temp) {
        Flux<Weather> data = weathers.cityGreatThen(temp);
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(2));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }
}
