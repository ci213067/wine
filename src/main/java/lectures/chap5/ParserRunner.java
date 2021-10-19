package lectures.chap5;

import com.cognac.exception.ParseException;
import com.cognac.wine.sementics.Token;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.sementics.parser.BasicParser;
import com.cognac.wine.sementics.Lexer;
import lectures.chap3.CodeDialog;

public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        BasicParser bp = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = bp.parse(l);
            System.out.println("=> " + ast.toString());
        }
    }
}
