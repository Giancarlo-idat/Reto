// src/components/AddFutbolistaForm.tsx
import React, { useState, ChangeEvent, FormEvent } from "react";
import { Button, Grid, TextField, Typography } from "@mui/material";
import { useFutbolista } from "@/hooks/useFutbolista";

interface Caracteristica {
  nombreCaracteristica: string;
  valorCaracteristica: string;
}

interface FutbolistaForm {
  nombres: string;
  apellidos: string;
  fechaNacimiento: string;
  caracteristicas: Caracteristica[];
}

export const AddFutbolista: React.FC = () => {
  const { addFutbolista } = useFutbolista();
  const [futbolista, setFutbolista] = useState<FutbolistaForm>({
    nombres: "",
    apellidos: "",
    fechaNacimiento: "",
    caracteristicas: [],
  });

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFutbolista({ ...futbolista, [name]: value });
  };

  const handleCaracteristicaChange = (
    e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
    index: number
  ) => {
    const { name, value } = e.target as HTMLInputElement;
    const newCaracteristicas = [...futbolista.caracteristicas];
    newCaracteristicas[index] = {
      ...newCaracteristicas[index],
      [name]: value,
    };
    setFutbolista({ ...futbolista, caracteristicas: newCaracteristicas });
  };

  const addCaracteristica = () => {
    setFutbolista({
      ...futbolista,
      caracteristicas: [
        ...futbolista.caracteristicas,
        { nombreCaracteristica: "", valorCaracteristica: "" },
      ],
    });
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    const caracteristicasObj = futbolista.caracteristicas.reduce(
      (obj, item) => ({
        ...obj,
        [item.nombreCaracteristica]: item.valorCaracteristica,
      }),
      {}
    );

    const futbolistaToSend = {
      ...futbolista,
      caracteristicas: caracteristicasObj,
    };

    await addFutbolista({ ...futbolistaToSend, idFutbolista: "" });
  };

  return (
    <form onSubmit={handleSubmit}>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <TextField
            fullWidth
            label="Nombres"
            name="nombres"
            value={futbolista.nombres}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            fullWidth
            label="Apellidos"
            name="apellidos"
            value={futbolista.apellidos}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            fullWidth
            label="Fecha de Nacimiento"
            name="fechaNacimiento"
            type="date"
            value={futbolista.fechaNacimiento}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <Typography variant="h6">Características</Typography>
          {futbolista.caracteristicas.map((caracteristica, index) => (
            <Grid container spacing={2} key={index}>
              <Grid item xs={6}>
                <TextField
                  fullWidth
                  label="Nombre Característica"
                  name="nombreCaracteristica"
                  value={caracteristica.nombreCaracteristica}
                  onChange={(e) => handleCaracteristicaChange(e, index)}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  fullWidth
                  label="Valor Característica"
                  name="valorCaracteristica"
                  value={caracteristica.valorCaracteristica}
                  onChange={(e) => handleCaracteristicaChange(e, index)}
                />
              </Grid>
            </Grid>
          ))}
          <Button onClick={addCaracteristica}>Añadir característica</Button>
        </Grid>
        <Grid item xs={12}>
          <Button type="submit" variant="contained" color="primary">
            Guardar
          </Button>
        </Grid>
      </Grid>
    </form>
  );
};
