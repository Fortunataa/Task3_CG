package com.cgvsu.math;

import com.cgvsu.model.Model;

public class AffineTransformations {
    private Matrix4f matrix;

    public AffineTransformations() {
        matrix = new Matrix4f();
    }

    public AffineTransformations(Matrix4f matrix) {
        this.matrix = matrix;
    }

    public enum Axis {
        X, Y, Z
    }

    /**
     * Применяет вращение вокруг оси X на указанный угол
     * @param angle угол поворота
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations rotationX(float angle) {
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
        return this;
    }

    /**
     * Применяет вращение вокруг оси Y на указанный угол
     * @param angle угол поворота
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations rotationY(float angle) {
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
        return this;
    }

    /**
     * Применяет вращение вокруг оси Z на указанный угол
     * @param angle угол поворота
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations rotationZ(float angle) {
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
        return this;
    }

    /**
     * Применяет вращение вокруг указанной оси на заданный угол
     * @param axis ось вращения
     * @param angle угол вращения
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations rotate(Axis axis, float angle) {
        return switch (axis) {
            case X -> rotationX(angle);
            case Y -> rotationY(angle);
            case Z -> rotationZ(angle);
        };
    }

    /**
     * Применяет перемещение на указанные расстояния по осям
     * @param tx смещение по оси X
     * @param ty смещение по оси Y
     * @param tz смещение по оси Z
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations translation(float tx, float ty, float tz) {
        Matrix4f translation = new Matrix4f(new float[][]{
                {1, 0, 0, tx},
                {0, 1, 0, ty},
                {0, 0, 1, tz},
                {0, 0, 0, 1}
        });
        matrix = matrix.matrixMultiplication(translation);
        return this;
    }

    /**
     * Применяет перемещение на вектор
     * @param vector вектор перемещения
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations translation(Vector3f vector) {
        return translation(vector.x, vector.y, vector.z);
    }

    /**
     * Применяет перемещение только по оси X
     * @param tx смещение по оси X
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations translationByX(float tx) {
        return translation(tx, 0, 0);
    }

    /**
     * Применяет перемещение только по оси Y
     * @param ty смещение по оси Y
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations translationByY(float ty) {
        return translation(0, ty, 0);
    }

    /**
     * Применяет перемещение только по оси Z
     * @param tz смещение по оси Z
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations translationByZ(float tz) {
        return translation(0, 0, tz);
    }

    /**
     * Применяет перемещение вдоль указанной оси на заданное расстояние
     * @param axis ось перемещения
     * @param distance расстояние перемещения
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations translation(Axis axis, float distance) {
        return switch (axis) {
            case X -> translationByX(distance);
            case Y -> translationByY(distance);
            case Z -> translationByZ(distance);
        };
    }

    /**
     * Применяет масштабирование с разными коэффициентами по осям
     * @param sx коэффициент масштабирования по оси X
     * @param sy коэффициент масштабирования по оси Y
     * @param sz коэффициент масштабирования по оси Z
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations scaling(float sx, float sy, float sz) {
        Matrix4f scaling = new Matrix4f(new float[][] {
                {sx, 0, 0, 0},
                {0, sy, 0, 0},
                {0, 0, sz, 0},
                {0, 0, 0, 1}
        });
        matrix = matrix.matrixMultiplication(scaling);
        return this;
    }

    /**
     * Применяет масштабирование только по оси X
     * @param sx коэффициент масштабирования по оси X
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations scalingByX(float sx) {
        return scaling(sx, 1, 1);
    }

    /**
     * Применяет масштабирование только по оси Y
     * @param sy коэффициент масштабирования по оси Y
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations scalingByY(float sy) {
        return scaling(1, sy, 1);
    }

    /**
     * Применяет масштабирование только по оси Z
     * @param sz коэффициент масштабирования по оси Z
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations scalingByZ(float sz) {
        return scaling(1, 1, sz);
    }

    /**
     * Применяет равномерное масштабирование по всем осям
     * @param ratio коэффициент масштабирования
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations scaleByRatio(float ratio) {
        return scaling(ratio, ratio, ratio);
    }

    /**
     * Применяет масштабирование с коэффициентами из вектора
     * @param vector вектор коэффициентов масштабирования
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations scaleByVector(Vector3f vector) {
        return scaling(vector.x, vector.y, vector.z);
    }

    /**
     * Применяет масштабирование вдоль указанной оси
     * @param axis ось масштабирования
     * @param ratio коэффициент масштабирования
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations scale(Axis axis, float ratio) {
        return switch (axis){
            case X -> scalingByX(ratio);
            case Y -> scalingByY(ratio);
            case Z -> scalingByZ(ratio);
        };
    }

    /**
     * Применяет другое аффинное преобразование к текущему
     * @param other другое аффинное преобразование
     * @return этот объект для цепочки вызовов
     */
    public AffineTransformations apply(AffineTransformations other) {
        this.matrix = this.matrix.matrixMultiplication(other.getMatrix());
        return this;
    }

    /**
     * Применяет текущее преобразование ко всем вершинам модели
     * @param model модель для преобразования
     */
    public void applyToModel(Model model) {
        for (int i = 0; i < model.vertices.size(); i++) {
            Vector3f vertex = model.vertices.get(i);
            Vector3f transform = matrix.multiplyMatrixByVector(vertex);
            model.vertices.set(i, transform);
        }
    }

    /**
     * Создает копию текущего преобразования,
     * возвращает новый независимый объект с такой же матрицей преобразования
     * @return новая копия этого преобразования
     */
    public AffineTransformations build() {
        return new AffineTransformations(new Matrix4f(this.matrix));
    }

    /**
     * Комбинирует текущее преобразование с другим, создавая новое преобразование
     * @param other другое аффинное преобразование
     * @return новое преобразование, являющееся комбинацией текущего и другого
     */
    public AffineTransformations combination(AffineTransformations other) {
        Matrix4f result = this.matrix.matrixMultiplication((other.getMatrix()));
        return new AffineTransformations(result);
    }

    /**
     * Применяет текущее преобразование к вектору
     * @param vector вектор для преобразования
     * @return преобразованный вектор
     */
    public Vector3f applyToVector(Vector3f vector) {
        return matrix.multiplyMatrixByVector(vector);
    }

    /**
     * Сбрасывает преобразование к единичной матрице
     */
    public void reset() {
        matrix.createUnitMatrix();
    }

    /**
     * Возвращает матрицу преобразования
     * @return матрица преобразования
     */
    protected Matrix4f getMatrix() {
        return matrix;
    }
}
