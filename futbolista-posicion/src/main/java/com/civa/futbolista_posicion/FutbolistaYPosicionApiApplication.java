package com.civa.futbolista_posicion;

import com.civa.futbolista_posicion.mock.DataMock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FutbolistaYPosicionApiApplication implements CommandLineRunner {

    public final DataMock dataMock;

    public FutbolistaYPosicionApiApplication(DataMock dataMock) {
        this.dataMock = dataMock;
    }

    public static void main(String[] args) {
        SpringApplication.run(FutbolistaYPosicionApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Ejecutando aplicación: ");
        dataMock.run(args);
    }
}
