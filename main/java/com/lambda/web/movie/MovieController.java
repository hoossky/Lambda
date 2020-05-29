package com.lambda.web.movie;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@RestController
public class MovieController {
}
