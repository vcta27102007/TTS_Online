package com.example.demo.service;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {
    private final AtomicLong seq;

    public IdGenerator(long start) {
        this.seq = new AtomicLong(start);
    }

    public long nextId() {
        return seq.getAndIncrement();
    }
}
