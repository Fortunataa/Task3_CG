package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Matrix3Test {

    @Test
    void testDefaultConstructorCreatesIdentityMatrix() {
        Matrix3f m = new Matrix3f();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f m = new Matrix3f(values);


        assertEquals(1, m.get(0, 0), 1e-10);
        assertEquals(2, m.get(0, 1), 1e-10);
        assertEquals(3, m.get(0, 2), 1e-10);
        assertEquals(4, m.get(1, 0), 1e-10);
        assertEquals(5, m.get(1, 1), 1e-10);
        assertEquals(6, m.get(1, 2), 1e-10);
        assertEquals(7, m.get(2, 0), 1e-10);
        assertEquals(8, m.get(2, 1), 1e-10);
        assertEquals(9, m.get(2, 2), 1e-10);
    }

    @Test
    void testConstructorInvalidArray() {
        float[][] invalid1 = {{1, 2}, {3, 4}};
        float[][] invalid2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        assertThrows(IllegalArgumentException.class, () -> new Matrix3f(invalid1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix3f(invalid2));
    }

    @Test
    void testMakeIdentity() {
        Matrix3f m = new Matrix3f();
        m.set(0, 0, 5);
        m.makeIdentity();

        assertEquals(1, m.get(0, 0), 1e-10);
        assertEquals(0, m.get(0, 1), 1e-10);
        assertEquals(1, m.get(1, 1), 1e-10);
        assertEquals(0, m.get(2, 0), 1e-10);
    }

    @Test
    void testMakeZero() {
        Matrix3f m = new Matrix3f();
        m.makeZero();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, m.get(i, j), 1e-10);
            }
        }
    }

    @Test
    void testAdd() {
        float[][] values1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        float[][] values2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        Matrix3f m1 = new Matrix3f(values1);
        Matrix3f m2 = new Matrix3f(values2);
        Matrix3f result = m1.add(m2);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(10.0, result.get(i, j), 1e-10);
            }
        }
    }

    @Test
    void testSubtract() {
        float[][] values1 = {
                {10, 10, 10},
                {10, 10, 10},
                {10, 10, 10}
        };
        float[][] values2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Matrix3f m1 = new Matrix3f(values1);
        Matrix3f m2 = new Matrix3f(values2);
        Matrix3f result = m1.subtract(m2);


        assertEquals(9, result.get(0, 0), 1e-10);
        assertEquals(8, result.get(0, 1), 1e-10);
        assertEquals(6, result.get(1, 0), 1e-10);
        assertEquals(1, result.get(2, 2), 1e-10);
    }

    @Test
    void testMultiplyByVector() {
        float[][] values = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f m = new Matrix3f(values);
        Vector3f v = new Vector3f(2, 3, 4);

        Vector3f result = m.multiply(v);


        assertEquals(20, result.getX(), 1e-10);
        assertEquals(47, result.getY(), 1e-10);
        assertEquals(74, result.getZ(), 1e-10);
    }

    @Test
    void testMultiplyByIdentityVector() {
        Matrix3f identity = new Matrix3f();
        Vector3f v = new Vector3f(2, 3, 4);

        Vector3f result = identity.multiply(v);

        assertEquals(2, result.getX(), 1e-10);
        assertEquals(3, result.getY(), 1e-10);
        assertEquals(4, result.getZ(), 1e-10);
    }

    @Test
    void testMultiplyMatrices() {
        float[][] values1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        float[][] values2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        Matrix3f m1 = new Matrix3f(values1);
        Matrix3f m2 = new Matrix3f(values2);
        Matrix3f result = m1.multiply(m2);



        assertEquals(30, result.get(0, 0), 1e-10);
        assertEquals(24, result.get(0, 1), 1e-10);
        assertEquals(18, result.get(0, 2), 1e-10);


        assertEquals(84, result.get(1, 0), 1e-10);


        assertEquals(90, result.get(2, 2), 1e-10);
    }

    @Test
    void testMultiplyByIdentityMatrix() {
        float[][] values = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f m = new Matrix3f(values);
        Matrix3f identity = new Matrix3f();

        Matrix3f result1 = m.multiply(identity);
        Matrix3f result2 = identity.multiply(m);


        assertTrue(m.equals(result1));
        assertTrue(m.equals(result2));
    }

    @Test
    void testTranspose() {
        float[][] values = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f m = new Matrix3f(values);
        Matrix3f transposed = m.transpose();


        assertEquals(1, transposed.get(0, 0), 1e-10);
        assertEquals(4, transposed.get(0, 1), 1e-10);
        assertEquals(2, transposed.get(1, 0), 1e-10);
        assertEquals(5, transposed.get(1, 1), 1e-10);
        assertEquals(9, transposed.get(2, 2), 1e-10);
    }

    @Test
    void testTransposeOfTranspose() {
        float[][] values = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f m = new Matrix3f(values);
        Matrix3f transposedTwice = m.transpose().transpose();

        assertTrue(m.equals(transposedTwice));
    }

    @Test
    void testTransposeOfIdentity() {
        Matrix3f identity = new Matrix3f();
        Matrix3f transposed = identity.transpose();

        assertTrue(identity.equals(transposed));
    }

    @Test
    void testSetAndGet() {
        Matrix3f m = new Matrix3f();

        m.set(0, 0, 10);
        m.set(1, 2, 20);
        m.set(2, 1, 30);

        assertEquals(10, m.get(0, 0), 1e-10);
        assertEquals(20, m.get(1, 2), 1e-10);
        assertEquals(30, m.get(2, 1), 1e-10);
    }

    @Test
    void testSetInvalidIndex() {
        Matrix3f m = new Matrix3f();

        assertThrows(IllegalArgumentException.class, () -> m.set(-1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> m.set(3, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> m.set(0, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> m.set(0, 3, 1));
    }

    @Test
    void testGetInvalidIndex() {
        Matrix3f m = new Matrix3f();

        assertThrows(IllegalArgumentException.class, () -> m.get(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> m.get(3, 0));
    }

    @Test
    void testEquals() {
        float[][] values1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        float[][] values2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        float[][] values3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 10}
        };

        Matrix3f m1 = new Matrix3f(values1);
        Matrix3f m2 = new Matrix3f(values2);
        Matrix3f m3 = new Matrix3f(values3);

        assertTrue(m1.equals(m2));
        assertFalse(m1.equals(m3));
    }


}