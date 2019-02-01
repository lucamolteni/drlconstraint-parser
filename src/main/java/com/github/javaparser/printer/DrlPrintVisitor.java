/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.javaparser.printer;

import com.github.javaparser.ast.drlx.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.AbstractVoidRuleVisitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DrlPrintVisitor extends AbstractVoidRuleVisitor<Void, PrettyPrintVisitor> {

    public DrlPrintVisitor(PrettyPrintVisitor visitor) {
        super( visitor );
    }

    @Override
    public void visit( RuleDeclaration rule, Void arg ) {
        visitor.printComment(rule.getComment(), arg);

        for (AnnotationExpr ae : rule.getAnnotations()) {
            ae.accept(visitor, arg);
            visitor.printer.print(" ");
        }

        visitor.printer.print("rule ");
        rule.getName().accept(visitor, arg);
        visitor.printer.println(" when ");
        rule.getRuleBody().accept(visitor, arg);
        visitor.printer.println("end");
    }

    @Override
    public void visit( RuleBody ruleBody, Void arg ) {
        Map<String, RuleConsequence> consequences = new HashMap<>();
        RuleConsequence lastConsequence = null;
        int itemsNr = ruleBody.getItems().size();

        for ( int i = 0; i < itemsNr; i++ ) {
            RuleItem item = ruleBody.getItems().get( i );
            if (item instanceof RulePattern) {
                RulePattern pattern = ( (RulePattern) item );
                pattern.accept( visitor, arg );
                visitor.printer.println();
            } else if (item instanceof RuleConsequence) {
                RuleConsequence consequence = ( (RuleConsequence) item );
                if (i == itemsNr-1) {
                    lastConsequence = consequence;
                } else {
                    String consequenceName = "exec" + i;
                    consequences.put(consequenceName, consequence);
                    visitor.printer.println("do[" + consequenceName + "] ");
                }
            }
        }

        visitor.printer.println("then ");
        if (lastConsequence != null) {
            lastConsequence.accept( visitor, arg );
        }

        consequences.forEach( (name, consequence) -> {
            visitor.printer.println("then[" + name + "] ");
            consequence.accept( visitor, arg );
        }  );
    }

    @Override
    public void visit( RulePattern rulePattern, Void arg ) {
        rulePattern.getBind().accept(visitor, arg);
        visitor.printer.print(" : ");
        rulePattern.getExpr().accept(visitor, arg);
    }

    @Override
    public void visit( RuleConsequence ruleConsequence, Void arg ) {
        BlockStmt consequence = ruleConsequence.getBlock();
        if (consequence != null) {
            for ( Iterator<Statement> i = consequence.getStatements().iterator(); i.hasNext(); ) {
                Statement statement = i.next();
                statement.accept( visitor, arg );
                visitor.printer.println();
            }
        }
    }

    @Override
    public void visit( OOPathExpr expr, Void arg ) {
        for ( Iterator<OOPathChunk> i = expr.getChunks().iterator(); i.hasNext(); ) {
            OOPathChunk chunk = i.next();
            chunk.accept( visitor, arg );
        }
    }

    @Override
    public void visit( OOPathChunk chunk, Void arg ) {
        visitor.printer.print("/");
        chunk.getField().accept( visitor, arg );

        SimpleName inlineCast = chunk.getInlineCast();
        if (inlineCast != null) {
            visitor.printer.print( "#" );
            inlineCast.accept( visitor, arg );
        }

        List<Expression> condition = chunk.getConditions();
        final Iterator<Expression> iterator = condition.iterator();
        if (!condition.isEmpty()) {
            visitor.printer.print("[");
            Expression first = iterator.next();
            first.accept(visitor, arg);
            while(iterator.hasNext()) {
                visitor.printer.print(",");
                iterator.next().accept(visitor, arg);
            }
            visitor.printer.print("]");
        }
    }
}
