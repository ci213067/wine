package com.cognac.env;

public interface ExecEnvExtStorage extends ExecEnvStorage {
    void putNew(String name, Object value);
    ExecEnvStorage where(String name);
    void setOuter(ExecEnvStorage e);
}
