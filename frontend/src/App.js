import Container from "./components/layout/Container";
import NavBar from "./components/layout/NavBar";
import Footer from "./components/layout/Footer";
//OUTLET É O CONTEÚDO QUE MUDA DE PÁGINA PARA PÁGINA
import { Outlet } from "react-router-dom";

function App() {
  return (
    <>
      <NavBar />
      <Container customClass="min-height">
        <Outlet />
      </Container>
      <Footer />
    </>
  );
}

export default App;
