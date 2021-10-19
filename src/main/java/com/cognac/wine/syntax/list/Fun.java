package com.cognac.wine.syntax.list;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.syntax.list.stmnt.BlockStmnt;
import lectures.chap7.Function;

import java.util.List;

public class Fun extends ASTList {
    public Fun(List<ASTree> c) { super(c); }
    public ParameterList parameters() { return (ParameterList)child(0); }
    public BlockStmnt body() { return (BlockStmnt)child(1); }
    public String toString() {
        return "(fun " + parameters() + " " + body() + ")";
    }
    public Object eval(ExecEnvStorage env) {
        return new Function(parameters(), body(), env);
    }
}
