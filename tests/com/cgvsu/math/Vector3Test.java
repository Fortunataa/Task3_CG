package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    @Test
    void testAdd() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        Vector3f result = v1.add(v2);

        assertEquals(5, result.getX(), 0.0001);
        assertEquals(7, result.getY(), 0.0001);
        assertEquals(9, result.getZ(), 0.0001);
    }

    @Test
    void testSubtract() {
        Vector3f v1 = new Vector3f(10, 20, 30);
        Vector3f v2 = new Vector3f(1, 2, 3);
        Vector3f result = v1.subtract(v2);

        assertEquals(9, result.getX(), 0.0001);
        assertEquals(18, result.getY(), 0.0001);
        assertEquals(27, result.getZ(), 0.0001);
    }

    @Test
    void testMultiply() {
        Vector3f v = new Vector3f(2, 3, 4);
        Vector3f result = v.multiply(2);

        assertEquals(4, result.getX(), 0.0001);
        assertEquals(6, result.getY(), 0.0001);
        assertEquals(8, result.getZ(), 0.0001);
    }

    @Test
    void testDivide() {
        Vector3f v = new Vector3f(6, 9, 12);
        Vector3f result = v.divide(3);

        assertEquals(2, result.getX(), 0.0001);
        assertEquals(3, result.getY(), 0.0001);
        assertEquals(4, result.getZ(), 0.0001);
    }

    @Test
    void testDivideByZero() {
        Vector3f v = new Vector3f(1, 1, 1);

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
        Vector3f v = new Vector3f(2, 3, 6);
        assertEquals(7, v.length(), 0.0001);
    }

    @Test
    void testLengthZero() {
        Vector3f v = new Vector3f(0, 0, 0);
        assertEquals(0, v.length(), 0.0001);
    }

    @Test
    void testNormalize() {
        Vector3f v = new Vector3f(2, 0, 0);
        Vector3f normalized = v.normalize();

        assertEquals(1, normalized.length(), 0.0001);
        assertEquals(1, normalized.getX(), 0.0001);
        assertEquals(0, normalized.getY(), 0.0001);
        assertEquals(0, normalized.getZ(), 0.0001);
    }

    @Test
    void testNormalizeComplex() {
        Vector3f v = new Vector3f(1, 2, 2);
        Vector3f normalized = v.normalize();

        assertEquals(1, normalized.length(), 0.0001);
        assertEquals(1.0/3.0, normalized.getX(), 0.0001);
        assertEquals(2.0/3.0, normalized.getY(), 0.0001);
        assertEquals(2.0/3.0, normalized.getZ(), 0.0001);
    }

    @Test
    void testNormalizeZeroVector() {
        Vector3f v = new Vector3f(0, 0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            v.normalize();
        });

        Vector3f tiny = new Vector3f(1e-11F, 1e-11F, 1e-11F);
        assertThrows(IllegalArgumentException.class, () -> {
            tiny.normalize();
        });
    }

    @Test
    void testNormalizeSafe() {
        Vector3f v1 = new Vector3f(2, 0, 0);
        Vector3f normalized1 = v1.normalizeSafe();
        assertEquals(1, normalized1.length(), 0.0001);
        assertEquals(1, normalized1.getX(), 0.0001);
        assertEquals(0, normalized1.getY(), 0.0001);
        assertEquals(0, normalized1.getZ(), 0.0001);


        Vector3f v2 = new Vector3f(0, 0, 0);
        Vector3f normalized2 = v2.normalizeSafe();
        assertEquals(0, normalized2.length(), 0.0001);
        assertEquals(0, normalized2.getX(), 0.0001);
        assertEquals(0, normalized2.getY(), 0.0001);
        assertEquals(0, normalized2.getZ(), 0.0001);


        Vector3f v3 = new Vector3f(1e-11F, 1e-11F, 1e-11F);
        Vector3f normalized3 = v3.normalizeSafe();
        assertEquals(1e-11, normalized3.getX(), 1e-20);
        assertEquals(1e-11, normalized3.getY(), 1e-20);
        assertEquals(1e-11, normalized3.getZ(), 1e-20);
    }

    @Test
    void testDotProduct() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        double dot = v1.dot(v2);


        assertEquals(32, dot, 0.0001);
    }

    @Test
    void testDotProductWithZero() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(0, 0, 0);
        double dot = v1.dot(v2);

        assertEquals(0, dot, 0.0001);
    }

    @Test
    void testCrossProduct() {

        Vector3f i = new Vector3f(1, 0, 0);
        Vector3f j = new Vector3f(0, 1, 0);
        Vector3f k = new Vector3f(0, 0, 1);


        Vector3f result1 = i.cross(j);
        assertEquals(0, result1.getX(), 0.0001);
        assertEquals(0, result1.getY(), 0.0001);
        assertEquals(1, result1.getZ(), 0.0001);


        Vector3f result2 = j.cross(k);
        assertEquals(1, result2.getX(), 0.0001);
        assertEquals(0, result2.getY(), 0.0001);
        assertEquals(0, result2.getZ(), 0.0001);


        Vector3f result3 = k.cross(i);
        assertEquals(0, result3.getX(), 0.0001);
        assertEquals(1, result3.getY(), 0.0001);
        assertEquals(0, result3.getZ(), 0.0001);


        Vector3f v1 = new Vector3f(2, 3, 4);
        Vector3f v2 = new Vector3f(5, 6, 7);
        Vector3f cross = v1.cross(v2);

        assertEquals(-3, cross.getX(), 0.0001);
        assertEquals(6, cross.getY(), 0.0001);
        assertEquals(-3, cross.getZ(), 0.0001);
    }

    @Test
    void testCrossProductOrthogonal() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        Vector3f cross = v1.cross(v2);

        double dot1 = cross.dot(v1);
        assertEquals(0, dot1, 0.0001);


        double dot2 = cross.dot(v2);
        assertEquals(0, dot2, 0.0001);
    }

    @Test
    void testCrossProductAntiCommutative() {

        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);

        Vector3f cross1 = v1.cross(v2);
        Vector3f cross2 = v2.cross(v1);

        assertEquals(-cross1.getX(), cross2.getX(), 0.0001);
        assertEquals(-cross1.getY(), cross2.getY(), 0.0001);
        assertEquals(-cross1.getZ(), cross2.getZ(), 0.0001);
    }

    @Test
    void testSetters() {
        Vector3f v = new Vector3f(1, 2, 3);


        v.setX(10);
        v.setY(20);
        v.setZ(30);

        assertEquals(10, v.getX(), 0.0001);
        assertEquals(20, v.getY(), 0.0001);
        assertEquals(30, v.getZ(), 0.0001);

        assertEquals(Math.sqrt(100 + 400 + 900), v.length(), 0.0001);


        Vector3f other = new Vector3f(1, 1, 1);
        Vector3f sum = v.add(other);
        assertEquals(11, sum.getX(), 0.0001);
        assertEquals(21, sum.getY(), 0.0001);
        assertEquals(31, sum.getZ(), 0.0001);
    }

}