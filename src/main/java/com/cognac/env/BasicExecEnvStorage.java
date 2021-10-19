package com.cognac.env;

import java.util.HashMap;

public class BasicExecEnvStorage implements ExecEnvStorage {
    protected HashMap<String,Object> values;
    public BasicExecEnvStorage() {
        values = new HashMap<String,Object>();
    }
    public void put(String name, Object value) {
        values.put(name, value);
    }
    public Object get(String name) {
        return values.get(name);
    }
}
