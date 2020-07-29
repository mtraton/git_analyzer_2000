package com.examle.gitAnalyzer.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepoLister {

    @Value("${repo.path}")
    private String path;

    @SneakyThrows
    public List<String> listRepositories() {
        return Files.list(Paths.get(path))
                .filter(p -> Files.isDirectory(p))
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
    }

}
