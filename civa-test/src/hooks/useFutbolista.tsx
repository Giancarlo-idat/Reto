import {
  FutbolistaContext,
  FutbolistaContextProps,
} from "@/context/FutbolistaContex";
import { useContext } from "react";

export const useFutbolista = (): FutbolistaContextProps => {
  const context = useContext(FutbolistaContext);
  if (!context) {
    throw new Error("El contexto de futbolista no está definido");
  }
  return context;
};
