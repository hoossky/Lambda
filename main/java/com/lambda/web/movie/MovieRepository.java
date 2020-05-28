package com.lambda.web.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
