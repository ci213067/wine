package com.cognac.wine.syntax.list.postfix;
import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.CognacException;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.syntax.list.postfix.Postfix;

import java.util.List;

public class ArrayRef extends Postfix {
    public ArrayRef(List<ASTree> c) { super(c); }
    public ASTree index() { return child(0); }
    public String toString() { return "[" + index() + "]"; }
    @Override
    public Object eval(ExecEnvStorage env, Object value) {
        if (value instanceof Object[]) {
            Object index = index().eval(env);
            if (index instanceof Integer)
                return ((Object[])value)[(Integer)index];
        }

        throw new CognacException("bad array access", this);
    }
}
