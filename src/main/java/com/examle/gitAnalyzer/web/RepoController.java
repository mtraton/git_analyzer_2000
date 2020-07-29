package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.web.dto.CloneRepoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repo")
public class RepoController {

    @PostMapping
    public ResponseEntity<?> cloneRepo(@RequestBody CloneRepoDto request) {
        System.out.println("Cloning repo: " + request.getRepoUrl() );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
