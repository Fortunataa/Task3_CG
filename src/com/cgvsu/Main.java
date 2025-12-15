package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        String testObj = """
            v -1.0 -1.0 -1.0
            v 1.0 -1.0 -1.0
            v 1.0 1.0 -1.0
            v -1.0 1.0 -1.0
            v -1.0 -1.0 1.0
            v 1.0 -1.0 1.0
            v 1.0 1.0 1.0
            v -1.0 1.0 1.0
           
            f 1 2 3 4
            f 5 6 7 8
            f 1 2 6 5
            f 3 4 8 7
            f 1 4 8 5
            f 2 3 7 6 
            """;

        Path testFile = Path.of("test.obj");
        Files.writeString(testFile, testObj);

        String fileContent = Files.readString(testFile);
        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);


        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());
    }
}
