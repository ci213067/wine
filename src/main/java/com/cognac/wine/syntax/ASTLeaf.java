package com.cognac.wine.syntax;
import java.util.Iterator;
import java.util.ArrayList;

import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.CognacException;
import com.cognac.wine.sementics.Token;

public class ASTLeaf extends ASTree {
    private static ArrayList<ASTree> empty = new ArrayList<ASTree>(); 
    protected Token token;
    public ASTLeaf(Token t) { token = t; }
    public ASTree child(int i) { throw new IndexOutOfBoundsException(); }
    public int numChildren() { return 0; }
    public Iterator<ASTree> children() { return empty.iterator(); }
    public String toString() { return token.getText(); }
    public String location() { return "at line " + token.getLineNumber(); }
    public Token token() { return token; }
    @Override
    public Object eval(ExecEnvStorage env) {
        throw new CognacException("cannot eval: " + toString(), this);
    }
}
