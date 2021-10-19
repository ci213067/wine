package com.cognac.wine.syntax.list.postfix;

import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;

import java.util.List;

public abstract class Postfix extends ASTList {
    public Postfix(List<ASTree> c) { super(c); }
    public abstract Object eval(ExecEnvStorage env, Object value);
}
