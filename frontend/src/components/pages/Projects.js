import { useEffect, useState } from "react";
import Message from "../layout/Message";
import { useLocation } from "react-router-dom";
import styles from "./Projects.module.css";
import LinkButton from "../layout/LinkButton";
import Container from "../layout/Container";
import ProjectCard from "../project/ProjectCard";

function Projects() {
  const location = useLocation();
  let message = "";
  if (location.state && location.state.message) {
    message = location.state.message;
  }

  const [projetos, setProjetos] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/projects/all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((resp) => resp.json())
      .then((data) => setProjetos(data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className={styles.project_container}>
      <div className={styles.tittle_container}>
        <h1>Projects</h1>
        <LinkButton to="/newproject" text="Criar Projeto" />
      </div>
      {message && <Message msg={message} type="sucess" />}
      <Container customClass="start">
        {projetos.length > 0 &&
          projetos.map((projeto) => (
            <ProjectCard
              id={projeto.id}
              name={projeto.name}
              orcamento={projeto.orcamento}
              categoria={projeto.categoria.name}
              key={projeto.id}
            />
          ))}
      </Container>
    </div>
  );
}

export default Projects;
