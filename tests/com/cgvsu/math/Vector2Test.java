package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {

    @Test
    void testAdd() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(3, 4);
        Vector2f result = v1.add(v2);

        assertEquals(4, result.getX(), 0.0001);
        assertEquals(6, result.getY(), 0.0001);
    }

    @Test
    void testSubtract() {
        Vector2f v1 = new Vector2f(5, 7);
        Vector2f v2 = new Vector2f(2, 3);
        Vector2f result = v1.subtract(v2);

        assertEquals(3, result.getX(), 0.0001);
        assertEquals(4, result.getY(), 0.0001);
    }

    @Test
    void testMultiply() {
        Vector2f v = new Vector2f(2, 3);
        Vector2f result = v.multiply(2);

        assertEquals(4, result.getX(), 0.0001);
        assertEquals(6, result.getY(), 0.0001);
    }

    @Test
    void testDivide() {
        Vector2f v = new Vector2f(6, 8);
        Vector2f result = v.divide(2);

        assertEquals(3, result.getX(), 0.0001);
        assertEquals(4, result.getY(), 0.0001);
    }

    @Test
    void testDivideByZero() {
        Vector2f v = new Vector2f(1, 1);


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
        Vector2f v = new Vector2f(3, 4);
        assertEquals(5, v.length(), 0.0001);
    }

    @Test
    void testNormalize() {
        Vector2f v = new Vector2f(3, 4);
        Vector2f normalized = v.normalize();

        assertEquals(1, normalized.length(), 0.0001);
        assertEquals(0.6, normalized.getX(), 0.0001);
        assertEquals(0.8, normalized.getY(), 0.0001);
    }

    @Test
    void testNormalizeZeroVector() {
        Vector2f v = new Vector2f(0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            v.normalize();
        });

        Vector2f tiny = new Vector2f(1e-11F, 1e-11F);
        assertThrows(IllegalArgumentException.class, () -> {
            tiny.normalize();
        });
    }

    @Test
    void testNormalizeSafe() {
        Vector2f v1 = new Vector2f(3, 4);
        Vector2f normalized1 = v1.normalizeSafe();
        assertEquals(1, normalized1.length(), 0.0001);
        assertEquals(0.6, normalized1.getX(), 0.0001);
        assertEquals(0.8, normalized1.getY(), 0.0001);


        Vector2f v2 = new Vector2f(0, 0);
        Vector2f normalized2 = v2.normalizeSafe();
        assertEquals(0, normalized2.length(), 0.0001);
        assertEquals(0, normalized2.getX(), 0.0001);
        assertEquals(0, normalized2.getY(), 0.0001);
    }

    @Test
    void testDotProduct() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(3, 4);
        float dot = v1.dot(v2);

        assertEquals(11, dot, 0.0001);
    }

    @Test
    void testSetters() {
        Vector2f v = new Vector2f(1, 2);

        v.setX(10);
        v.setY(20);

        assertEquals(10, v.getX(), 0.0001);
        assertEquals(20, v.getY(), 0.0001);

        assertEquals(Math.sqrt(100 + 400), v.length(), 0.0001);
    }
}