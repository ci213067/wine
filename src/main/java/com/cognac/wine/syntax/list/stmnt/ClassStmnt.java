package com.cognac.wine.syntax.list.stmnt;
import com.cognac.env.ExecEnvExtStorage;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.syntax.ASTLeaf;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.syntax.list.ClassBody;
import lectures.chap9.ClassInfo;

import java.util.List;

public class ClassStmnt extends ASTList {
    public ClassStmnt(List<ASTree> c) { super(c); }
    public String name() { return ((ASTLeaf)child(0)).token().getText(); }
    public String superClass() {
        if (numChildren() < 3)
            return null;
        else
            return ((ASTLeaf)child(1)).token().getText();
    }
    public ClassBody body() { return (ClassBody)child(numChildren() - 1); }
    public String toString() {
        String parent = superClass();
        if (parent == null)
            parent = "*";
        return "(class " + name() + " " + parent + " " + body() + ")";
    }
    @Override
    public Object eval(ExecEnvStorage env) {
        ClassInfo ci = new ClassInfo(this, env);
        ((ExecEnvExtStorage)env).put(name(), ci);
        return name();
    }
}
