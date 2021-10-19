package lectures.chap7;

import com.cognac.wine.sementics.Lexer;
import com.cognac.wine.sementics.Token;
import com.cognac.wine.sementics.parser.FuncParser;
import com.cognac.wine.syntax.ASTree;
import com.cognac.wine.syntax.list.stmnt.NullStmnt;
import com.cognac.env.ExecEnvStorage;
import com.cognac.exception.ParseException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FuncInterpreterCLI {
    public static void main(String[] args) throws ParseException{
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("와인스크립트를 입력해주세요.");
        System.out.println("입력이 끝난 후 \\a를 입력하시고 엔터를 누루면 어희분석을 시작합니다.");
        while(true){
            String input = sc.nextLine();
            if("\\a".equals(input)){
                System.out.println("어희분석을 실행합니다.");
                break;
            }else{
                sb.append(String.format("%s%s", input, System.lineSeparator()));
            }
        }
        String input = sb.toString();
        InputStream is = new ByteArrayInputStream(input.getBytes());
        Lexer l = new Lexer(new InputStreamReader(is));
        FuncParser bp = new FuncParser();
        ExecEnvStorage env = new NestedExecEnvStorage();
        while (l.peek(0) != Token.EOF) {
            ASTree t = bp.parse(l);
            if (!(t instanceof NullStmnt)) {
                Object r = t.eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}
