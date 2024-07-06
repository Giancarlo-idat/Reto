import { RoleContext, RoleContextProps } from "@/context";
import { useContext } from "react";

export const useRole = (): RoleContextProps => {
  const context = useContext(RoleContext);
  if (!context) {
    throw new Error("El contexto del rol no está definido");
  }
  return context;
};
