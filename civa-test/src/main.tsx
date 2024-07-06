import ReactDOM from "react-dom/client";
import { AppRoutes } from "@/routes/AppRoutes.tsx";
import { BrowserRouter } from "react-router-dom";
import {
  FutbolistaProvider,
  AuthProvider,
  PosicionProvider,
  RoleProvider,
  UsuarioProvider,
} from "@/context";

import "./index.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <BrowserRouter>
    <AuthProvider>
      <UsuarioProvider>
        <RoleProvider>
          <FutbolistaProvider>
            <PosicionProvider>
              <AppRoutes />
            </PosicionProvider>
          </FutbolistaProvider>
        </RoleProvider>
      </UsuarioProvider>
    </AuthProvider>
  </BrowserRouter>
);
