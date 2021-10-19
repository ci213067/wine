package com.cognac.wine.syntax.list;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;

import java.util.List;

public class ClassBody extends ASTList {
    public ClassBody(List<ASTree> c) { super(c); }
    @Override
    public Object eval(ExecEnvStorage env) {
        for (ASTree t: this)
            t.eval(env);
        return null;
    }
}