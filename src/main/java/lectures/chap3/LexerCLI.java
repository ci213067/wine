package lectures.chap3;

import com.cognac.exception.ParseException;
import com.cognac.wine.sementics.Token;
import com.cognac.wine.sementics.Lexer;

import java.io.*;
import java.util.Scanner;

public class LexerCLI {
    public static void main(String[] args) throws ParseException{
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("와인시크립트를 입력해주세요.");
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
        InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        Lexer l = new Lexer(new InputStreamReader(is));
        for (Token t; (t = l.read()) != Token.EOF; )
            System.out.println("=> " + t.getText());
    }
}
