package com.cognac.wine.syntax.leaf;
import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.CognacException;
import com.cognac.wine.sementics.Token;
import com.cognac.wine.syntax.ASTLeaf;

public class Name extends ASTLeaf {
    public Name(Token t) { super(t); }
    public String name() { return token().getText(); }
    @Override
    public Object eval(ExecEnvStorage env) {
        Object value = env.get(name());
        if (value == null)
            throw new CognacException("undefined name: " + name(), this);
        else
            return value;
    }
}
