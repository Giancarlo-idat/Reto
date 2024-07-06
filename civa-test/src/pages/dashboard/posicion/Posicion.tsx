import React, { useEffect } from "react";
import { usePosicion } from "@/hooks"; // Asegúrate de importar el hook correcto para las posiciones
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
} from "@mui/material";

export const Posicion: React.FC = () => {
  const { posiciones, fetchPosiciones, loading, error } = usePosicion();

  useEffect(() => {
    fetchPosiciones();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="w-full p-4 flex justify-center flex-col">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold">Posiciones</h1>
        <Button
          variant="contained"
          color="primary"
          className="bg-blue-500 hover:bg-blue-700"
          onClick={() => {
            // Manejar la lógica para agregar nueva posición
          }}
        >
          + Agregar Nueva
        </Button>
      </div>
      <TableContainer component={Paper}>
        <Table>
          <TableHead className="bg-gray-200">
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Nombre de la Posición</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {posiciones.map((posicion) => (
              <TableRow key={posicion.idPosicion}>
                <TableCell>{posicion.idPosicion}</TableCell>
                <TableCell>{posicion.nombre}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};
