package ru.swdmi.booklibrary.service.model;

import lombok.Getter;

@Getter
public abstract class AbstractBookSaveRequest {
    private Long id;
    private final String title;

    public AbstractBookSaveRequest(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public AbstractBookSaveRequest(String title) {
        this.title = title;
    }

    public boolean isCreatable() {
        return this.id == null;
    }
}
