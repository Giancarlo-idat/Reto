import React, { useState } from "react";
import { TextField, Button, Paper } from "@mui/material";
import { AuthLogin } from "@/context";
import { useNavigate } from "react-router-dom";
import { useAuth } from "@/hooks/useAuth";

export const Login: React.FC = () => {
  const [authRequest, setAuthRequest] = useState<AuthLogin>({
    email: "",
    password: "",
  });

  const { login, loading, error } = useAuth();
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setAuthRequest({ ...authRequest, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (login) {
      await login(authRequest);
      navigate("/dashboard");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <Paper className="p-6 w-full max-w-md">
        <h1 className="text-2xl font-bold mb-4 text-center">Login</h1>
        {error && <p className="text-red-500 mb-4">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <TextField
              fullWidth
              label="email"
              name="email"
              value={authRequest.email}
              onChange={handleChange}
              variant="outlined"
              disabled={loading}
            />
          </div>
          <div className="mb-4">
            <TextField
              fullWidth
              label="Password"
              name="password"
              type="password"
              value={authRequest.password}
              onChange={handleChange}
              variant="outlined"
              disabled={loading}
            />
          </div>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            className="w-full bg-blue-500 hover:bg-blue-700"
            disabled={loading}
          >
            {loading ? "Iniciando sesión..." : "Iniciar sesión"}
          </Button>
        </form>
      </Paper>
    </div>
  );
};
