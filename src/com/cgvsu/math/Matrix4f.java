package com.cgvsu.math;

import java.util.Arrays;

public class Matrix4f {
    private float[][] matrix;

    public Matrix4f() {
        matrix = new float[4][4];
        createUnitMatrix();
    }

    public Matrix4f(float[][] values) {
        if (values.length != 4) {
            throw new IllegalArgumentException("Должно быть 4 строки");
        }

        for (int i = 0; i < 4; i++) {
            if (values[i].length != 4) {
                throw new IllegalArgumentException("Строка " + i + " должна иметь 4 элемента");
            }
        }

        this.matrix = new float[4][4];
        for (int i = 0; i < 4; i++) {
            this.matrix[i] = Arrays.copyOf(values[i], 4);
        }
    }

    public void createUnitMatrix() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    matrix[i][j] = 1.0f;
                } else {
                    matrix[i][j] = 0.0f;
                }
            }
        }
    }

    public Matrix4f matrixMultiplication(Matrix4f other) {
        float[][] result = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        return new Matrix4f(result);
    }

    public Vector3f multiplyMatrixByVector(Vector3f vector) {
        float[] result = new float[4];

        for (int i = 0; i < 4; i++) {
            result[i] = matrix[i][0] * vector.x + matrix[i][1] * vector.y +
                    matrix[i][2] * vector.z + matrix[i][3];
        }

        if (result[3] != 0 && result[3] != 1) {
            return new Vector3f(result[0] / result[3], result[1] / result[3], result[2] / result[3]);
        }

        return new Vector3f(result[0], result[1], result[2]);
    }
}
