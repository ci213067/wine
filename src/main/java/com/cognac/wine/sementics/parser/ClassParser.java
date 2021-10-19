package com.cognac.wine.sementics.parser;
import static com.cognac.wine.sementics.parser.Parser.rule;

import com.cognac.wine.sementics.Token;
import com.cognac.wine.syntax.list.ClassBody;
import com.cognac.wine.syntax.list.stmnt.ClassStmnt;
import com.cognac.wine.syntax.list.postfix.Dot;

public class ClassParser extends ClosureParser {
    Parser member = Parser.rule().or(def, simple);
    Parser class_body = Parser.rule(ClassBody.class).sep("{").option(member)
                            .repeat(Parser.rule().sep(";", Token.EOL).option(member))
                            .sep("}");
    Parser defclass = Parser.rule(ClassStmnt.class).sep("class").identifier(reserved)
                          .option(Parser.rule().sep("extends").identifier(reserved))
                          .ast(class_body);
    public ClassParser() {
        postfix.insertChoice(Parser.rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(defclass);
    }
}
