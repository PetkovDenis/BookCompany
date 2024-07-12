package ru.petkov.bookcompany.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "randomId", url = "https://httpbin.org/uuid")
public interface RandomIdFeign {

    @GetMapping
    String getRandomId();

}
