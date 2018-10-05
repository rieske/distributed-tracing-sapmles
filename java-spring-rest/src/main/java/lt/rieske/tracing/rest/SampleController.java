package lt.rieske.tracing.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/sample")
class SampleController {

    @GetMapping
    String getSample() {
        return "{\"foo\":\"bar\"}";
    }
}
