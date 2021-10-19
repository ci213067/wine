package com.cognac.wine.syntax.list;
import com.cognac.env.ExecEnvExtStorage;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.syntax.ASTLeaf;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;

import java.util.List;

public class ParameterList extends ASTList {
    public ParameterList(List<ASTree> c) { super(c); }
    public String name(int i) { return ((ASTLeaf)child(i)).token().getText(); }
    public int size() { return numChildren(); }
    public void eval(ExecEnvStorage env, int index, Object value) {
        ((ExecEnvExtStorage)env).putNew(name(index), value);
    }
}
