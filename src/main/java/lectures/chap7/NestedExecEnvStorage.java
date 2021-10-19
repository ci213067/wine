package lectures.chap7;

import com.cognac.env.ExecEnvExtStorage;
import com.cognac.env.ExecEnvStorage;

import java.util.HashMap;

public class NestedExecEnvStorage implements ExecEnvStorage {
    protected HashMap<String,Object> values;
    protected ExecEnvStorage outer;
    public NestedExecEnvStorage() { this(null); }
    public NestedExecEnvStorage(ExecEnvStorage e) {
        values = new HashMap<String,Object>();
        outer = e;
    }
    public void setOuter(ExecEnvStorage e) { outer = e; }
    public Object get(String name) {
        Object v = values.get(name);
        if (v == null && outer != null)
            return outer.get(name);
        else
            return v;
    }
    public void putNew(String name, Object value) { values.put(name, value); }
    public void put(String name, Object value) {
        ExecEnvStorage e = where(name);
        if (e == null)
            e = this;
        ((ExecEnvExtStorage)e).putNew(name, value);
    }
    public ExecEnvStorage where(String name) {
        if (values.get(name) != null)
            return this;
        else if (outer == null)
            return null;
        else
            return ((ExecEnvExtStorage)outer).where(name);
    }
}
