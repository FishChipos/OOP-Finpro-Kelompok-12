package com.sundaempire.frontend;

public interface Observable {
    void addObserver(Notifiable observer);
    void removeObserver(Notifiable observer);
}
