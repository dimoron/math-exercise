package com.dimoron.math.exercise.triangle;

import java.util.Random;
import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The aim is to generate question to test the fundamentals of deriving lengths of a side of a right
 * angle triangle using the following equation
 * <p>
 * a^2 + b^2 = c^2
 */
public class SimpleTriangle {

  private static final Logger logger = LoggerFactory.getLogger(SimpleTriangle.class.getName());
  @Getter
  private final int a;
  @Getter
  private final int b;
  @Getter
  private final int c;
  @Getter
  private final TargetVar targetVar;

  /**
   * Generates random exercise.
   * Limit to the range of `a`,`b` and `c` to practice the use of the formula, not do complex pow and sqrt calculation
   */
  @SneakyThrows
  public SimpleTriangle() {
    Random r = new Random();
    int counter = 1;
    final int escapeCounter = 1000;
    final int bLimit = 100;
    int tempA = 0;
    int tempB = 0;
    double tempC = 0;

    while (counter < escapeCounter) {
      tempA = r.nextInt(1, 20);
      tempB = r.nextInt(tempA, 20);

      while (tempB < bLimit && counter < escapeCounter) {
        tempB++;
        tempC = SimpleTriangle.calculateC(tempA, tempB);

        if (SimpleTriangle.isCleanInteger(tempC)) {
          counter = escapeCounter;
        } else {
          counter++;
        }
      }
    }
    if (counter == escapeCounter && !SimpleTriangle.isCleanInteger(tempC)) {
      throw new SimpleTriangleGenerationException("Generation loop exceed limit");
    }

    this.a = tempA;
    this.b = tempB;
    this.c = (int) tempC;
    final int targetVarInt = r.nextInt(3);
    this.targetVar = switch (targetVarInt) {
      case 0 -> TargetVar.A;
      case 1 -> TargetVar.B;
      case 2 -> TargetVar.C;
      default -> throw new IllegalStateException("Unexpected value: " + targetVarInt);
    };
    logger.info("Generated => A {}, B {}, C {}, targetVar {}", this.a, this.b, this.c,
        this.targetVar);
  }

  @SneakyThrows
  public SimpleTriangle(
      final int a,
      final int b,
      final int c,
      final TargetVar targetVar
  ) {
    this.a = a;
    this.b = b;
    this.c = c;

    final double tempC = SimpleTriangle.calculateC(this.a, this.b);

    if (tempC - c != 0) { // or tempC == c; to validate the input values satisfy the base conditions
      throw new SimpleTriangleGenerationException("Input values invalid");
    }

    this.targetVar = targetVar;
  }

  private static double calculateC(final int tempA, final int tempB) {
    return Math.sqrt(Math.pow(tempA, 2) + Math.pow(tempB, 2));
  }

  private static boolean isCleanInteger(double t) {
    return t % 1 == 0;
  }

  public boolean solve(String ans) {
    int ansInt = Integer.parseInt(ans.trim());
    return TargetVar.A.equals(this.targetVar) && this.a == ansInt ||
        TargetVar.B.equals(this.targetVar) && this.b == ansInt ||
        TargetVar.C.equals(this.targetVar) && this.c == ansInt;
  }

  public enum TargetVar {
    A, B, C
  }

}
