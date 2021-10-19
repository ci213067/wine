package com.cognac.wine.syntax.list.stmnt;
import com.cognac.env.ExecEnvExtStorage;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.syntax.ASTLeaf;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.syntax.list.ParameterList;
import lectures.chap7.Function;

import java.util.List;

public class DefStmnt extends ASTList {
    public DefStmnt(List<ASTree> c) { super(c); }
    public String name() { return ((ASTLeaf)child(0)).token().getText(); }
    public ParameterList parameters() { return (ParameterList)child(1); }
    public BlockStmnt body() { return (BlockStmnt)child(2); } 
    public String toString() {
        return "(def " + name() + " " + parameters() + " " + body() + ")";
    }
    @Override
    public Object eval(ExecEnvStorage env) {
        ((ExecEnvExtStorage)env).putNew(name(), new Function(parameters(), body(), env));
        return name();
    }
}
