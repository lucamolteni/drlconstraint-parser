package org.drools.constraint.parser;

import java.util.List;

import com.github.javaparser.Range;
import com.github.javaparser.Token;
import com.github.javaparser.TokenTypes;

public class JavaToken extends com.github.javaparser.JavaToken {

    private int kind;
    private String text;


    public JavaToken(int kind, String text) {
        super(kind, text);
        this.kind = kind;
        this.text = text;
    }

    public JavaToken(Token token, List<com.github.javaparser.JavaToken> tokens) {
        super(token.kind, token.image);

        Range range = Range.range(token.beginLine, token.beginColumn, token.endLine, token.endColumn);
        String text = token.image;
        setRange(range);
        this.kind = token.kind;
        this.text = text;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        this.text = text;
    }

    @Override
    public int getKind() {
        return kind;
    }

    public com.github.javaparser.JavaToken.Category getCategory() {
        return TokenTypes.getCategory(kind);
    }

    @Override
    public int hashCode() {
        int result = kind;
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        org.drools.constraint.parser.JavaToken javaToken = (org.drools.constraint.parser.JavaToken) o;
        if (kind != javaToken.kind)
            return false;
        if (!text.equals(javaToken.text))
            return false;
        return true;
    }

}
