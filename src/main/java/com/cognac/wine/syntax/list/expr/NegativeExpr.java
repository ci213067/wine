package com.cognac.wine.syntax.list.expr;
import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.CognacException;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;

import java.util.List;

public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> c) { super(c); }
    public ASTree operand() { return child(0); }
    public String toString() {
        return "-" + operand();
    }
    @Override
    public Object eval(ExecEnvStorage env) {
        Object v = operand().eval(env);
        if (v instanceof Integer)
            return new Integer(-((Integer)v).intValue());
        else
            throw new CognacException("bad type for -", this);
    }
}
