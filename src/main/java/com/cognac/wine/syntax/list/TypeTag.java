package com.cognac.wine.syntax.list;
import com.cognac.wine.syntax.ASTLeaf;
import com.cognac.wine.syntax.ASTList;
import com.cognac.wine.syntax.ASTree;

import java.util.List;

public class TypeTag extends ASTList {
    public static final String UNDEF = "<Undef>";
    public TypeTag(List<ASTree> c) { super(c); }
    public String type() {
        if (numChildren() > 0)
            return ((ASTLeaf)child(0)).token().getText();
        else
            return UNDEF;
    }
    public String toString() { return ":" + type(); }
}
