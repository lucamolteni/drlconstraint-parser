package com.github.javaparser.ast.visitor;

import org.drools.drlcostraintparser.ast.expr.OOPathChunk;
import org.drools.drlcostraintparser.ast.expr.OOPathExpr;
import org.drools.drlcostraintparser.ast.expr.RuleBody;
import org.drools.drlcostraintparser.ast.expr.RuleConsequence;
import org.drools.drlcostraintparser.ast.expr.RuleDeclaration;
import org.drools.drlcostraintparser.ast.expr.RulePattern;
import org.drools.drlcostraintparser.ast.expr.CommaSeparatedMethodCallExpr;
import org.drools.drlcostraintparser.ast.expr.DrlxExpression;
import org.drools.drlcostraintparser.ast.expr.HalfBinaryExpr;
import org.drools.drlcostraintparser.ast.expr.HalfPointFreeExpr;
import org.drools.drlcostraintparser.ast.expr.InlineCastExpr;
import org.drools.drlcostraintparser.ast.expr.NullSafeFieldAccessExpr;
import org.drools.drlcostraintparser.ast.expr.NullSafeMethodCallExpr;
import org.drools.drlcostraintparser.ast.expr.PointFreeExpr;
import org.drools.drlcostraintparser.ast.expr.TemporalLiteralChunkExpr;
import org.drools.drlcostraintparser.ast.expr.TemporalLiteralExpr;
import org.drools.drlcostraintparser.ast.expr.TemporalLiteralInfiniteChunkExpr;
import org.drools.drlcostraintparser.ast.expr.BigDecimalLiteralExpr;
import org.drools.drlcostraintparser.ast.expr.BigIntegerLiteralExpr;


public interface GenericRuleVisitor<R, A> {

    default R visit(RuleDeclaration ruleDeclaration, A arg ) { return null; }

    default R visit(RuleBody ruleBody, A arg ) { return null; }

    default R visit(RulePattern rulePattern, A arg ) { return null; }

    default R visit(DrlxExpression expr, A arg ) { return null; }

    default R visit(OOPathExpr expr, A arg ) { return null; }

    default R visit(OOPathChunk chunk, A arg ) { return null; }

    default R visit(RuleConsequence ruleConsequence, A arg ) { return null; }

    default R visit(InlineCastExpr inlineCastExpr, A arg ) { return null; }

    default R visit(NullSafeFieldAccessExpr nullSafeFieldAccessExpr, A arg ) { return null; }

    default R visit(NullSafeMethodCallExpr nullSafeMethodCallExpr, A arg ) { return null; }

    default R visit(PointFreeExpr pointFreeExpr, A arg ) { return null; }

    default R visit(TemporalLiteralExpr temporalLiteralExpr, A arg ) { return null; }

    default R visit(TemporalLiteralChunkExpr temporalLiteralChunkExpr, A arg) { return null; }

    default R visit(HalfBinaryExpr n, A arg) { return null; }

    default R visit(HalfPointFreeExpr n, A arg) { return null; }

    default R visit(BigDecimalLiteralExpr bigDecimalLiteralExpr, A arg) { return null; }

    default R visit(BigIntegerLiteralExpr bigIntegerLiteralExpr, A arg) { return null; }

    default R visit(TemporalLiteralInfiniteChunkExpr temporalLiteralInfiniteChunkExpr, A arg) { return null; }

    default R visit(CommaSeparatedMethodCallExpr commaSeparatedMethodCallExpr, A arg) { throw new UnsupportedOperationException(); }
}
