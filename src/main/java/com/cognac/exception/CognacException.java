package com.cognac.exception;
import com.cognac.wine.syntax.ASTree;

public class CognacException extends RuntimeException {
    public CognacException(String m) { super(m); }
    public CognacException(String m, ASTree t) {
        super(m + " " + t.location());
    }
}
