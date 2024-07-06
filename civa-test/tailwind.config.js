/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        principal: "#E2721F",
        title: "#0F1111",
        bghover: "#CAA462",
        bgicon: "#ADADAD",
        cborder: "#f5f5f5",
        bgtransparent: "#FCFCFC",
      },
      fontSize: {
        2.5: "28px",
      },
      height: {
        600: "600px",
        700: "700px",
      },
      zIndex: {
        1: "1",
      },
      fontFamily: {
        cinzel: "Cinzel",
        lato: "Lato, sans-serif",
        amazonEmber: "Amazon Ember Thin, sans-serif",
      },
      screens: {
        tablet: "640px",
        mobile: "375px",
      },
      fontWeight: {
        400: "400",
      },
      flex: {
        10: "1 1 10%",
        20: "1 1 20%",
        25: "1 1 25%",
        50: "1 1 50%",
        75: "1 1 75%",
        80: "1 1 80%",
        90: "1 1 90%",
      },
    },
  },
  plugins: [],
};
