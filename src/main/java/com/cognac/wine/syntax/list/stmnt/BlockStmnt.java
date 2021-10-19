package com.cognac.wine.syntax.list.stmnt;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;

import java.util.List;

public class BlockStmnt extends ASTList {
    public BlockStmnt(List<ASTree> c) { super(c); }
    @Override
    public Object eval(ExecEnvStorage env) {
        Object result = 0;
        for (ASTree t: this) {
            if (!(t instanceof NullStmnt))
                result = t.eval(env);
        }
        return result;
    }
}
