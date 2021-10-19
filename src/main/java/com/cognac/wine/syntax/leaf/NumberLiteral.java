package com.cognac.wine.syntax.leaf;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.sementics.Token;
import com.cognac.wine.syntax.ASTLeaf;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) { super(t); }
    public int value() { return token().getNumber(); }
    @Override
    public Object eval(ExecEnvStorage e) { return value(); }
}
