package com.cgvsu.math;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AffineTransformationsTest {
    private static final float EPS= 0.0001f;
    private AffineTransformations transform;

    @BeforeEach
    void setUp() {
        transform = new AffineTransformations();
    }

    @Test
    void testTranslation() {
        transform.translation(2, 3, 4);
        Vector3f vector3f = new Vector3f(1, 1, 1);
        Vector3f resultVector = transform.applyToVector(vector3f);

        assertEquals(3, resultVector.x, EPS);
        assertEquals(4, resultVector.y, EPS);
        assertEquals(5, resultVector.z, EPS);
    }

    @Test
    void testTranslationByX() {
        transform.translationByX(5);
        Vector3f vector3f = new Vector3f(1, 2, 3);
        Vector3f resultVector = transform.applyToVector(vector3f);

        assertEquals(6, resultVector.x, EPS);
        assertEquals(2, resultVector.y, EPS);
        assertEquals(3, resultVector.z, EPS);
    }

    @Test
    void testTranslationByY() {
        transform.translationByY(-10);
        Vector3f vector3f = new Vector3f(10, 5, 7);
        Vector3f resultVector = transform.applyToVector(vector3f);

        assertEquals(10, resultVector.x, EPS);
        assertEquals(-5, resultVector.y, EPS);
        assertEquals(7, resultVector.z, EPS);
    }

    @Test
    void testTranslationByZ() {
        transform.translationByZ(1.578f);
        Vector3f vector3f = new Vector3f(10, 0, 9);
        Vector3f resultVector = transform.applyToVector(vector3f);

        assertEquals(10, resultVector.x, EPS);
        assertEquals(0, resultVector.y, EPS);
        assertEquals(10.578, resultVector.z, EPS);
    }

    @Test
    void testTranslationWithVector() {
        Vector3f vector3f = new Vector3f(1, 2, 3);
        transform.translation(vector3f);
        Vector3f original = new Vector3f(4, 5, 6);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(5, resultVector.x, EPS);
        assertEquals(7, resultVector.y, EPS);
        assertEquals(9, resultVector.z, EPS);
    }

    @Test
    void testScaling() {
        transform.scaling(2, 3, 4);
        Vector3f original = new Vector3f(1, 2, 3);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(2, resultVector.x, EPS);
        assertEquals(6, resultVector.y, EPS);
        assertEquals(12, resultVector.z, EPS);
    }

    @Test
    void testScalingByX() {
        transform.scalingByX(2);
        Vector3f original = new Vector3f(3, 4, 5);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(6, resultVector.x, EPS);
        assertEquals(4, resultVector.y, EPS);
        assertEquals(5, resultVector.z, EPS);
    }

    @Test
    void testScaleByRatio() {
        transform.scaleByRatio(2.5f);
        Vector3f original = new Vector3f(2, 3, 4);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(5, resultVector.x, EPS);
        assertEquals(7.5, resultVector.y, EPS);
        assertEquals(10, resultVector.z, EPS);
    }

    @Test
    void testRotationX() {
        transform.rotationX(45);
        Vector3f original = new Vector3f(0, 1, 0);
        float cos45 = (float) Math.cos(Math.toRadians(45));
        float sin45 = (float) Math.sin(Math.toRadians(45));
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(0, resultVector.x, EPS);
        assertEquals(cos45, resultVector.y, EPS);
        assertEquals(sin45, resultVector.z, EPS);
    }

    @Test
    void testRotationY() {
        transform.rotationY(30);
        Vector3f original = new Vector3f(0, 0, 1);
        float cos30 = (float) Math.cos(Math.toRadians(30));
        float sin30 = (float) Math.sin(Math.toRadians(30));
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(sin30, resultVector.x, EPS);
        assertEquals(0, resultVector.y, EPS);
        assertEquals(cos30, resultVector.z, EPS);
    }

    @Test
    void testRotationZ() {
        transform.rotationZ(60);
        Vector3f original = new Vector3f(1, 0, 0);
        float cos60 = (float) Math.cos(Math.toRadians(60));
        float sin60 = (float) Math.sin(Math.toRadians(60));
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(cos60, resultVector.x, EPS);
        assertEquals(sin60, resultVector.y, EPS);
        assertEquals(0, resultVector.z, EPS);
    }

    @Test
    void testChainOfOperations() {
        transform.scaleByRatio(2).translation(1, 2, 3);
        Vector3f original = new Vector3f(1, 1, 1);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(4, resultVector.x, EPS);
        assertEquals(6, resultVector.y, EPS);
        assertEquals(8, resultVector.z, EPS);
    }

    @Test
    void testApplyMethod() {
        AffineTransformations translation = new AffineTransformations().translation(2, 0, 0);
        AffineTransformations rotation = new AffineTransformations().rotationZ(90);
        translation.apply(rotation);
        Vector3f original = new Vector3f(1, 0, 0);
        Vector3f resultVector = translation.applyToVector(original);

        assertEquals(2, resultVector.x, EPS);
        assertEquals(1, resultVector.y, EPS);
        assertEquals(0, resultVector.z, EPS);
    }

    @Test
    void testBuild() {
        AffineTransformations original = new AffineTransformations().translation(1, 2, 3);
        AffineTransformations copy = original.build();
        original.scaleByRatio(2);
        Vector3f testVector = new Vector3f(0, 0, 0);
        Vector3f resultVector = copy.applyToVector(testVector);

        assertEquals(1, resultVector.x, EPS);
        assertEquals(2, resultVector.y, EPS);
        assertEquals(3, resultVector.z, EPS);
    }

    @Test
    void testReset() {
        transform.translation(5, 5, 5).scaleByRatio(2);
        transform.reset();
        Vector3f original = new Vector3f(1, 2, 3);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(1, resultVector.x, EPS);
        assertEquals(2, resultVector.y, EPS);
        assertEquals(3, resultVector.z, EPS);
    }

    @Test
    void testScaleWithAxis() {
        transform.scale(AffineTransformations.Axis.X, 3).scale(AffineTransformations.Axis.Y, 2);
        Vector3f original = new Vector3f(1, 1, 1);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(3, resultVector.x, EPS);
        assertEquals(2, resultVector.y, EPS);
        assertEquals(1, resultVector.z, EPS);
    }

    @Test
    void testTranslateWithAxis() {
        transform.translation(AffineTransformations.Axis.X, 5).translation(AffineTransformations.Axis.Z, -2);
        Vector3f original = new Vector3f(0, 0, 0);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(5, resultVector.x, EPS);
        assertEquals(0, resultVector.y, EPS);
        assertEquals(-2, resultVector.z, EPS);
    }

    @Test
    void testRotateWithAxis() {
        transform.rotate(AffineTransformations.Axis.Y, 90);
        Vector3f original = new Vector3f(1, 0, 0);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(0, resultVector.x, EPS);
        assertEquals(0, resultVector.y, EPS);
        assertEquals(-1, resultVector.z, EPS);
    }

    @Test
    void testScaleByVector() {
        Vector3f scaleVector = new Vector3f(2, 0.5f, 3);
        transform.scaleByVector(scaleVector);
        Vector3f original = new Vector3f(4, 6, 2);
        Vector3f resultVector = transform.applyToVector(original);

        assertEquals(8, resultVector.x, EPS);
        assertEquals(3, resultVector.y, EPS);
        assertEquals(6, resultVector.z, EPS);
    }
}
