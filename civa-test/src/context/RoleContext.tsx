import React, { createContext, useState, useEffect, ReactNode } from "react";
import { api } from "@/api";
import { AxiosError } from "axios";
import { useAuth } from "@/hooks/useAuth";

// Interface para el modelo de datos del rol
export interface Role {
  idRole: string;
  name: string;
}

// Interface para las propiedades del contexto
export interface RoleContextProps {
  roles: Role[];
  loading: boolean;
  error: string | null;
  fetchRoles: () => Promise<void>;
}

// Contexto de roles
export const RoleContext = createContext<RoleContextProps | undefined>(
  undefined
);

// Propiedades para el proveedor de contexto
interface RoleProviderProps {
  children: ReactNode;
}

// Proveedor de contexto para roles
export const RoleProvider: React.FC<RoleProviderProps> = ({ children }) => {
  const [roles, setRoles] = useState<Role[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const { token } = useAuth();

  // Función para obtener todos los roles
  const fetchRoles = async () => {
    setLoading(true);
    try {
      const { data } = await api.get("/roles/all", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setRoles(data);
      setError(null);
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  // Efecto para cargar roles al montar el componente
  useEffect(() => {
    fetchRoles();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // Valor proporcionado por el contexto
  const contextValue: RoleContextProps = {
    roles,
    loading,
    error,
    fetchRoles,
  };

  return (
    <RoleContext.Provider value={contextValue}>{children}</RoleContext.Provider>
  );
};
