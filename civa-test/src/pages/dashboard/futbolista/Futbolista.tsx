import React, { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  TablePagination,
  CircularProgress,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useFutbolista } from "@/hooks";
import { AddFutbolista } from "./AddFutbolista";

export const Futbolista: React.FC = () => {
  const {
    futbolistas,
    fetchFutbolistas,
    loading,
    error,
    page,
    setPage,
    rowsPerPage,
    setRowsPerPage,
    totalCount,
  } = useFutbolista();
  const [open, setOpen] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    fetchFutbolistas(page, rowsPerPage);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [page, rowsPerPage]);

  const handleChangePage = (_event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleDetailFutbolista = (id: string) => {
    navigate(`/dashboard/futbolistas/${id}`);
  };

  const handleAddFutbolista = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  if (loading) return <CircularProgress />;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="w-full p-4 flex justify-center flex-col">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold">Futbolistas</h1>
        <Button
          variant="contained"
          color="primary"
          className="bg-blue-500 hover:bg-blue-700"
          onClick={handleAddFutbolista}
        >
          + Agregar Nuevo
        </Button>
      </div>
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
            {futbolistas.map((futbolista) => (
              <TableRow
                key={futbolista.idFutbolista}
                hover
                onClick={() => handleDetailFutbolista(futbolista.idFutbolista)}
                style={{ cursor: "pointer" }}
              >
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
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[5]} // Opciones de paginación
        component="div"
        count={totalCount}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
      <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
        <DialogTitle>Agregar Nuevo Futbolista</DialogTitle>
        <DialogContent>
          <AddFutbolista />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancelar
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};
