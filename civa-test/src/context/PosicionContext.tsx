import { createContext, useState, ReactNode } from "react";
import { api } from "@/api";
import { AxiosError } from "axios";
import { useAuth } from "@/hooks/useAuth";

interface Posicion {
  idPosicion: string;
  nombre: string;
}

export interface PosicionContextProps {
  posiciones: Posicion[];
  loading: boolean;
  error: string | null;
  fetchPosiciones: () => Promise<void>;
  fetchPosicionById: (id: string) => Promise<Posicion | void>;
  addPosicion: (posicion: Posicion) => Promise<void>;
}

export const PosicionContext = createContext<PosicionContextProps | undefined>(
  undefined
);

interface PosicionProviderProps {
  children: ReactNode;
}

export const PosicionProvider: React.FC<PosicionProviderProps> = ({
  children,
}) => {
  const [posiciones, setPosiciones] = useState<Posicion[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const { token } = useAuth();

  const fetchPosiciones = async () => {
    try {
      setLoading(true);
      const { data } = await api.get("/posicion/all", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setPosiciones(data);
      setError(null);
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  const fetchPosicionById = async (id: string): Promise<Posicion | void> => {
    try {
      setLoading(true);
      const { data } = await api.get(`/posicion/find?id=${id}`, {
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

  const addPosicion = async (posicion: Posicion) => {
    try {
      setLoading(true);
      await api.post("/posicion/add", posicion, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setError(null);
      await fetchPosiciones(); // Actualizar la lista después de agregar
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <PosicionContext.Provider
      value={{
        posiciones,
        loading,
        error,
        fetchPosiciones,
        fetchPosicionById,
        addPosicion,
      }}
    >
      {children}
    </PosicionContext.Provider>
  );
};
