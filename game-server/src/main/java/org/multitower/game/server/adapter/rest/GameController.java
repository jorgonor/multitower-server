package org.multitower.game.server.adapter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @GetMapping
    Mono<ResponseEntity<List<String>>> get() {
        return Mono.just(ResponseEntity.ok(
            List.of("a", "b", "c", "d", "e")
        ));
    }
}
