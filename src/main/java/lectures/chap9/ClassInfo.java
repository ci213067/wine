package lectures.chap9;

import com.cognac.wine.syntax.list.ClassBody;
import com.cognac.wine.syntax.list.stmnt.ClassStmnt;
import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.CognacException;

public class ClassInfo {
    protected ClassStmnt definition;
    protected ExecEnvStorage execEnvStorage;
    protected ClassInfo superClass;
    public ClassInfo(ClassStmnt cs, ExecEnvStorage env) {
        definition = cs;
        execEnvStorage = env;
        Object obj = env.get(cs.superClass());
        if (obj == null)
            superClass = null;
        else if (obj instanceof ClassInfo)
            superClass = (ClassInfo)obj;
        else
            throw new CognacException("unknown super class: " + cs.superClass(),
                                     cs);
    }
    public String name() { return definition.name(); }
    public ClassInfo superClass() { return superClass; }
    public ClassBody body() { return definition.body(); }
    public ExecEnvStorage environment() { return execEnvStorage; }
    @Override
    public String toString() { return "<class " + name() + ">"; }
}
