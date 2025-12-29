package com.cgvsu.math;

public class Vector3f {
    private static final float EPSILON = 1e-10F;
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(
                this.x + other.x,
                this.y + other.y,
                this.z + other.z
        );
    }

    public Vector3f subtract(Vector3f other) {
        return new Vector3f(
                this.x - other.x,
                this.y - other.y,
                this.z - other.z
        );
    }


    public Vector3f multiply(float scalar) {
        return new Vector3f(
                this.x * scalar,
                this.y * scalar,
                this.z * scalar
        );
    }

    public Vector3f divide(float scalar) {
        if (Math.abs(scalar) < EPSILON) {
            throw new IllegalArgumentException("деление на ноль");
        }
        return new Vector3f(
                this.x / scalar,
                this.y / scalar,
                this.z / scalar
        );
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }


    public Vector3f normalize() {
        float len = length();
        if (len < EPSILON) {
            throw new IllegalArgumentException("Невозможно нормализовать нулевой вектор");
        }
        return new Vector3f(x / len, y / len, z / len);
    }

    public Vector3f normalizeSafe() {
        float len = length();
        if (len < EPSILON) {
            return new Vector3f(x, y, z);
        }
        return new Vector3f(x / len, y / len, z / len);
    }

    public float dot(Vector3f other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3f cross(Vector3f other) {
        return new Vector3f(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }

    @Override
    public String toString() {
        return String.format("Vector3f(%.4f, %.4f, %.4f)", x, y, z);
    }
}