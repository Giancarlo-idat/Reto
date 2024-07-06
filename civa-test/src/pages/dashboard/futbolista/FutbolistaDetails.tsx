import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useFutbolista } from "@/hooks";
import { Futbolista as FutbolistaType } from "@/context";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  CircularProgress,
  Button,
} from "@mui/material";

export const FutbolistaDetails: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const { fetchFutbolistaById } = useFutbolista();
  const [futbolista, setFutbolista] = useState<FutbolistaType | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const getFutbolista = async () => {
      try {
        const data = await fetchFutbolistaById(id || "");
        setFutbolista(data || null);
        setError(null);
      } catch (err) {
        setError("Error fetching futbolista details");
      } finally {
        setLoading(false);
      }
    };

    getFutbolista();
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  if (loading) return <CircularProgress />;
  if (error) return <p>{error}</p>;

  if (!futbolista) return <p>Futbolista no encontrado</p>;

  return (
    <div className="w-full">
      <TableContainer component={Paper}>
        <Table>
          <TableHead className="bg-gray-200">
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Nombres</TableCell>
              <TableCell>Apellidos</TableCell>
              <TableCell>Fecha de Nacimiento</TableCell>
              <TableCell>Características</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow key={futbolista.idFutbolista}>
              <TableCell>{futbolista.idFutbolista}</TableCell>
              <TableCell>{futbolista.nombres}</TableCell>
              <TableCell>{futbolista.apellidos}</TableCell>
              <TableCell>{futbolista.fechaNacimiento}</TableCell>
              <TableCell>
                {Object.entries(futbolista.caracteristicas).map(
                  ([key, value]) => (
                    <div key={key}>
                      <strong>{key}:</strong> {value}
                    </div>
                  )
                )}
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>
      <Button
        variant="contained"
        color="primary"
        className="bg-blue-500 hover:bg-blue-700"
        onClick={() => window.history.back()}
      >
        Volver
      </Button>
    </div>
  );
};
