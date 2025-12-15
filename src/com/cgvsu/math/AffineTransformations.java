package com.cgvsu.math;

public class AffineTransformations {
    private Matrix4f matrix;

    public AffineTransformations() {
        matrix = new Matrix4f();
    }

    public void rotationX(float angle) {
        float rad = (float) Math.toRadians(angle);
        float sin = (float) Math.sin(rad);
        float cos = (float) Math.cos(rad);

        Matrix4f rotation = new Matrix4f(new float[][] {
                {1, 0, 0, 0,},
                {0, cos, -sin, 0},
                {0, sin, cos, 0},
                {0, 0, 0, 1}
        });

        matrix = matrix.matrixMultiplication(rotation);
    }

    public void rotationY(float angle) {
        float rad = (float) Math.toRadians(angle);
        float sin = (float) Math.sin(rad);
        float cos = (float) Math.cos(rad);

        Matrix4f rotation = new Matrix4f(new float[][] {
                {cos, 0, sin, 0,},
                {0, 1, 0, 0},
                {-sin, 0, cos, 0},
                {0, 0, 0, 1}
        });
        matrix = matrix.matrixMultiplication(rotation);
    }

    public void rotationZ(float angle) {
        float rad = (float) Math.toRadians(angle);
        float sin = (float) Math.sin(rad);
        float cos = (float) Math.cos(rad);

        Matrix4f rotation = new Matrix4f(new float[][] {
                {cos, -sin, 0, 0},
                {sin, cos, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
        matrix = matrix.matrixMultiplication(rotation);
    }

    public void translation(float tx, float ty, float tz) {
        Matrix4f translation = new Matrix4f(new float[][]{
                {1, 0, 0, tx},
                {0, 1, 0, ty},
                {0, 0, 1, tz},
                {0, 0, 0, 1}
        });
        matrix = matrix.matrixMultiplication(translation);
    }

    public void scaling(float sx, float sy, float sz) {
        Matrix4f scaling = new Matrix4f(new float[][] {
                {sx, 0, 0, 0},
                {0, sy, 0, 0},
                {0, 0, sz, 0},
                {0, 0, 0, 1}
        });
        matrix = matrix.matrixMultiplication(scaling);
    }
}
