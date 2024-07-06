import { createContext, useState, ReactNode } from "react";
import { api } from "@/api";
import { AxiosError } from "axios";
import { useAuth } from "@/hooks/useAuth";

export interface Futbolista {
  idFutbolista: string;
  nombres: string;
  apellidos: string;
  fechaNacimiento: string;
  caracteristicas: Record<string, string>;
}

export interface FutbolistaContextProps {
  futbolistas: Futbolista[];
  loading: boolean;
  error: string | null;
  fetchFutbolistas: (page?: number, size?: number) => Promise<void>;
  fetchFutbolistaById: (id: string) => Promise<Futbolista | void>;
  addFutbolista: (futbolista: Futbolista) => Promise<void>;
  page: number;
  setPage: (page: number) => void;
  rowsPerPage: number;
  setRowsPerPage: (size: number) => void;
  totalCount: number;
}

export const FutbolistaContext = createContext<
  FutbolistaContextProps | undefined
>(undefined);

interface FutbolistaProviderProps {
  children: ReactNode;
}

export const FutbolistaProvider: React.FC<FutbolistaProviderProps> = ({
  children,
}) => {
  const [futbolistas, setFutbolistas] = useState<Futbolista[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const [page, setPage] = useState<number>(0);
  const [rowsPerPage, setRowsPerPage] = useState<number>(5); // Paginación de 5 en 5
  const [totalCount, setTotalCount] = useState<number>(0);

  const { token } = useAuth();

  const fetchFutbolistas = async (page = 0, size = 5) => {
    try {
      setLoading(true);
      const { data } = await api.get(`/futbolista/all`, {
        params: { page, size },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setFutbolistas(data.content);
      setTotalCount(data.totalElements);
      setError(null);
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  const fetchFutbolistaById = async (
    id: string
  ): Promise<Futbolista | void> => {
    try {
      setLoading(true);
      const response = await api.get(`/futbolista/find`, {
        params: { id },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setError(null);
      return response.data;
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  const addFutbolista = async (futbolista: Futbolista) => {
    try {
      setLoading(true);
      await api.post(`/futbolista/add`, futbolista, {
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

  return (
    <FutbolistaContext.Provider
      value={{
        futbolistas,
        loading,
        error,
        fetchFutbolistas,
        fetchFutbolistaById,
        addFutbolista,
        page,
        setPage,
        rowsPerPage,
        setRowsPerPage,
        totalCount,
      }}
    >
      {children}
    </FutbolistaContext.Provider>
  );
};
