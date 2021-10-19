package com.cognac.wine.syntax.list.stmnt;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;

import java.util.List;

public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> c) { super(c); }
    public ASTree condition() { return child(0); }
    public ASTree body() { return child(1); }
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }
    @Override
    public Object eval(ExecEnvStorage env) {
        Object result = 0;
        for (;;) {
            Object c = condition().eval(env);
            if (c instanceof Integer && ((Integer)c).intValue() == FALSE)
                return result;
            else
                result = body().eval(env);
        }
    }
}
