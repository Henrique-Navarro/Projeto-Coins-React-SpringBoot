import { useEffect, useState } from "react";
import Message from "../layout/Message";
import { useLocation } from "react-router-dom";
import styles from "./Projects.module.css";
import LinkButton from "../layout/LinkButton";
import Container from "../layout/Container";
import ProjectCard from "../project/ProjectCard";
import Loading from "../layout/Loading";

function Projects() {
  const [removeLoading, setRemoveLoading] = useState(false);
  const [projectMessage, setProjectMessage] = useState("");

  const location = useLocation();
  let message = "";
  if (location.state && location.state.message) {
    message = location.state.message;
  }

  const [projetos, setProjetos] = useState([]);
  useEffect(() => {
    setTimeout(() => {
      fetch("http://localhost:8080/projects/all", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((resp) => resp.json())
        .then((data) => {
          setProjetos(data);
          setRemoveLoading(true);
        })
        .catch((err) => console.log(err));
    }, 1000);
  }, []);

  function removeProject(id) {
    console.log("Id removido:" + id);
    fetch(`http://localhost:8080/projects/delete?id=${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then(() => {
        setProjetos(projetos.filter((projeto) => projeto.id !== id));
        setProjectMessage("Projeto Removido com Sucesso!");
      })
      .catch((err) => console.log(err));
  }

  return (
    <div className={styles.project_container}>
      <div className={styles.tittle_container}>
        <h1>Projects</h1>
        <LinkButton to="/newproject" text="Criar Projeto" />
      </div>

      {message && <Message msg={message} type="sucess" />}
      {projectMessage && <Message msg={projectMessage} type="delete" />}

      <Container customClass="start">
        {projetos.length > 0 &&
          projetos.map((projeto) => (
            <ProjectCard
              id={projeto.id}
              name={projeto.name}
              orcamento={projeto.orcamento}
              categoria={projeto.categoria.name}
              key={projeto.id}
              handleRemove={removeProject}
            />
          ))}
        {!removeLoading && <Loading />}
        {removeLoading && projetos.length === 0 && (
          <h3>Não há projetos cadastrados 😞</h3>
        )}
      </Container>
    </div>
  );
}

export default Projects;
