package lectures.chap7;

import com.cognac.wine.syntax.list.ParameterList;
import com.cognac.wine.syntax.list.stmnt.BlockStmnt;
import com.cognac.env.ExecEnvStorage;

public class Function {
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected ExecEnvStorage env;
    public Function(ParameterList parameters, BlockStmnt body, ExecEnvStorage env) {
        this.parameters = parameters;
        this.body = body;
        this.env = env;
    }
    public ParameterList parameters() { return parameters; }
    public BlockStmnt body() { return body; }
    public ExecEnvStorage makeEnv() { return new NestedExecEnvStorage(env); }
    @Override public String toString() { return "<fun:" + hashCode() + ">"; }
}
