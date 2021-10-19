package com.cognac.wine.sementics.parser;
import static com.cognac.wine.sementics.parser.Parser.rule;

import com.cognac.wine.syntax.list.ArrayLiteral;
import com.cognac.wine.syntax.list.postfix.ArrayRef;

public class ArrayParser extends FuncParser {
    Parser elements = rule(ArrayLiteral.class)
                          .ast(expr).repeat(rule().sep(",").ast(expr));
    public ArrayParser() {
        reserved.add("]");
        primary.insertChoice(rule().sep("[").maybe(elements).sep("]"));
        postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));
    }
}
