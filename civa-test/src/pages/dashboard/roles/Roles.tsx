/* eslint-disable react-hooks/exhaustive-deps */
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
import { useRole } from "@/hooks";

export const Roles: React.FC = () => {
  const { roles, fetchRoles, loading, error } = useRole();

  // Manejar la carga inicial de roles
  useEffect(() => {
    fetchRoles();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="w-full p-4 flex justify-center flex-col">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold">Roles</h1>
        <Button
          variant="contained"
          color="primary"
          className="bg-blue-500 hover:bg-blue-700"
          onClick={() => {
            // Lógica para agregar nuevo rol
          }}
        >
          + Agregar Nuevo
        </Button>
      </div>
      <TableContainer component={Paper}>
        <Table>
          <TableHead className="bg-gray-200">
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Nombre</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {roles.map((role) => (
              <TableRow key={role.idRole}>
                <TableCell>{role.idRole}</TableCell>
                <TableCell>{role.name}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};
