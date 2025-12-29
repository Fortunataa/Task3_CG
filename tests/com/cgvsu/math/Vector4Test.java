package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector4Test {

    @Test
    void testAdd() {
        Vector4f v1 = new Vector4f(1, 2, 3, 4);
        Vector4f v2 = new Vector4f(5, 6, 7, 8);
        Vector4f result = v1.add(v2);

        assertEquals(6, result.getX(), 0.0001);
        assertEquals(8, result.getY(), 0.0001);
        assertEquals(10, result.getZ(), 0.0001);
        assertEquals(12, result.getW(), 0.0001);
    }

    @Test
    void testSubtract() {
        Vector4f v1 = new Vector4f(10, 20, 30, 40);
        Vector4f v2 = new Vector4f(1, 2, 3, 4);
        Vector4f result = v1.subtract(v2);

        assertEquals(9, result.getX(), 0.0001);
        assertEquals(18, result.getY(), 0.0001);
        assertEquals(27, result.getZ(), 0.0001);
        assertEquals(36, result.getW(), 0.0001);
    }

    @Test
    void testMultiply() {
        Vector4f v = new Vector4f(2, 3, 4, 5);
        Vector4f result = v.multiply(2);

        assertEquals(4, result.getX(), 0.0001);
        assertEquals(6, result.getY(), 0.0001);
        assertEquals(8, result.getZ(), 0.0001);
        assertEquals(10, result.getW(), 0.0001);
    }

    @Test
    void testDivide() {
        Vector4f v = new Vector4f(8, 12, 16, 20);
        Vector4f result = v.divide(4);

        assertEquals(2, result.getX(), 0.0001);
        assertEquals(3, result.getY(), 0.0001);
        assertEquals(4, result.getZ(), 0.0001);
        assertEquals(5, result.getW(), 0.0001);
    }

    @Test
    void testDivideByZero() {
        Vector4f v = new Vector4f(1, 1, 1, 1);


        assertThrows(IllegalArgumentException.class, () -> {
            v.divide(0);
        });


        assertThrows(IllegalArgumentException.class, () -> {
            v.divide(1e-11F);
        });


        assertDoesNotThrow(() -> {
            v.divide(1e-9F);
        });
    }

    @Test
    void testLength() {
        Vector4f v = new Vector4f(1, 2, 2, 4);
        // 1² + 2² + 2² + 4² = 1 + 4 + 4 + 16 = 25, √25 = 5
        assertEquals(5, v.length(), 0.0001);
    }

    @Test
    void testLengthZero() {
        Vector4f v = new Vector4f(0, 0, 0, 0);
        assertEquals(0, v.length(), 0.0001);
    }

    @Test
    void testNormalize() {
        Vector4f v = new Vector4f(2, 0, 0, 0);
        Vector4f normalized = v.normalize();

        assertEquals(1, normalized.length(), 0.0001);
        assertEquals(1, normalized.getX(), 0.0001);
        assertEquals(0, normalized.getY(), 0.0001);
        assertEquals(0, normalized.getZ(), 0.0001);
        assertEquals(0, normalized.getW(), 0.0001);
    }

    @Test
    void testNormalizeComplex() {
        Vector4f v = new Vector4f(1, 1, 1, 1);
        Vector4f normalized = v.normalize();

        assertEquals(1, normalized.length(), 0.0001);
        assertEquals(0.5, normalized.getX(), 0.0001);
        assertEquals(0.5, normalized.getY(), 0.0001);
        assertEquals(0.5, normalized.getZ(), 0.0001);
        assertEquals(0.5, normalized.getW(), 0.0001);
    }

    @Test
    void testNormalizeZeroVector() {
        Vector4f v = new Vector4f(0, 0, 0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            v.normalize();
        });


        Vector4f tiny = new Vector4f(1e-11F, 1e-11F, 1e-11F, 1e-11F);
        assertThrows(IllegalArgumentException.class, () -> {
            tiny.normalize();
        });
    }

    @Test
    void testNormalizeSafe() {
        Vector4f v1 = new Vector4f(2, 0, 0, 0);
        Vector4f normalized1 = v1.normalizeSafe();
        assertEquals(1, normalized1.length(), 0.0001);
        assertEquals(1, normalized1.getX(), 0.0001);
        assertEquals(0, normalized1.getY(), 0.0001);
        assertEquals(0, normalized1.getZ(), 0.0001);
        assertEquals(0, normalized1.getW(), 0.0001);


        Vector4f v2 = new Vector4f(0, 0, 0, 0);
        Vector4f normalized2 = v2.normalizeSafe();
        assertEquals(0, normalized2.length(), 0.0001);
        assertEquals(0, normalized2.getX(), 0.0001);
        assertEquals(0, normalized2.getY(), 0.0001);
        assertEquals(0, normalized2.getZ(), 0.0001);
        assertEquals(0, normalized2.getW(), 0.0001);


        Vector4f v3 = new Vector4f(1e-11F, 1e-11F, 1e-11F, 1e-11F);
        Vector4f normalized3 = v3.normalizeSafe();
        assertEquals(1e-11, normalized3.getX(), 1e-20);
        assertEquals(1e-11, normalized3.getY(), 1e-20);
        assertEquals(1e-11, normalized3.getZ(), 1e-20);
        assertEquals(1e-11, normalized3.getW(), 1e-20);
    }

    @Test
    void testDotProduct() {
        Vector4f v1 = new Vector4f(1, 2, 3, 4);
        Vector4f v2 = new Vector4f(5, 6, 7, 8);
        double dot = v1.dot(v2);

        assertEquals(70, dot, 0.0001);
    }

    @Test
    void testDotProductWithZero() {
        Vector4f v1 = new Vector4f(1, 2, 3, 4);
        Vector4f v2 = new Vector4f(0, 0, 0, 0);
        double dot = v1.dot(v2);

        assertEquals(0, dot, 0.0001);
    }

    @Test
    void testDotProductOrthogonal() {
        Vector4f v1 = new Vector4f(1, 0, 0, 0);
        Vector4f v2 = new Vector4f(0, 1, 0, 0);

        double dot = v1.dot(v2);
        assertEquals(0, dot, 0.0001);
    }

    @Test
    void testDotProductSameVector() {
        Vector4f v = new Vector4f(1, 2, 3, 4);
        double dot = v.dot(v);
        double lengthSquared = v.length() * v.length();

        assertEquals(lengthSquared, dot, 0.0001);
    }

    @Test
    void testSetters() {
        Vector4f v = new Vector4f(1, 2, 3, 4);

        v.setX(10);
        v.setY(20);
        v.setZ(30);
        v.setW(40);

        assertEquals(10, v.getX(), 0.0001);
        assertEquals(20, v.getY(), 0.0001);
        assertEquals(30, v.getZ(), 0.0001);
        assertEquals(40, v.getW(), 0.0001);

        assertEquals(Math.sqrt(100 + 400 + 900 + 1600), v.length(), 0.0001);

        Vector4f other = new Vector4f(1, 1, 1, 1);
        Vector4f sum = v.add(other);
        assertEquals(11, sum.getX(), 0.0001);
        assertEquals(21, sum.getY(), 0.0001);
        assertEquals(31, sum.getZ(), 0.0001);
        assertEquals(41, sum.getW(), 0.0001);
    }
    @Test
    void testToVector3() {
        Vector4f v1 = new Vector4f(2, 4, 6, 1);
        Vector3f result1 = v1.toVector3();
        assertEquals(2, result1.getX(), 1e-10);
        assertEquals(4, result1.getY(), 1e-10);
        assertEquals(6, result1.getZ(), 1e-10);

        Vector4f v2 = new Vector4f(2, 4, 6, 2);
        Vector3f result2 = v2.toVector3();
        assertEquals(1, result2.getX(), 1e-10);
        assertEquals(2, result2.getY(), 1e-10);
        assertEquals(3, result2.getZ(), 1e-10);

        Vector4f v3 = new Vector4f(2, 4, 6, 0.5F);
        Vector3f result3 = v3.toVector3();
        assertEquals(4, result3.getX(), 1e-10);
        assertEquals(8, result3.getY(), 1e-10);
        assertEquals(12, result3.getZ(), 1e-10);

        Vector4f v4 = new Vector4f(2, 4, 6, -2);
        Vector3f result4 = v4.toVector3();
        assertEquals(-1, result4.getX(), 1e-10);
        assertEquals(-2, result4.getY(), 1e-10);
        assertEquals(-3, result4.getZ(), 1e-10);
    }

    @Test
    void testToVector3WithZeroW() {
        Vector4f v = new Vector4f(2, 4, 6, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            v.toVector3();
        });

        Vector4f tiny = new Vector4f(2, 4, 6, 1e-11F);
        assertThrows(IllegalArgumentException.class, () -> {
            tiny.toVector3();
        });

        Vector4f small = new Vector4f(2, 4, 6, 1e-9F);
        assertDoesNotThrow(() -> {
            small.toVector3();
        });
    }

    @Test
    void testToVector3Safe() {
        Vector4f v1 = new Vector4f(2, 4, 6, 2);
        Vector3f result1 = v1.toVector3Safe();
        assertEquals(1, result1.getX(), 1e-10);
        assertEquals(2, result1.getY(), 1e-10);
        assertEquals(3, result1.getZ(), 1e-10);

        Vector4f v2 = new Vector4f(2, 4, 6, 0);
        Vector3f result2 = v2.toVector3Safe();
        assertEquals(2, result2.getX(), 1e-10);
        assertEquals(4, result2.getY(), 1e-10);
        assertEquals(6, result2.getZ(), 1e-10);

        Vector4f v3 = new Vector4f(2, 4, 6, 1e-11F);
        Vector3f result3 = v3.toVector3Safe();
        assertEquals(2, result3.getX(), 1e-10);
        assertEquals(4, result3.getY(), 1e-10);
        assertEquals(6, result3.getZ(), 1e-10);
    }

    @Test
    void testToVector3PreservesDirection() {
        Vector4f v1 = new Vector4f(1, 2, 3, 2);
        Vector4f v2 = new Vector4f(2, 4, 6, 4);

        Vector3f result1 = v1.toVector3();
        Vector3f result2 = v2.toVector3();

        assertEquals(result1.getX(), result2.getX(), 1e-10);
        assertEquals(result1.getY(), result2.getY(), 1e-10);
        assertEquals(result1.getZ(), result2.getZ(), 1e-10);
    }

    @Test
    void testChainedOperationsWithToVector3() {
        Vector4f v = new Vector4f(10, 20, 30, 2);

        Vector3f result = v.toVector3().multiply(2);

        assertEquals(10, result.getX(), 1e-10);
        assertEquals(20, result.getY(), 1e-10);
        assertEquals(30, result.getZ(), 1e-10);
    }


}