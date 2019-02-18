/*
 *  * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.drools.constraint.parser.ast.expr;

import java.math.BigDecimal;
import javax.annotation.Generated;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.LiteralStringValueExpr;
import com.github.javaparser.ast.visitor.CloneVisitor;
import org.drools.constraint.parser.ast.visitor.DrlGenericVisitor;
import org.drools.constraint.parser.ast.visitor.DrlVoidVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.LongLiteralExprMetaModel;

public final class BigDecimalLiteralExpr extends LiteralStringValueExpr {

    public BigDecimalLiteralExpr() {
        this(null, "0");
    }

    @AllFieldsConstructor
    public BigDecimalLiteralExpr(final String value) {
        this(null, value);
    }

    /**
     * This constructor is used by the parser and is considered private.
     */
    @Generated("com.github.javaparser.generator.core.node.MainConstructorGenerator")
    public BigDecimalLiteralExpr(TokenRange tokenRange, String value) {
        super(tokenRange, value);
        customInitialization();
    }

    public BigDecimalLiteralExpr(final BigDecimal value) {
        this(null, value.toString());
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return ((DrlGenericVisitor<R, A>) v).visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        ((DrlVoidVisitor<A>) v).visit(this, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.RemoveMethodGenerator")
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    /**
     * @return the literal value as an long while respecting different number representations
     */
    public BigDecimal asBigDecimal() {
        String result = value.replaceAll("_", "");
        char lastChar = result.charAt(result.length() - 1);
        if (lastChar == 'B') {
            result = result.substring(0, result.length() - 1);
        }
        return new BigDecimal(result);
    }

    public BigDecimalLiteralExpr setLong(long value) {
        this.value = String.valueOf(value);
        return this;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.CloneGenerator")
    public BigDecimalLiteralExpr clone() {
        return (BigDecimalLiteralExpr) accept(new CloneVisitor(), null);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.GetMetaModelGenerator")
    public LongLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.longLiteralExprMetaModel;
    }
}
