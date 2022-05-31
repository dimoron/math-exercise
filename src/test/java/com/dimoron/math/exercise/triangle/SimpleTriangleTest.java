package com.dimoron.math.exercise.triangle;

import com.dimoron.math.exercise.triangle.SimpleTriangle.TargetVar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleTriangleTest {

  @Test
  void basicTest() {
    SimpleTriangle simpleTriangle = new SimpleTriangle();
    Assertions.assertNotEquals(0, simpleTriangle.getA());
    Assertions.assertNotEquals(0, simpleTriangle.getB());
    Assertions.assertNotEquals(0, simpleTriangle.getC());
    Assertions.assertNotNull(simpleTriangle.getTargetVar());

  }

  @Test
  void basicSolveTest() {
    SimpleTriangle simpleTriangle = new SimpleTriangle(3, 4, 5, TargetVar.A);
    Assertions.assertFalse(simpleTriangle.solve("5"));
    Assertions.assertTrue(simpleTriangle.solve("3 "));
  }

  @Test
  void basicSetupFail() {
    SimpleTriangleGenerationException thrown = Assertions.assertThrows(
        SimpleTriangleGenerationException.class,
        () -> new SimpleTriangle(2, 3, 4, TargetVar.A)
    );

    Assertions.assertEquals("Input values invalid", thrown.getMessage());
  }
}
