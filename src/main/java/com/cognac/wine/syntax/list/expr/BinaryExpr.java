package com.cognac.wine.syntax.list.expr;
import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.CognacException;
import com.cognac.wine.syntax.ASTLeaf;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.syntax.leaf.Name;
import com.cognac.wine.syntax.list.postfix.ArrayRef;
import com.cognac.wine.syntax.list.postfix.Dot;
import lectures.chap9.WineObject;

import java.util.List;

public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> c) { super(c); }
    public ASTree left() { return child(0); }
    public String operator() {
        return ((ASTLeaf)child(1)).token().getText();
    }
    public ASTree right() { return child(2); }
    @Override
    public Object eval(ExecEnvStorage env) {
        String op = operator();
        if ("=".equals(op)) {
            Object right = right().eval(env);
            return computeAssign(env, right);
        }
        else {
            Object left = left().eval(env);
            Object right = right().eval(env);
            return computeOp(left, op, right);
        }
    }
    protected Object setField(WineObject obj, Dot expr, Object rvalue) {
        String name = expr.name();
        try {
            obj.write(name, rvalue);
            return rvalue;
        } catch (WineObject.AccessException e) {
            throw new CognacException("bad member access " + location()
                    + ": " + name);
        }
    }
    protected Object computeAssign(ExecEnvStorage env, Object rvalue) {
        ASTree l = left();
        if (l instanceof Name) {
            env.put(((Name)l).name(), rvalue);
            return rvalue;
        }else if(l instanceof PrimaryExpr){
            PrimaryExpr p = (PrimaryExpr)l;
            if (p.hasPostfix(0) && p.postfix(0) instanceof Dot) {
                Object t = p.evalSubExpr(env, 1);
                if (t instanceof WineObject)
                    return setField((WineObject)t, (Dot)p.postfix(0),
                            rvalue);
            }else if(p.hasPostfix(0) && p.postfix(0) instanceof ArrayRef){
                Object a = p.evalSubExpr(env, 1);
                if (a instanceof Object[]) {
                    ArrayRef arrayRef = (ArrayRef)p.postfix(0);
                    Object index = arrayRef.index().eval(env);
                    if (index instanceof Integer) {
                        ((Object[])a)[(Integer)index] = rvalue;
                        return rvalue;
                    }
                }
            }
            throw new CognacException("bad assignment", this);
        }else
            throw new CognacException("bad assignment", this);
    }
    protected Object computeOp(Object left, String op, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            return computeNumber((Integer)left, op, (Integer)right);
        }
        else
        if (op.equals("+"))
            return String.valueOf(left) + String.valueOf(right);
        else if (op.equals("==")) {
            if (left == null)
                return right == null ? TRUE : FALSE;
            else
                return left.equals(right) ? TRUE : FALSE;
        }
        else
            throw new CognacException("bad type", this);
    }
    protected Object computeNumber(Integer left, String op, Integer right) {
        int a = left.intValue();
        int b = right.intValue();
        if (op.equals("+"))
            return a + b;
        else if (op.equals("-"))
            return a - b;
        else if (op.equals("*"))
            return a * b;
        else if (op.equals("/"))
            return a / b;
        else if (op.equals("%"))
            return a % b;
        else if (op.equals("=="))
            return a == b ? TRUE : FALSE;
        else if (op.equals(">"))
            return a > b ? TRUE : FALSE;
        else if (op.equals("<"))
            return a < b ? TRUE : FALSE;
        else
            throw new CognacException("bad operator", this);
    }
}
