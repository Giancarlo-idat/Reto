import {
  Drawer,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Divider,
} from "@mui/material";
import {
  GolfCourse,
  Face,
  SportsHandball,
  ExitToApp,
  Accessibility,
} from "@mui/icons-material";
import Logo from "@/assets/logo.png";
import { Link } from "react-router-dom";

export const Sidebar = () => {
  const menuItems = [
    { text: "Futbolistas", icon: <SportsHandball />, path: "futbolistas" },
    { text: "Posiciones", icon: <GolfCourse />, path: "posiciones" },
    { text: "Roles", icon: <Accessibility />, path: "roles" },
    { text: "Usuarios", icon: <Face />, path: "usuarios" },
  ];

  return (
    <Drawer variant="permanent" anchor="left">
      <div className="flex flex-col h-full p-6">
        <div className="flex items-center justify-center h-16">
          <Link to="/dashboard">
            <img src={Logo} alt="logo" className="h-10" loading="lazy" />
          </Link>
        </div>
        <Divider className="bg-gray-600" />
        <List className="flex-grow flex gap-4 flex-col">
          {menuItems.map((item, index) => (
            <ListItem
              key={index}
              className="hover:bg-gradient-to-r from-purple-600 to-pink-500 hover:text-white transition-all duration-300 ease-in-out hover:rounded-lg"
            >
              <ListItemIcon className="text-white">{item.icon}</ListItemIcon>
              <Link className="cursor-pointer" to={`/dashboard/${item.path}`}>
                <ListItemText primary={item.text} />
              </Link>
            </ListItem>
          ))}
        </List>
        <Divider className="bg-gray-600" />
        <List>
          <ListItem className="hover:bg-gray-700">
            <ListItemIcon className="text-white">
              <ExitToApp />
            </ListItemIcon>
            <ListItemText primary="Logout" />
          </ListItem>
        </List>
      </div>
    </Drawer>
  );
};
