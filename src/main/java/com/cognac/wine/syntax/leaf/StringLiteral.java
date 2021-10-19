package com.cognac.wine.syntax.leaf;
import com.cognac.env.ExecEnvStorage;
import com.cognac.wine.sementics.Token;
import com.cognac.wine.syntax.ASTLeaf;

public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t) { super(t); }
    public String value() { return token().getText(); }
    @Override
    public Object eval(ExecEnvStorage e) { return value(); }
}
