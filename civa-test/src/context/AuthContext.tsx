import { createContext, useState, ReactNode, useEffect } from "react";
import { api } from "@/api";
import { AxiosError } from "axios";
import { useNavigate } from "react-router-dom";

export interface AuthLogin {
  email: string;
  password: string;
}

export interface AuthContextProps {
  token: string | null;
  loading: boolean;
  error: string | null;
  login: (authLogin: AuthLogin) => Promise<void>;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextProps | undefined>(
  undefined
);

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [token, setToken] = useState<string | null>(
    localStorage.getItem("token")
  );
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);


  const navigate = useNavigate();

  useEffect(() => {
    if (token) {
      localStorage.setItem("token", token);
      api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    } else {
      localStorage.removeItem("token");
      delete api.defaults.headers.common["Authorization"];
      navigate("/login");
    }
  }, [token, navigate]);


  const login = async (authLogin: AuthLogin) => {
    setLoading(true);
    try {
      const { data } = await api.post("/auth/login", authLogin);
      console.log(data)
      setToken(data.jwt);
      setError(null);
    } catch (error) {
      setError((error as AxiosError).message);
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    setToken(null);
  };

  return (
    <AuthContext.Provider value={{ token, loading, error, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
