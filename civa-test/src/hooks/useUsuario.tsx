import { UsuarioContext,UsuarioContextProps } from "@/context";
import { useContext } from "react";

export const useUsuario = (): UsuarioContextProps => {
  const context = useContext(UsuarioContext);
  if (!context) {
    throw new Error("El contexto del usuario no está definido");
  }
  return context;
};
