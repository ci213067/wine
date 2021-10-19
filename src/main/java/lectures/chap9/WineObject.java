package lectures.chap9;


import com.cognac.env.ExecEnvExtStorage;
import com.cognac.env.ExecEnvStorage;

public class WineObject {
    public static class AccessException extends Exception {}
    protected ExecEnvStorage env;
    public WineObject(ExecEnvStorage e) { env = e; }
    @Override public String toString() { return "<object:" + hashCode() + ">"; }
    public Object read(String member) throws AccessException {
        return getEnv(member).get(member);
    }
    public void write(String member, Object value) throws AccessException {
        ((ExecEnvExtStorage)getEnv(member)).putNew(member, value);
    }
    protected ExecEnvStorage getEnv(String member) throws AccessException {
        ExecEnvStorage e = ((ExecEnvExtStorage)env).where(member);
        if (e != null && e == env)
            return e;
        else
            throw new AccessException();
    }
}
