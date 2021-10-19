package com.cognac.wine.sementics.parser;

import com.cognac.wine.syntax.list.postfix.Arguments;
import com.cognac.wine.syntax.list.stmnt.DefStmnt;
import com.cognac.wine.syntax.list.ParameterList;

public class FuncParser extends BasicParser {
    Parser param = Parser.rule().identifier(reserved);
    Parser params = Parser.rule(ParameterList.class)
                        .ast(param).repeat(Parser.rule().sep(",").ast(param));
    Parser paramList = Parser.rule().sep("(").maybe(params).sep(")");
    Parser def = Parser.rule(DefStmnt.class)
                     .sep("def").identifier(reserved).ast(paramList).ast(block);
    Parser args = Parser.rule(Arguments.class)
                      .ast(expr).repeat(Parser.rule().sep(",").ast(expr));
    Parser postfix = Parser.rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }
}
