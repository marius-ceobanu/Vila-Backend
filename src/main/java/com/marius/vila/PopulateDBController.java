package com.marius.vila;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/add-data")
public class PopulateDBController {

    private final PopulateDB populateDB;

    @GetMapping
    public void populateDb() {
        populateDB.populateDb();
    }

}
