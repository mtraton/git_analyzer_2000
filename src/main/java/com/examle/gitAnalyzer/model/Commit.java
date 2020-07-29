package com.examle.gitAnalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Commit {
    private String author;
    private LocalDateTime dateTime;
    private String message;
}
