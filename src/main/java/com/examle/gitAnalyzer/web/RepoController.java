package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.service.GitRepoCloner;
import com.examle.gitAnalyzer.web.dto.CloneRepoDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repo")
@AllArgsConstructor
public class RepoController {
    private GitRepoCloner cloner;

    @PostMapping
    public ResponseEntity<?> cloneRepo(@RequestBody CloneRepoDto request) {
        System.out.println("Cloning repo: " + request.getRepoUrl() );
        cloner.cloneRepository(request.getRepoUrl());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
