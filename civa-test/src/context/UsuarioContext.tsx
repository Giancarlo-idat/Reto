/* eslint-disable react-hooks/exhaustive-deps */
import React, { createContext, useState, useEffect, ReactNode } from "react";
import { api } from "@/api";
import { AxiosError } from "axios";
import { useAuth } from "@/hooks/useAuth";

// Interface para el modelo de datos del usuario
export interface Usuario {
  idUser: string;
  name: string;
  lastName: string;
  nickname: string;
  email: string;
  phone: string;
  address: string;
  birthday: string;
}

// Interface para las propiedades del contexto
export interface UsuarioContextProps {
  users: Usuario[];
  loading: boolean;
  error: string | null;
  fetchUsers: () => Promise<void>;
  fetchUserById: (id: string) => Promise<Usuario | void>;
  saveUser: (user: Usuario) => Promise<void>;
}

// Contexto de usuarios
export const UsuarioContext = createContext<UsuarioContextProps | undefined>(
  undefined
);

// Propiedades para el proveedor de contexto
interface UsuarioProviderProps {
  children: ReactNode;
}

// Proveedor de contexto para usuarios
export const UsuarioProvider: React.FC<UsuarioProviderProps> = ({
  children,
}) => {
  const [users, setUsers] = useState<Usuario[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const { token } = useAuth();

  // Función para obtener todos los usuarios
  const fetchUsers = async () => {
    setLoading(true);
    try {
      const { data } = await api.get("/users/all", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setUsers(data);
      setError(null);
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  // Función para obtener un usuario por su ID
  const fetchUserById = async (id: string) => {
    setLoading(true);
    try {
      const { data } = await api.get("/users/userId", {
        params: { id },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setError(null);
      return data;
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  // Función para guardar un usuario
  const saveUser = async (user: Usuario) => {
    setLoading(true);
    try {
      await api.post("/users/save", user, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setError(null);
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  // Efecto para cargar usuarios al montar el componente
  useEffect(() => {
    fetchUsers();
  }, []);

  // Valor proporcionado por el contexto
  const contextValue: UsuarioContextProps = {
    users,
    loading,
    error,
    fetchUsers,
    fetchUserById,
    saveUser,
  };

  return (
    <UsuarioContext.Provider value={contextValue}>{children}</UsuarioContext.Provider>
  );
};
