package de.lathspell.de.kspring2

import org.springframework.web.bind.annotation.*;
import java.io.File

@RestController
@RequestMapping("/rest")
class MgmtResource {

    @GetMapping("/ping")
    fun ping() = "pong"

}
