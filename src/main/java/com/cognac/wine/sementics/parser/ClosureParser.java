package com.cognac.wine.sementics.parser;
import static com.cognac.wine.sementics.parser.Parser.rule;

import com.cognac.wine.syntax.list.Fun;

public class ClosureParser extends FuncParser {
    public ClosureParser() {
        primary.insertChoice(Parser.rule(Fun.class)
                                 .sep("fun").ast(paramList).ast(block));
    }
}
