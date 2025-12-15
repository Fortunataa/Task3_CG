package com.cgvsu;

import com.cgvsu.math.AffineTransformations;
import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя OBJ файла: ");
        String filename = scanner.nextLine();
        String text = Files.readString(Path.of(filename));
        Model model = ObjReader.read(text);
        System.out.println("Модель загружена.");
        System.out.println("Количество вершин: " + model.vertices.size());
        System.out.println("Количество полигонов: " + model.polygons.size());

        AffineTransformations transformations = new AffineTransformations();

        boolean run = true;
        while (run) {
            System.out.println("\n============ МЕНЮ ============");
            System.out.println("1. Перемещение");
            System.out.println("2. Масштабирование");
            System.out.println("3. Вращение вокруг оси X");
            System.out.println("4. Вращение вокруг оси Y");
            System.out.println("5. Вращение вокруг оси Z");
            System.out.println("6. Применить преобразования к модели");
            System.out.println("7. Сбросить преобразования");
            System.out.println("8. Сохранить модель в файл");
            System.out.println("9. Выход из программы");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите смещение по X: ");
                    float tx = scanner.nextFloat();
                    System.out.print("Введите смещение по Y: ");
                    float ty = scanner.nextFloat();
                    System.out.print("Введите смещение по Z: ");
                    float tz = scanner.nextFloat();
                    transformations.translation(tx, ty, tz);
                    System.out.println("Перемещение добавлено");
                    break;
                case 2:
                    System.out.print("Введите масштаб по X: ");
                    float sx = scanner.nextFloat();
                    System.out.print("Введите масштаб по Y: ");
                    float sy = scanner.nextFloat();
                    System.out.print("Введите масштаб по Z: ");
                    float sz = scanner.nextFloat();
                    transformations.scaling(sx, sy, sz);
                    System.out.println("Масштабирование добавлено");
                    break;
                case 3:
                    System.out.print("Введите угол: ");
                    float angleX = scanner.nextFloat();
                    transformations.rotationX(angleX);
                    System.out.println("Вращение вокруг X добавлено");
                    break;
                case 4:
                    System.out.print("Введите угол: ");
                    float angleY = scanner.nextFloat();
                    transformations.rotationY(angleY);
                    System.out.println("Вращение вокруг Y добавлено");
                    break;
                case 5:
                    System.out.println("Введите угол: ");
                    float angleZ = scanner.nextFloat();
                    transformations.rotationZ(angleZ);
                    System.out.println("Вращение вокруг Z добавлено");
                    break;
                case 6:
                    transformations.applyToModel(model);
                    System.out.println("Преобразования применены к модели");
                    break;
                case 7:
                    transformations.reset();
                    System.out.println("Преобразования сброшены");
                    break;
                case 8:
                    System.out.print("Введите имя файла для сохранения: ");
                    scanner.nextLine();
                    String outputFile = scanner.nextLine();
                    ObjWriter writer = new ObjWriter();
                    writer.write(model, outputFile);
                    System.out.println("Модель сохранена в " + outputFile);
                    break;
                case 9:
                    run = false;
                    System.out.println("Выход из программы");
                    break;
                default:
                    System.out.println("Неверный выбор!");
                    break;
            }
        }
        scanner.close();
    }
}