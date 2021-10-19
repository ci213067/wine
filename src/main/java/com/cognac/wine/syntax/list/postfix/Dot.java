package com.cognac.wine.syntax.list.postfix;
import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.CognacException;
import com.cognac.wine.syntax.ASTLeaf;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.syntax.list.postfix.Postfix;
import lectures.chap7.NestedExecEnvStorage;
import lectures.chap9.ClassInfo;
import lectures.chap9.WineObject;

import java.util.List;

public class Dot extends Postfix {
    public Dot(List<ASTree> c) { super(c); }
    public String name() { return ((ASTLeaf)child(0)).token().getText(); }
    public String toString() { return "." + name(); }
    protected void initObject(ClassInfo ci, ExecEnvStorage env) {
        if (ci.superClass() != null)
            initObject(ci.superClass(), env);
        ci.body().eval(env);
    }
    public Object eval(ExecEnvStorage env, Object value) {
        String member = name();
        if (value instanceof ClassInfo) {
            if ("new".equals(member)) {
                ClassInfo ci = (ClassInfo)value;
                NestedExecEnvStorage e = new NestedExecEnvStorage(ci.environment());
                WineObject so = new WineObject(e);
                e.putNew("this", so);
                initObject(ci, e);
                return so;
            }
        }
        else if (value instanceof WineObject) {
            try {
                return ((WineObject)value).read(member);
            } catch (WineObject.AccessException e) {}
        }
        throw new CognacException("bad member access: " + member, this);
    }

}
