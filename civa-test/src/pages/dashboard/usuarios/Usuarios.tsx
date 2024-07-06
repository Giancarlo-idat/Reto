// src/components/Users.tsx
import React, { useEffect } from "react";
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
import { useUsuario } from "@/hooks/useUsuario";

export const Usuarios: React.FC = () => {
  const { users, fetchUsers, loading, error } = useUsuario();

  // Manejar la carga inicial de usuarios
  useEffect(() => {
    fetchUsers();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="w-full p-4 flex justify-center flex-col">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold">Usuarios</h1>
        <Button
          variant="contained"
          color="primary"
          className="bg-blue-500 hover:bg-blue-700"
          onClick={() => {
            // Lógica para agregar nuevo usuario
          }}
        >
          + Agregar Nuevo
        </Button>
      </div>
      <TableContainer component={Paper}>
        <Table>
          <TableHead className="bg-gray-200">
            <TableRow>
              <TableCell>ID Usuario</TableCell>
              <TableCell>Nombre de Usuario</TableCell>
              <TableCell>Apellido</TableCell>
              <TableCell>Nickname</TableCell>
              <TableCell>Teléfono</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Dirección</TableCell>
              <TableCell>Fecha de Nacimiento</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {users.map((user) => (
              <TableRow key={user.idUser}>
                <TableCell>{user.idUser}</TableCell>
                <TableCell>{user.name}</TableCell>
                <TableCell>{user.lastName}</TableCell>
                <TableCell>{user.nickname}</TableCell>
                <TableCell>{user.phone}</TableCell>
                <TableCell>{user.email}</TableCell>
                <TableCell>{user.address}</TableCell>
                <TableCell>{user.birthday}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};
