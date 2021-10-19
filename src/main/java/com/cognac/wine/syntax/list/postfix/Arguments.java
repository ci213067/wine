package com.cognac.wine.syntax.list.postfix;
import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.CognacException;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.syntax.list.ParameterList;
import lectures.chap7.Function;
import lectures.chap8.NativeFunction;

import java.util.List;

public class Arguments extends Postfix {
    public Arguments(List<ASTree> c) { super(c); }
    public int size() { return numChildren(); }
    @Override
    public Object eval(ExecEnvStorage callerEnv, Object value) {
        if(value instanceof Function){
            Function func = (Function)value;
            ParameterList params = func.parameters();
            if (size() != params.size())
                throw new CognacException("bad number of arguments", this);
            ExecEnvStorage newEnv = func.makeEnv();
            int num = 0;
            for (ASTree a: this)
                params.eval(newEnv, num++, a.eval(callerEnv));
            return func.body().eval(newEnv);
        }else if(value instanceof NativeFunction){
            NativeFunction func = (NativeFunction)value;
            int numOfParameters = func.numOfParameters();
            if (size() != numOfParameters)
                throw new CognacException("bad number of arguments", this);
            Object[] args = new Object[numOfParameters];
            int num = 0;
            for (ASTree a: this) {
                args[num++] = a.eval(callerEnv);
            }
            return func.invoke(args, this);
        }else{
            throw new CognacException("bad arguments", this);
        }
    }
}
