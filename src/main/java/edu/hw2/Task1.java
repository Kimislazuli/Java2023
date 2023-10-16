package edu.hw2;

import static java.lang.Math.abs;

public final class Task1 {
    private Task1() {
    }

    public sealed interface Expr {
        double evaluate();

        record Constant(double number) implements Expr {
            @Override
            public double evaluate() {
                return number;
            }
        }

        record Negate(Expr number) implements Expr {
            @Override
            public double evaluate() {
                return -number.evaluate();
            }
        }

        record Exponent(Expr base, double power) implements Expr {
            private static final double ACCURACY = 1e-15;

            @Override
            public double evaluate() { // TODO: validation for negative power input
                double evaluatedBase = base.evaluate();
                if (!checkIfPowerIsInteger(power) && evaluatedBase < 0) {
                    throw new IllegalArgumentException(
                        "You can't reach real result for negative base and non-integer power"
                    );
                } else if (power == 0 && evaluatedBase == 0) {
                    throw new IllegalArgumentException(
                        "You can't reach real result for zero base and zero power"
                    );
                }
                return Math.pow(evaluatedBase, power);
            }

            private boolean checkIfPowerIsInteger(double power) {
                return abs(power - Math.floor(power)) < ACCURACY && !Double.isInfinite(power);
            }
        }

        record Addition(Expr first, Expr second) implements Expr {
            @Override
            public double evaluate() {
                return first.evaluate() + second.evaluate();
            }
        }

        record Multiplication(Expr first, Expr second) implements Expr {
            @Override
            public double evaluate() {
                return first.evaluate() * second.evaluate();
            }
        }
    }
}
