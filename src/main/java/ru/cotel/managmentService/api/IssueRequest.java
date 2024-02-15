package ru.cotel.managmentService.api;

import lombok.Data;

@Data
public class IssueRequest {

    private long readerId;
    private long bookId;
}
