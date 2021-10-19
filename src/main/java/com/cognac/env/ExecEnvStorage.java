package com.cognac.env;

public interface ExecEnvStorage {
    void put(String name, Object value);
    Object get(String name);
}
