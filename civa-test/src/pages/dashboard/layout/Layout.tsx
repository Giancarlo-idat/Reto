import React from "react";
import { Outlet } from "react-router-dom";
import { Sidebar } from "../sidebar";
import { List, ListItem, ListItemText } from "@mui/material";
import { DarkModeOutlined, LightModeOutlined } from "@mui/icons-material";

export const Layout: React.FC = () => {
  return (
    <div className="w-full h-dvh">
      <div className="w-full h-dvh flex">
        {/* LEFT */}
        <div className="flex flex-col flex-10">
          <Sidebar />
        </div>
        {/* CENTER */}
        <div className="flex flex-col flex-80">
          <div className="flex flex-col my-6">
            {/* HEADER */}
            <div className="flex justify-between items-center px-12">
              <h5 className="font-bold text-xl font-raleway text-sidebarLeft">
                Dashboard
              </h5>
              <div className="flex gap-8 items-center">
                <List
                  style={{ padding: ".75rem .2rem" }}
                  component="div"
                  disablePadding
                  className="flex items-center rounded-xl bg-black40 h-8"
                >
                  <ListItem className="flex">
                    <LightModeOutlined
                      className=" cursor-pointer"
                      fontSize="small"
                    />
                  </ListItem>
                  <ListItem className="flex">
                    <DarkModeOutlined
                      className=" cursor-pointer"
                      fontSize="small"
                    />
                  </ListItem>
                </List>
                <div className="flex gap-4">
                  <List component="div" className="flex flex-col ">
                    <ListItemText
                      style={{ margin: "0" }}
                      primary={
                        <span className="font-raleway text-sm font-semibold">
                          Hey, <strong>John</strong>
                        </span>
                      }
                    />
                    <ListItemText
                      style={{ margin: "0" }}
                      primary={
                        <span className="font-raleway text-sm font-semibold">
                          Admin
                        </span>
                      }
                    />
                  </List>

                  <div className="flex items-center justify-center">
                    <img
                      loading="lazy"
                      className="w-12 rounded-full"
                      alt="profile"
                      src="https://atg-prod-scalar.s3.amazonaws.com/studentpower/media/user%20avatar.png"
                    />
                  </div>
                </div>
              </div>
            </div>

            {/* CONTENT */}
            <div className="w-full flex gap-8 py-6 px-12">
              <Outlet />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
