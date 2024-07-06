import { Routes, Route, Navigate } from "react-router-dom";
import { useAuth } from "@/hooks/useAuth";
import { Futbolista, Layout, Posicion, Roles, Usuarios, Login, FutbolistaDetails } from "@/pages";

export const AppRoutes: React.FC = () => {
  const { token } = useAuth();

  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route
        path="/dashboard"
        element={token ? <Layout /> : <Navigate to="/login" />}
      >
        <Route path="futbolistas" element={<Futbolista />} />
        <Route path="posiciones" element={<Posicion />} />
        <Route path="roles" element={<Roles />} />
        <Route path="usuarios" element={<Usuarios />} />
        <Route path="futbolistas/:id" element={<FutbolistaDetails />} />
        {/* Añade tus demás rutas aquí */}
      </Route>
    </Routes>
  );
};

