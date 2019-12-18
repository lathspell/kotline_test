package micronaut.swagger

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.parameters.RequestBody

@Controller
class RestController {

    @Get(uri = "/", produces = [MediaType.TEXT_PLAIN])
    @Operation(summary = "Startpage", requestBody = RequestBody(description = "greeting line", content = [Content(mediaType = "text/plain")]))
    fun index() = "Hello World!"
}
