package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Matrix4Test {

    @Test
    void testDefaultConstructorCreatesIdentityMatrix() {
        Matrix4f m = new Matrix4f();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    assertEquals(1.0, m.get(i, j), 1e-10);
                } else {
                    assertEquals(0.0, m.get(i, j), 1e-10);
                }
            }
        }
    }

    @Test
    void testConstructorFromArray() {
        float[][] values = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f m = new Matrix4f(values);

        assertEquals(1, m.get(0, 0), 1e-10);
        assertEquals(6, m.get(1, 1), 1e-10);
        assertEquals(11, m.get(2, 2), 1e-10);
        assertEquals(16, m.get(3, 3), 1e-10);
        assertEquals(5, m.get(1, 0), 1e-10);
        assertEquals(10, m.get(2, 1), 1e-10);
    }

    @Test
    void testConstructorInvalidArray() {
        float[][] invalid1 = {{1, 2}, {3, 4}};
        float[][] invalid2 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20}
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix4f(invalid1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix4f(invalid2));
    }

    @Test
    void testMakeIdentity() {
        Matrix4f m = new Matrix4f();
        m.set(0, 0, 5);
        m.makeIdentity();

        assertEquals(1, m.get(0, 0), 1e-10);
        assertEquals(0, m.get(0, 1), 1e-10);
        assertEquals(1, m.get(1, 1), 1e-10);
        assertEquals(0, m.get(3, 0), 1e-10);
        assertEquals(1, m.get(3, 3), 1e-10);
    }

    @Test
    void testMakeZero() {
        Matrix4f m = new Matrix4f();
        m.makeZero();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(0.0, m.get(i, j), 1e-10);
            }
        }
    }

    @Test
    void testAdd() {
        float[][] values1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] values2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };

        Matrix4f m1 = new Matrix4f(values1);
        Matrix4f m2 = new Matrix4f(values2);
        Matrix4f result = m1.add(m2);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(17.0, result.get(i, j), 1e-10);
            }
        }
    }

    @Test
    void testSubtract() {
        float[][] values1 = {
                {10, 10, 10, 10},
                {10, 10, 10, 10},
                {10, 10, 10, 10},
                {10, 10, 10, 10}
        };
        float[][] values2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        Matrix4f m1 = new Matrix4f(values1);
        Matrix4f m2 = new Matrix4f(values2);
        Matrix4f result = m1.subtract(m2);

        assertEquals(9, result.get(0, 0), 1e-10);
        assertEquals(8, result.get(0, 1), 1e-10);
        assertEquals(5, result.get(1, 0), 1e-10);
        assertEquals(-6, result.get(3, 3), 1e-10);
    }

    @Test
    void testMultiplyByVector() {
        float[][] values = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f m = new Matrix4f(values);
        Vector4f v = new Vector4f(2, 3, 4, 5);

        Vector4f result = m.multiply(v);

        assertEquals(40, result.getX(), 1e-10);
        assertEquals(96, result.getY(), 1e-10);
        assertEquals(152, result.getZ(), 1e-10);
        assertEquals(208, result.getW(), 1e-10);
    }

    @Test
    void testMultiplyByIdentityVector() {
        Matrix4f identity = new Matrix4f();
        Vector4f v = new Vector4f(2, 3, 4, 5);

        Vector4f result = identity.multiply(v);

        assertEquals(2, result.getX(), 1e-10);
        assertEquals(3, result.getY(), 1e-10);
        assertEquals(4, result.getZ(), 1e-10);
        assertEquals(5, result.getW(), 1e-10);
    }

    @Test
    void testMultiplyMatrices() {
        float[][] values1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] values2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };

        Matrix4f m1 = new Matrix4f(values1);
        Matrix4f m2 = new Matrix4f(values2);
        Matrix4f result = m1.multiply(m2);

        assertEquals(80, result.get(0, 0), 1e-10);
        assertEquals(70, result.get(0, 1), 1e-10);
        assertEquals(240, result.get(1, 0), 1e-10);
        assertEquals(386, result.get(3, 3), 1e-10);
    }

    @Test
    void testMultiplyByIdentityMatrix() {
        float[][] values = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f m = new Matrix4f(values);
        Matrix4f identity = new Matrix4f();

        Matrix4f result1 = m.multiply(identity);
        Matrix4f result2 = identity.multiply(m);

        assertTrue(m.equals(result1));
        assertTrue(m.equals(result2));
    }

    @Test
    void testTranspose() {
        float[][] values = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f m = new Matrix4f(values);
        Matrix4f transposed = m.transpose();

        assertEquals(1, transposed.get(0, 0), 1e-10);
        assertEquals(5, transposed.get(0, 1), 1e-10);
        assertEquals(2, transposed.get(1, 0), 1e-10);
        assertEquals(6, transposed.get(1, 1), 1e-10);
        assertEquals(11, transposed.get(2, 2), 1e-10);
        assertEquals(16, transposed.get(3, 3), 1e-10);
    }

    @Test
    void testTransposeOfTranspose() {
        float[][] values = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f m = new Matrix4f(values);
        Matrix4f transposedTwice = m.transpose().transpose();

        assertTrue(m.equals(transposedTwice));
    }

    @Test
    void testTransposeOfIdentity() {
        Matrix4f identity = new Matrix4f();
        Matrix4f transposed = identity.transpose();

        assertTrue(identity.equals(transposed));
    }

    @Test
    void testSetAndGet() {
        Matrix4f m = new Matrix4f();

        m.set(0, 0, 10);
        m.set(1, 2, 20);
        m.set(2, 3, 30);
        m.set(3, 1, 40);

        assertEquals(10, m.get(0, 0), 1e-10);
        assertEquals(20, m.get(1, 2), 1e-10);
        assertEquals(30, m.get(2, 3), 1e-10);
        assertEquals(40, m.get(3, 1), 1e-10);
    }

    @Test
    void testSetInvalidIndex() {
        Matrix4f m = new Matrix4f();

        assertThrows(IllegalArgumentException.class, () -> m.set(-1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> m.set(4, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> m.set(0, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> m.set(0, 4, 1));
    }

    @Test
    void testGetInvalidIndex() {
        Matrix4f m = new Matrix4f();

        assertThrows(IllegalArgumentException.class, () -> m.get(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> m.get(4, 0));
    }

    @Test
    void testEquals() {
        float[][] values1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] values2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] values3 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 17}
        };

        Matrix4f m1 = new Matrix4f(values1);
        Matrix4f m2 = new Matrix4f(values2);
        Matrix4f m3 = new Matrix4f(values3);

        assertTrue(m1.equals(m2));
        assertFalse(m1.equals(m3));
    }

}