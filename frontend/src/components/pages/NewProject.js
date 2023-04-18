import ProjectForm from "../project/ProjectForm";
import styles from "./NewProjects.module.css";
import { useNavigate } from "react-router-dom";

function NewProject() {
  const navigate = useNavigate();

  function create_project(project) {
    fetch("http://localhost:8080/projects/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(project),
    })
      .then((resp) => resp.json())
      .then((data) => {
        navigate("/projects", {
          state: { message: "Projeto criado com sucesso!" },
        });
      });
  }
  return (
    <div className={styles.newproject_container}>
      <h1>Criar Projeto</h1>
      <p>Crie seu projeto para depois adicionar aos serviços</p>
      <ProjectForm handleSubmit={create_project} btnText="Adicionar Projeto" />
    </div>
  );
}

export default NewProject;
