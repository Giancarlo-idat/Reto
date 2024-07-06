import { PosicionContext, PosicionContextProps } from "@/context";
import { useContext } from "react";

export const usePosicion = (): PosicionContextProps => {
  const context = useContext(PosicionContext);
  if (!context) {
    throw new Error("El contexto de la posición no está definido");
  }
  return context;
};
